<?php
include "demo2003.php";

$ss=new shopping();
$ss->set_price(150);
$ss->buy("�W��",3);
$ss->buy("�~�M");
$ss->buy("���J��",2);
$ss->buy("�~�M",2);
echo $ss->showlist("�A���M�橱�ʪ��M��");

$ss2=new shopping_pro();
$ss2->set_price(150);
$ss2->buy("�W��",3);
$ss2->buy("�~�M");
$ss2->buy("���J��",2);
$ss2->buy("�~�M",2);
echo $ss2->showlist("�A���M�橱�ʪ��M��");
?>
