@echo off

set dim=26
set feaType=MFCC_E_D_Z

echo I.1: Generate output directories (���Ϳ�X�ؿ�)
for %%i in (output output\feature output\hmm) do mkdir %%i > nul 2>&1

echo I.2: Generate digitSyl.mnl and digitSylPhone.mlf (���� digitSyl.mnl �� digitSylPhone.mlf)
@echo EX > output\syl2phone.scp
HLEd -n output\digitSyl.mnl -d digitSyl.pam -l * -i output\digitSylPhone.mlf output\syl2phone.scp digitSyl.mlf

echo I.3: Generate wav2fea.scp (���� wav2fea.scp) 
(for /f "delims=" %%i in ('dir/s/b ..\waveFile\*.wav') do @echo %%i output\feature\%%~ni.fea)> output\wav2fea.scp

echo I.4: Acoustic feature extraction using HCopy (�ϥ� HCopy.exe �i��y���S�x���)
HCopy -C mfcc%dim%.cfg -S output\wav2fea.scp

echo II.1: Generate file listings for training and test sets (���ͰV�m��ƩM���ո�ƪ��ɮצC�� trainFea.scp �� testFea.scp)
for %%i in (train test) do (
	for /f %%j in (%%iFile.list) do @echo output\feature\%%j.fea
) > output\%%iFea.scp

echo II.2: Generate HMM template file (���� HMM �˪O�ɮ� template.hmm)
outMacro.exe P D 3 1 %feaType% %dim% > output\template.hmm

echo II.3: Populate HMM template to have hcompv.hmm (���� hcompv.hmm)   
HCompV -m -o hcompv.hmm -M output -I output\digitSylPhone.mlf -S output\trainFea.scp output\template.hmm

echo II.4: Duplicate hcompv.hmm to generate macro.init (���� hcompv.hmm �H���� macro.init)
(for /f %%i in (output\digitSyl.mnl) do @sed 's/hcompv.hmm/%%i/g' output\hcompv.hmm) > output\macro.init

echo II.5: Create more Gaussian components to generate macro.0 (�ϥ� mxup.scp �ӭק� macro.init �H���� macro.0)
(@echo MU 3 {*.state[2-4].mix}) > output\mxup.scp
copy /y output\macro.init output\hmm\macro.0
HHEd -H output\hmm\macro.0 output\mxup.scp output\digitSyl.mnl

echo II.6: Generate macro.1~macro.5 via EM (�i�� EM �H���� macro.1~macro.5)
set current=0
:loop 
	set /a prev=current
	set /a current+=1
	copy /y output\hmm\macro.%prev% output\hmm\macro.%current%
	set cmd=HERest -H output\hmm\macro.%current% -I output\digitSylPhone.mlf -S output\trainFea.scp output\digitSyl.mnl
	echo %cmd%
	%cmd%
if not %current%==5 goto :loop

echo III.1: Use digit.grammar to generate digit.net (�ϥ� digit.grammar ���� digit.net)
Hparse digit.grammar output\digit.net

echo III.2: Compute recognition rates for both inside and outside tests (����ո�ƤΰV�m��Ʋ��Ϳ��ѵ��G)
echo 	Outside test: Generating result_test.mlf...
HVite -H output\hmm\macro.5 -l * -i output\result_test.mlf -w output\digit.net -S output\testFea.scp digitSyl.pam output\digitSyl.mnl
echo 	Inside test: Generating result_train.mlf
HVite -H output\hmm\macro.5 -l * -i output\result_train.mlf -w output\digit.net -S output\trainFea.scp digitSyl.pam output\digitSyl.mnl

echo III.3: Generate confusion matrices for both inside and outside tests (���� Outside Test �� Inside Test �� Confusion Matrix)
findstr /v "sil" output\result_test.mlf > output\result_test_no_sil.mlf
findstr /v "sil" digitSyl.mlf > output\answer.mlf
HResults -p -I output\answer.mlf digitSyl.pam output\result_test_no_sil.mlf > output\outsideTest.txt
echo 	Confusion matrix of outside test:
type output\outsideTest.txt

findstr /v "sil" output\result_train.mlf > output\result_train_no_sil.mlf
findstr /v "sil" digitSyl.mlf > output\answer.mlf
HResults -p -I output\answer.mlf digitSyl.pam output\result_train_no_sil.mlf > output\insideTest.txt
echo 	Confusion matrix of inside test:
type output\insideTest.txt