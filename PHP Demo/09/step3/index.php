<?php
switch($_REQUEST['op']){
	case "process":
	//�i���ƳB�z
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

	header("location:index.php?op=result&email={$_POST['email']}");
	break;

	case "result":
	//��ܵ��G
	echo <<<RESULT
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=Big5">
	<link rel="stylesheet" type="text/css" media="screen" href="style.css">
	<title>�q�ʵ��G</title>
</head>
<body>
<div class="order_form">
�w�N�q��H�X�I�z�]�i�H�� {$_GET['email']} �����T�{�q��q���A���±z�����{�I
</div>
</body>
</html>
RESULT;
	break;

	default:
	//��ܪ��
	echo <<<FORM
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=Big5">
	<link rel="stylesheet" type="text/css" media="screen" href="style.css">
	<title>²���q��</title>
</head>
<body>
<div class="order_form">
	<form action="index.php" method="post">
	<div class="order_col">�п�J�m�W�G<input type="text" name="username"></div>
	<div class="order_col">�п�JEmail�G<input type="text" name="email"></div>
	<div class="order_col">
	�п�ܱz�n�q�ʪ����~�G<br>
	<input type="checkbox" name="goods[]" value="12�T PowerBooK">
	12�T PowerBooK NT 56,900 ��<br>
	<input type="checkbox" name="goods[]" value="14�T PowerBooK">
	14�T PowerBooK NT 74,900 ��<br>
	<input type="checkbox" name="goods[]" value="15�T PowerBooK">
	15�T PowerBooK NT 92,900 ��<br>
	</div>
	<input type="hidden" name="op" value="process">
	<input type="submit" value="�q��">
	</form>
</div>
</body>
</html>
FORM;
}
?>
