<?php
$link = @mysql_connect("localhost", "tad", "12345") or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

$sql="insert into practice (name,sex,birthday ,salary) values('sherry','�k','1973/05/15',50000)";
if(mysql_db_query("myweb",$sql,$link)){
  $new_num=mysql_insert_id($link);
  echo "��Ʒs�W�����I��s�����G{$new_num}";
}else{
  echo "�L�k����H�U�y�k�G<br>" . $sql;
}
mysql_close($link);
?> 
