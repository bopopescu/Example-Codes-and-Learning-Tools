<?php
if($sex=="boy"){
  if($age < 18){
    echo "�µ������Q�K�������Y�p�l";
  }elseif($age > 40){
    echo "�z�W�L���Ĵ����F�I";
  }else{
    if($status=="���f" or $status=="�i�פ�" or $status=="�w�h��"){
      echo "���η�L�u�n";
    }else{
      echo "�ǳƬ���ĩR�a�I";
    }
  }
}elseif($sex=="girl"){
  echo "�k�ͤ���ݳ�I";
}
?>
