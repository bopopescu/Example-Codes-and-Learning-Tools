@echo off

set current=0
:loop 
	set /a prev=current
	set /a current+=1
	copy /y output\hmm\macro.%prev% output\hmm\macro.%current%
	set cmd=HERest -H output\hmm\macro.%current% -I output\digitSylPhone.mlf -S output\trainFea.scp output\digitSyl.mnl
	echo %cmd%
	%cmd%
if not %current%==5 goto :loop

echo III.1: �ϥ� digit.grammar ���� digit.net
Hparse digit.grammar output\digit.net

echo III.2: ����ո�ƤΰV�m��Ʋ��Ϳ��ѵ��G
echo 	���Ѳv���ա]Outside test�^�G���� result_test.mlf
HVite -H output\hmm\macro.5 -l * -i output\result_test.mlf -w output\digit.net -S output\testFea.scp digitSyl.pam output\digitSyl.mnl
echo 	���Ѳv���ա]Inside test�^�G���� result_train.mlf
HVite -H output\hmm\macro.5 -l * -i output\result_train.mlf -w output\digit.net -S output\trainFea.scp digitSyl.pam output\digitSyl.mnl

echo III.3: ���� Outside Test �� Inside Test �� Confusion Matrix
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