<?php
echo "�ثe�H�ơG".my_counter();

function my_counter(){
	$data="count.txt";
	//���ͷs��
	if(file_exists($data)){
		//Ū���­Ȩå[1�����s��
		$fp=fopen($data,"r");
		$old_count=fread($fp,filesize($data));
		$new_count=$old_count+1;
		fclose($fp);
	}else{
		$new_count=1;
	}
	//�g�J�s�Ȩ�count.txt��
	$fp=fopen($data,"w");
	fwrite($fp,$new_count);
	fclose($fp);

	$pic="";
	//�}�l�q$new_count�^���X�C�@�ӼƦr
	for($i=0;$i<strlen($new_count);$i++){
		$img_num=substr($new_count,$i,1);
		$pic.="<img src='{$img_num}.png' border=0>";
	}

	return $pic;
}
?>
