<?php
//�u��C
function toolbar(){
  if(check_user($_SESSION["id"],$_SESSION["passwd"])){
    $main="
    <div class='toolbar'>
    <a href='{$_SERVER['PHP_SELF']}?op=profile'>�b���]�w</a>
    <a href='{$_SERVER['PHP_SELF']}?op=logout'>�n�X</a>
    </div>";
  }else{
    $main="
    <div class='toolbar'>
    <a href='{$_SERVER['PHP_SELF']}?op=register_form'>���U</a>
    <a href='{$_SERVER['PHP_SELF']}?op=login_form'>�n�J</a>
    </div>";
  }
  return $main;
}

//���U���
function register_form($the_id=""){
  if(!empty($the_id)){
    $op="modify_profile";
    $readonly="readonly";
    $mem=get_mem_data($the_id);

    foreach($mem as $col=>$val){
      $val=stripslashes($val);
      $mem[$col]=$val;
    }
  }else{
    $op="register";
    $readonly="";
  }
  $main=<<<FORM
  <form action="{$_SERVER['PHP_SELF']}" method="post">
  <table class="input_table">
  <tr>
  <td class="col_title">�z���m�W�G</td>
  <td class="col"><input type="text" name="reg[name]" value="{$mem['name']}" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�q�l�l��G</td>
  <td class="col"><input type="text" name="reg[email]" value="{$mem['email']}" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�]�w�b���G</td>
  <td class="col"><input type="text" name="reg[id]" value="{$mem['id']}" class="txt" $readonly></td>
  </tr>
  <tr>
  <td class="col_title">�]�w�K�X�G</td>
  <td class="col"><input type="password" name="reg[passwd]" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�T�{�K�X�G</td>
  <td class="col"><input type="password" name="reg[passwd2]" class="txt"></td>
  </tr>
  <td colspan="2" align="center">
  <input type="hidden" name="op" value="{$op}">
  <input type="submit" value="���U" class="input_btn">
  </td>
  </tr>
  </table>
  </form>
FORM;

  return $main;
}

//���U
function register($user=array()){
  if(empty($user['id']) or empty($user['passwd']))die("�ж�n�����I");
  if($user['passwd']!=$user['passwd2'])die("�K�X�e���J���@�P��I");
  if(!eregi("[_.0-9a-z-]+@([0-9a-z-]+.)+[a-z]{2,3}$",$user['email']))die("Email�榡�����T��I");
  if(eregi("[^a-zA-Z0-9]",$user['id']))die("�b���u��έ^��Ʀr��I");

  //�Q�ο�J���b���h���o���
  $mem=get_mem_data($user['id']);
  if(!empty($mem['id']))die("���b���w���H�ϥ��o�I");

  $fp = fopen (_MEM_FILE, "a+") or die("�L�k�}�� "._MEM_FILE." �ɮ�");
  //���o�ثe�̤j���s��
  while ((list($sn,$name,$email,$id,$passwd) = fgetcsv($fp, 1000))) {
    if(!empty($sn))$i=$sn;
  }
  $new_sn=$i+1;

  if(!get_magic_quotes_gpc()){
    foreach($user as $col=>$val){
      $val=addslashes($val);
      $user[$col]=$val;
    }
  }

  $passwd=md5($user['passwd']);
  $content="{$new_sn},\"{$user['name']}\",\"{$user['email']}\",\"{$user['id']}\",\"{$passwd}\"\n";

  fwrite ($fp, $content,strlen($content));
  fclose($fp);
}

//�n�J���
function login_form(){
  $main=<<<FORM
  <form action="{$_SERVER['PHP_SELF']}" method="post">
  <table class="input_table">
  <tr>
  <td class="col_title">�b���G</td>
  <td class="col"><input type="text" name="id" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�K�X�G</td>
  <td class="col"><input type="password" name="passwd" class="txt"></td>
  </tr>
  <td colspan="2" align="center">
  <input type="hidden" name="op" value="login">
  <input type="submit" value="�n�J" class="input_btn">
  </td>
  </tr>
  </table>
  </form>
FORM;

  return $main;
}

//���o�Y�H�����
function get_mem_data($the_id="") {
  if(empty($the_id))return;
  $fp = fopen(_MEM_FILE, "r") or die("�L�k�}�� "._MEM_FILE." �ɮ�");
  while ((list($sn,$name,$email,$id,$passwd) = fgetcsv($fp, 1000))) {
    if($the_id==$id){
      return array("sn"=>$sn,"name"=>$name,"email"=>$email,"id"=>$id,"passwd"=>$passwd);
    }else{
      continue;
    }
  }
  fclose($fp);
}

//�����T�{
function check_user($id="",$passwd="",$md5=false){
  if(empty($id) or empty($passwd))return false ;
  if($md5)$passwd=md5($passwd);
  $user=get_mem_data($id);
  if($user['id']==$id and $user['passwd']==$passwd){
    if(empty($_SESSION["id"])){
      $_SESSION["id"]=$id;
      $_SESSION["passwd"]=$passwd;
      $_SESSION["email"]=$user['email'];
    }
    return true;
  }
  return false;
}

//�q�X�Ҧ��q�T�����e
function listall(){
  return "�q�T�����e�]�s�@��...�^";
}

//�n�X
function logout(){
	$_SESSION = array();
	session_destroy();
}

//�ק���U���
function modify_profile($user=array()){
  if(empty($user['id']) or empty($user['passwd']))die("�ж�n�����I");
  if($user['passwd']!=$user['passwd2'])die("�K�X�e���J���@�P��I");
  if(!eregi("[_.0-9a-z-]+@([0-9a-z-]+.)+[a-z]{2,3}$",$user['email']))die("Email�榡�����T��I");
  if(eregi("[^a-zA-Z0-9]",$user['id']))die("�b���u��έ^��Ʀr��I");
  if($_SESSION["id"]!=$user['id'])die("�o���O�z���b����I����ק���I");

  $fp = fopen (_MEM_FILE, "r");
  $content="";
  while ((list($sn,$name,$email,$id,$passwd) = fgetcsv($fp, 1000))) {
    if($id==$user['id']){
      if(!get_magic_quotes_gpc()){
        foreach($user as $col=>$val){
          $val=addslashes($val);
          $user[$col]=$val;
        }
      }
      $passwd=md5($user['passwd']);
      $content.="{$sn},\"{$user['name']}\",\"{$user['email']}\",\"{$id}\",\"{$passwd}\"\n";
      $_SESSION['passwd']=$passwd;
      $_SESSION['email']=$user['email'];
    }else{
      $content.="{$sn},\"{$name}\",\"{$email}\",\"{$id}\",\"{$passwd}\"\n";
    }
  }
  fclose($fp);

  $fp = fopen (_MEM_FILE, "w");
  fwrite ($fp, $content,strlen($content));
  fclose($fp);
}
?>
