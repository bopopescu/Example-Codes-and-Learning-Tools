<?php
include "demo2001.php";

$ss=new shopping();
$ss->buy("�����",2);
$ss->buy("ñ�r��",2);
$ss->buy("����");
echo $ss->showlist();

$ss2=new shopping();
$ss2->set_price(150);
$ss2->buy("�W��",3);
$ss2->buy("�~�M");
$ss2->buy("���J��",2);
$ss2->buy("�~�M",2);
echo $ss2->showlist("�A���M�橱�ʪ��M��");
?>
