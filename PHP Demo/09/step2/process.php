<?php
//�ˬd�m�W
if(empty($_POST['username'])){
	die("�S��m�W�O�I�o�ˤ����D�z�O����O�I");
}

//�ˬdEmail
if(empty($_POST['email'])){
	die("�˷R��{$_POST['username']}�A�z�S��Email�O�I�o�ˤ���M�z�p����I");
}elseif(!eregi("^[_.0-9a-z-]+@([0-9a-z-]+.)+[a-z]{2,3}$",$_POST['email'])) {
	die("�˷R��{$_POST['username']}�A�z��Email�����D��I�o�ˤ���M�z�p����I");
}

//�H�󤺮e
$mail_content = "�q�ʪ̡G{$_POST['username']}
�q�ʪ�Email�G{$_POST['email']}
�q�ʪ��~�p�U�G
";

//Ū���ƿ����
foreach($_POST['goods'] as $goods){
	$mail_content .= $goods."\n";
}

//�����e�ɶ�
$order_time=date("Y�~m��d�� H:i:s");
$mail_content .= "�U�q�ɶ��G{$order_time}";

//�H���ۤv
@mail("tadbook5@gmail.com","{$_POST['username']} ���q��",$mail_content) or die("�L�k�H�H�� tadbook5@gmail.com");

//�H���q�ʪ�
@mail($_POST['email'],"�q��T�{",$mail_content)  or die("�L�k�H�H�� {$_POST['email']}");

header("location:result.php?email={$_POST['email']}");
?>
