<?php
session_start();
if($_GET['op']=="big"){
  $_SESSION['fontsize']++;
}elseif($_GET['op']=="small"){
  $_SESSION['fontsize']--;
}elseif(empty($_SESSION['fontsize'])){
  $_SESSION['fontsize']=16;
}

?>
<html>
  <head>
  <title>�r���j�p</title>
  </head>
  <body style='font-size:<?php echo $_SESSION['fontsize'];?>px'>
  <?php echo $_POST['content']?>
  <form action='fontsize.php' method='post'>
  <textarea name="content" rows="5" cols="40"></textarea><br>
  <input type='submit' value='�e�X'>
  </form>
  
  �ثe���r���j�p��<?php echo $_SESSION['fontsize'];?>px 
  |<a href='fontsize.php?op=big'>�դj</a>
  |<a href='fontsize.php?op=small'>�Y�p</a>|
  </body>
</html>
