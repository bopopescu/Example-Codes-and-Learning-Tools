<?php
$link = @mysql_connect("localhost", "tad", "12345") or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

$sql="select * from practice";
$result=mysql_db_query("myweb",$sql,$link);
while(list($num,$name,$sex,$birthday,$salary)=mysql_fetch_row($result)){
  echo "{$name}�O{$sex}�͡A�ͤ�O{$birthday}<br>";
}
mysql_close($link);
?> 
