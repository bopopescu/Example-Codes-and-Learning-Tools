<?php
switch($_REQUEST['op']){
case "result":
  $main=result();
  break;
default:
  $main=form();

}

function form(){
  $num1=rand(1,99);
  $num2=rand(1,99);
  $main="<form action='exam.php' method='post'>
  {$num1} �� {$num2} = <input type='text' name='user_ans' size=4>
  <input type='hidden' name='n1' value='{$num1}'>
  <input type='hidden' name='n2' value='{$num2}'>
  <input type='hidden' name='op' value='result'>
  <input type='submit' value='�ݵ���'>
  </form>";
  return $main;
}

function result(){
  $ans=$_POST['n1']*$_POST['n2'];
  $main="���T�ѵ��G{$_POST['n1']} �� {$_POST['n2']} = {$ans}<br>";
  if($_POST['user_ans']==$ans){
    $main.= "�z����F�o�I�u�O�F�`�I";
  }else{
    $main.="�z�����o�I���רä��O {$_POST['user_ans']} ��I";
  }
  return $main;
}
?>

<html>
  <head>
  <title>�ƾǽm��</title>
  </head>
  <body>
  <?php echo $main;?>
  </body>
</html>
