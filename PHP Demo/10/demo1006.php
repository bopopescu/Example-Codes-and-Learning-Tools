<?php
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
//�Ϥ��ͦ�
header("Content-type: image/png");
$im = @imagecreatetruecolor(80, 20) or die("�L�k�إ߹Ϥ��I");
$text_color = imagecolorallocate($im, 255, 255, 255);
imagestring($im, 5, 5, 1,  $new_count, $text_color);
imagepng($im);
imagedestroy($im);
?>
