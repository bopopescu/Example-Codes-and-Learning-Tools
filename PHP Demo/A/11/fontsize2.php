<?php
session_start();
if($_GET['op']=="big"){
  $_SESSION['fontsize']++;
}elseif($_GET['op']=="small"){
  $_SESSION['fontsize']--;
}elseif(empty($_SESSION['fontsize'])){
  $_SESSION['fontsize']=16;
}

if(!empty($_POST['content']))$_SESSION['content']=$_POST['content'];
?>
<html>
  <head>
  <title>�r���j�p</title>
  </head>
  <body style='font-size:<?php echo $_SESSION['fontsize'];?>px'>
  <?php echo $_SESSION['content']?>
  <form action='fontsize2.php' method='post'>
  <textarea name="content" rows="5" cols="40"></textarea><br>
  <input type='submit' value='�e�X'>
  </form>
  
  �ثe���r���j�p�� <?php echo $_SESSION['fontsize'];?>px  
  |<a href='fontsize2.php?op=big'>�դj</a>
  |<a href='fontsize2.php?op=small'>�Y�p</a>|
  </body>
</html>
