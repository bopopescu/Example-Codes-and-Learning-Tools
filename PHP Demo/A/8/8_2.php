<?php
echo today(1973,6,16);
function today($year="",$mon="",$day=""){
  $cw=array("��","�@","�G","�T","�|","��","��");
  $y=$year-1911;
  $w=date("w",mktime(0,0,0,$mon,$day,$year));
  $date=date("m��d��",mktime(0,0,0,$mon,$day,$year));
  return "����{$y}�~{$date}�P��{$cw[$w]}";
}
?>
