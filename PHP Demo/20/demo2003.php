<?php
include "demo2001.php";

class shopping_pro extends shopping{  
  //��X�ʪ��M��
  function showlist($title="�ʪ��M��"){    
    $total=0;
    $all_num=0;
    $list="<h3>{$title}</h3><hr>";
    foreach($this->products as $things => $num){
      $money= $num * $this->price;
      $list.="<li>�u{$things}�v�ѡu{$num}�v�� = {$money} ��";
      $total+=$money;
      $all_num+=$num;
    }
    
    if($all_num>=8){
      $new_money = $total * 0.8;
      $list.="<hr>�ѩ�R�F 8 ��H�W�A��� {$total} �����K��A�u�f�� {$new_money} ��";
    }else{
      $list.="<hr>�@�p {$total} ��";
    }
    $list.="<br>���b����G{$this->date}";
    return $list;
  }
}
?>
