<?php
$link = @mysql_connect("localhost", "tad", "12345") or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

$sql="select * from practice";
$result=mysql_db_query("myweb",$sql,$link);
while($data=mysql_fetch_array($result)){
  echo "{$data['name']}�O{$data['sex']}�͡A�ͤ�O{$data['birthday']}<br>";
}
mysql_close($link);
?> 
