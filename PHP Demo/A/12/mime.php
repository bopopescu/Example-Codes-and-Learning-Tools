<html>
  <head>
  <title>MIME�d��</title>
  </head>
  <body>
  <form enctype="multipart/form-data" action="mime.php" method="post">
  <input type="file" name="file"><input type="submit" value="�d��">                        
  </form>
  <?php
  if(!empty($_FILES['file']['name'])){
    echo $_FILES['file']['name']."���ɮ�MIME�������G".$_FILES['file']['type'];
  }
  ?>
  </body>
</html>
