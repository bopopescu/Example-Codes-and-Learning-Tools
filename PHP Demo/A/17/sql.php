<?php
$link = @mysql_connect("localhost", "tad", "12345") or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

//���J�@����Ƭ��G�u�b2001/1/1�o�@�ѡA�o��tim�~��1500���v
$sql="insert into money (name,salary,date) values('tim','1500','2001/1/1')";
mysql_db_query("myweb",$sql,$link);

//�P�ɴ��J�p�U��ơG�u�b2001/1/15�o�@�ѡA�o��bee�~��1500���v�B�u�b2001/2/1�o�@�ѡA�o��frog�~��5000���v�B�u�b2001/3/1�o�@�ѡA�o��apple�~��2000���v
$sql="insert into money (name,salary,date) values('bee','1500',2001/1/15') , ('frog','5000','2001/2/1') , ('apple','2000','2001/3/1')";
mysql_db_query("myweb",$sql,$link);

//Ū�Xfrog���~�����h�֡H
$sql="select salary from money where name='frog'";
$result=mysql_db_query("myweb",$sql,$link);
while(list($salary)=mysql_fetch_row($result)){
  echo "frog���~����{$salary}<br>";
}

//�C�X�C�ӤH�~���̰��@��������H���~���H 
$sql="select name,date,max(salary) from money group by name";
$result=mysql_db_query("myweb",$sql,$link);
while(list($name,$date,$salary)=mysql_fetch_row($result)){
  echo "{$name}���~���̰��O{$date}��{$salary}<br>";
}

//�H����Ƨǡ]�ѻ��ܪ�^�̧ǦC�X����B�m�W�B�~�������
$sql="select date,name,salary from money order by date";
$result=mysql_db_query("myweb",$sql,$link);
while(list($date,$name,$salary)=mysql_fetch_row($result)){
  echo "{$date}{$name}���~���O{$salary}<br>";
}

//�N�~���Ѱ��ܧC�ƦC�A�̧ǦC�X�m�W�B�~���A�öȦC�X�|�����
$sql="select name,salary from money order by salary desc limit 0,4";
$result=mysql_db_query("myweb",$sql,$link);
while(list($name,$salary)=mysql_fetch_row($result)){
  echo "{$name}���~���O{$salary}<br>";
}


//��s��5����ơA�ñN�~��אּ2000�A����אּ2001/8/15
$sql="update money set salary='2000',date='2001/8/15' where serial=5";
mysql_db_query("myweb",$sql,$link);

//�R���m�W��tad�A�B�~���C��2000����Ƹ��
$sql="delete from money where name='tad' and salary<2000";
mysql_db_query("myweb",$sql,$link);

mysql_close($link);
?> 
