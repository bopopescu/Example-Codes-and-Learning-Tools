<?php
$link = @mysql_connect("localhost", "tad", "12345") or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());
mysql_select_db ( "myweb" , $link);
$sql="insert into practice (name,sex,birthday ,salary) values('phebe','�k','1973/03/10',40000)";
if(mysql_query($sql,$link)){
  echo "��Ʒs�W�����I";
}else{
  echo "�L�k����H�U�y�k�G<br>" . $sql;
}
mysql_close($link);
?> 
