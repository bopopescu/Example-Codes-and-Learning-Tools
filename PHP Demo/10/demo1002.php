<?php
echo "�ثe�H�ơG".my_counter();

function my_counter(){
	$data="count.txt";
	//���ͷs��
	if(file_exists($data)){
		//Ū���­Ȩå[1�����s��
		$old_count=file_get_contents($data);
		$new_count=$old_count+1;
	}else{
		$new_count=1;
	}
	//�g�J�s�Ȩ�count.txt��

	$fp=fopen($data,"w");
	fwrite($fp,$new_count);
	fclose($fp);

	return $new_count;
}
?>
