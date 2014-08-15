@echo off
setlocal
path %path%;%cd%\..\bin.win32;%cd%\..\bin

echo I.1: ���Ϳ�X�ؿ�
for %%i in (output output\feature output\hmm) do mkdir %%i > nul 2>&1

echo I.2: ���� modelName.txt �� phone.mlf
@echo EX > output\syl2phone.scp
HLEd -n output\modelName.txt -d digitMonophone.pam -l * -i output\phone.mlf output\syl2phone.scp syl.mlf

echo I.3: ���� wav2fea.scp 
(for /f "delims=" %%i in ('dir/s/b ..\waveFile\*.wav') do @echo %%i output\feature\%%~ni.fea)> output\wav2fea.scp

echo I.4: �ϥ� HCopy.exe �i��y���S�x���
HCopy -C mfcc.cfg -S output\wav2fea.scp

echo II.1: ���ͰV�m��ƩM���ո�ƪ��ɮצC�� trainFea.scp �� testFea.scp
for %%i in (train test) do (
	for /f %%j in (%%iFile.list) do @echo output\feature\%%j.fea
) > output\%%iFea.scp

echo II.2: ���� HMM �˪O�ɮ� template.hmm
rem outMacro.exe P D 3 "1-1-1" MFCC_E 13 > output\template.hmm
outMacro.exe P D 3 1 MFCC_E 13 > output\template.hmm

echo II.3: �ϥ� Flat Start ���� hcompv.hmm    
HCompV -m -o hcompv.hmm -M output -I output\phone.mlf -S output\trainFea.scp output\template.hmm

echo II.4: ���� hcompv.hmm �H���� macro.init
(for /f %%i in (output\modelName.txt) do @sed 's/hcompv.hmm/%%i/g' output\hcompv.hmm) > output\macro.init

echo II.5: �ϥ� mxup.scp �ӭק� macro.init �H���� macro.0
(@echo MU 3 {*.state[2-4].mix}) > output\mxup.scp
copy /y output\macro.init output\hmm\macro.0
HHEd -H output\hmm\macro.0 output\mxup.scp output\modelName.txt

echo II.6: �i�� EM �H���� macro.1~macro.5
:loop 
  set /a current+=1
	set /a prev=current-1
	copy /y output\hmm\macro.%prev% output\hmm\macro.%current%
	set cmd=HERest -H output\hmm\macro.%current% -I output\phone.mlf -S output\trainFea.scp output\modelName.txt
	echo %cmd%
	%cmd%
if not %current%==5 goto :loop

echo III.1: �ϥ� digit.grammar ���� digit.net
Hparse digit.grammar output\digit.net

echo III.2: ����ո�ƤΰV�m��Ʋ��Ϳ��ѵ��G
echo 	���Ѳv���ա]Outside test�^�G���� result_test.mlf
HVite -H output\hmm\macro.5 -l * -i output\result_test.mlf -w output\digit.net -S output\testFea.scp digitMonophone.pam output\modelName.txt
echo 	���Ѳv���ա]Inside test�^�G���� result_train.mlf
HVite -H output\hmm\macro.5 -l * -i output\result_train.mlf -w output\digit.net -S output\trainFea.scp digitMonophone.pam output\modelName.txt

echo III.3: ���� Outside Test �� Inside Test �� Confusion Matrix
findstr /v "sil" output\result_test.mlf > output\result_test_no_sil.mlf
findstr /v "sil" syl.mlf > output\answer.mlf
HResults -p -I output\answer.mlf digitMonophone.pam output\result_test_no_sil.mlf > output\outsideTest.txt
echo 	Confusion matrix of outside test:
type output\outsideTest.txt

findstr /v "sil" output\result_train.mlf > output\result_train_no_sil.mlf
findstr /v "sil" syl.mlf > output\answer.mlf
HResults -p -I output\answer.mlf digitMonophone.pam output\result_train_no_sil.mlf > output\insideTest.txt
echo 	Confusion matrix of inside test:
type output\insideTest.txt