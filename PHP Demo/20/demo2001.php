<?php
class shopping{
  var $products;
  var $price=10;
  var $date;
  
  //�غc���
  function shopping(){
    $this->date=date("Y�~m��d��H��i��s��");
  }
  
  //�R�F��
  function buy($thing="",$num=1){
    $this->products[$thing]+=$num;
  }
  
  //���]����
  function set_price($new_price=""){
    $this->price=$new_price;
  }
  
  //��X�ʪ��M��
  function showlist($title="�ʪ��M��"){    
    $total=0;
    $list="<h3>{$title}</h3><hr>";
    foreach($this->products as $things => $num){
      $money= $num * $this->price;
      $list.="<li>�u{$things}�v�ѡu{$num}�v�� = {$money} ��";
      $total+=$money;
    }
    $list.="<hr>�@�p {$total} ��<br>���b����G{$this->date}";
    return $list;
  }
}
?>
