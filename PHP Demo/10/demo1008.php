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
$im = @imagecreatefrompng ("counter_bg.png") or die("�L�k�إ߹Ϥ��I");
$text_color = imagecolorallocate($im, 0, 0, 0);
imagettftext($im, 20, 0, 100, 40, $text_color, "C:\WINDOWS\Fonts\arial.ttf",$new_count);
$web_name=iconv("Big5","UTF-8","TAD ���ۻs PHP �ϧέp�ƾ�");
$web_name_color1 = imagecolorallocate($im, 255, 255, 0);
$web_name_color2 = imagecolorallocate($im, 0, 0, 0);
imagettftext($im, 9, 0, 81, 66, $web_name_color2, "C:\WINDOWS\Fonts\mingliu.ttc",$web_name);
imagettftext($im, 9, 0, 80, 65, $web_name_color1, "C:\WINDOWS\Fonts\mingliu.ttc",$web_name);
imagepng($im);
imagedestroy($im);
?>
