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
	file_put_contents($data,$new_count);

	return $new_count;
}
?>
