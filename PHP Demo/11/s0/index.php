<?php
//�ޤJ�ɮװϡ]�ݻݭn�ޤJ�ƻ��ɡA���b�����ޤJ�^
include "setup.php";
include "function.php";

//�y�{����ϡ]�P�_�ϥΪ̭n�����ʧ@�A�h�I�s��������ƩΪ����k�^
switch ($_REQUEST['op']) {
case "register_form":
 $main_content = register_form();
	break;
default:
  $main_content = login_form();
  break;
}

//�e�{�e���ϡ]�Y���ʧ@�O�ݭn�e�{�b�e���W���A����Τ@�b����X�^
?>

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=Big5">
<link rel="stylesheet" type="text/css" media="screen" href="style.css">
<title>�ڪ��q�T��</title>
</head>
<body background="images/bg.gif">

<div class="center_block">
  <img src="images/title.png" class="logo">
  <?php
  echo toolbar();
  echo $main_content;
  ?>
</div>
<div class="copyright">Powered by Tad (tadbook5@gmail.com) (c) 2001-2005</div>
</body>
</html>
