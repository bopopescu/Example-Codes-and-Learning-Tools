<?php
echo today();
function today(){
  $cw=array("��","�@","�G","�T","�|","��","��");
  $y=date("Y")-1911;
  $w=date("w");
  $date=date("m��d��");
  return "����{$y}�~{$date}�P��{$cw[$w]}";
}
?>
