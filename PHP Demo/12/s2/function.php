<?php
//�u��C
function toolbar(){
  if(check_user($_SESSION["id"],$_SESSION["passwd"])){
    $main="
    <div class='toolbar'>
    <a href='{$_SERVER['PHP_SELF']}'>�q�T���@��</a>
		<a href='{$_SERVER['PHP_SELF']}?op=add_form'>�s�W���</a>
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


/************ �q�T���D�\���� ************/

//�Ψӷs�W�q�T�������
function add_form($sn=""){
  if(!empty($sn)){
    $val_array=get_data($sn);
    if(get_magic_quotes_gpc()){
      foreach($val_array as $k=>$v){
        $val[$k]=stripslashes($v);
      }
    }else{
      $val=$val_array;
    }
    $op="modify";
    $hidden_col="
    <input type='hidden' name='sn' value='{$sn}'>
    <input type='hidden' name='p' value='{$_GET['p']}'>";
  }else{
    $op="add";
  }
  $main=<<<FORM
  <form action="{$_SERVER['PHP_SELF']}" method="post" enctype="multipart/form-data">
  <table class="input_table">
  <tr>
  <td colspan="4" class="col_head_title">�s��q�T���</td>
  </tr>
  <tr>
  <td class="col_title">�n�ͩm�W�G</td><td class="col"><input type="text" name="data[name]" value="{$val['name']}" class="txt"></td>
  <td class="col_title">�q�l�l��G</td><td class="col"><input type="text" name="data[email]" value="{$val['email']}" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�ͤ����G</td><td class="col"><input type="text" name="data[birthday]" value="{$val['birthday']}" class="txt"></td>
  <td class="col_title">�ӤH�Y���G</td><td class="col"><input type="file" name="pic"></td>
  </tr>
  <tr>
  <td class="col_title">�q�T�q�ܡG</td><td class="col"><input type="text" name="data[tel]" value="{$val['tel']}" class="txt"></td>
  <td class="col_title">��ʹq�ܡG</td><td class="col"><input type="text" name="data[mtel]" value="{$val['mtel']}" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�p���a�}�G</td><td colspan="3"><input type="text" name="data[addr]" value="{$val['addr']}" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">��L�T���G</td><td colspan="2"><textarea name="data[note]" rows=3>{$val['note']}</textarea></td>
  <td align="center">
  <input type="hidden" name="op" value="{$op}">
  $hidden_col
  <input type="submit" value="�x�s" class="input_btn">
  </td>
  </tr>
  </table>
  </form>
FORM;

  return $main;
}

//�s�W�@���q�T���
function add($data_array=array()){
  $fp = fopen (_DATA_FILE, "a+");

  //���o�ثe�̤j���s��
  while ((list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note) = fgetcsv($fp, 1000))) {
    if(!empty($sn))$i=$sn;
  }
  $new_sn=$i+1;

  //�B�z�W�Ǫ��Y��
  if(!empty($_FILES['pic']['name'])){
    $pic_name=up_pic($new_sn);
  }else{
    $pic_name="";
  }

  foreach($data_array as $col=>$val){
    if(!get_magic_quotes_gpc()){
      $val=addslashes($val);
    }
    ${$col}=$val;
  }

  $content.="{$new_sn},\"{$name}\",\"{$email}\",\"{$birthday}\",\"{$pic_name}\",\"{$tel}\",\"{$mtel}\",\"{$addr}\",\"{$note}\"\n";

  fwrite ($fp, $content,strlen($content));
  fclose($fp);
}

//�B�z�W�ǹϤ�
function up_pic($sn=""){
  if(empty($sn))die("�S���ɮ׽s���O�I");
  mk_dir(_PIC_DIR);
  $pic_name="{$sn}_{$_FILES['pic']['name']}";
  $uploadfile=_PIC_DIR."/{$pic_name}";
  move_uploaded_file($_FILES['pic']['tmp_name'], $uploadfile);
  return $pic_name;
}

//�إߥؿ�
function mk_dir($dir=""){
  //�Y�L�ؿ��W�٨q�Xĵ�i�T��
  if(empty($dir))die("�L�ؿ��W��");
  //�Y�ؿ����s�b���ܫإߥؿ�
  if (!is_dir($dir)) {
    umask(000);
    //�Y�إߥ��Ѩq�Xĵ�i�T��
    if(!mkdir($dir, 0777))die("�L�k�إߥؿ�");
  }
}

//�q�X�X�q�T��
function listall(){
  //�B�z����
  $p=(empty($_GET['p']))?1:$_GET['p'];
  $num=7;
  $i=0;
  $start=($p-1)*$num+1;
  $end=$p*$num;

  //���}�����
  if(file_exists(_DATA_FILE)){
    $fp = fopen(_DATA_FILE, "r");
  }else{
    $fp = fopen(_DATA_FILE, "w+");
  }

  $addr_list="";
  //�Q��fgetcsv()��Ū�����e
  while (($csvdata = fgetcsv($fp, 1000))) {

    $i++;
    if($i<$start or $i>$end){
      continue;
    }

    list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note)=$csvdata;

    $pic=(empty($pic))?"images/none.gif":_PIC_DIR."/".$pic;
    $name=stripslashes($name);
    $addr=stripslashes($addr);
    $note=nl2br(stripslashes($note));

    if($_SESSION["id"]==_ROOT or $_SESSION["email"]==$email){
      $admin_tool="
      <a href='{$_SERVER['PHP_SELF']}?op=edit&p={$p}&sn={$sn}'>�s��</a><br>
      <a href='javascript:delete_data($sn,$p)'>�R��</a>";
    }else{
      $admin_tool="";
    }

    $addr_list.="<tr class='view'>
    <td rowspan='2' class='func'>
    $admin_tool
    </td>
    <td rowspan='2' valign='top'><img src='{$pic}'></td>
    <td rowspan='2' style='font-size:16px' nowrap align='center'>$name<br>
    <font class='eng'>$birthday</font></td>
    <td class='eng'><a href='mailto:$email'>$email</a></td>
    <td class='eng'>$tel</td>
    <td class='eng'>$mtel</td>
    <td rowspan='2' valign='top'>$note</td>
    </tr>
    <tr>
    <td colspan='3'>$addr</td>
    </tr>";
  }

  $n=ceil($i/$num);
  $page_list="<select onChange=\"if(this.value!='') location.href = '{$_SERVER['PHP_SELF']}?p=' + this.value\">";
  for($a=1;$a<=$n;$a++){
    $selected=($p==$a)?"selected":"";
    $page_list.="<option value='{$a}' $selected>�� $a ��</option>";
  }
  $page_list.="</select>";

  $next_page=$p+1;
  $previous_page=$p-1;

  $nav="
  <div class='nav'>
  �]�����@ $i ���q�T��ơ^
  <a href='{$_SERVER['PHP_SELF']}?p={$previous_page}'>�W�@��</a>
  $page_list
  <a href='{$_SERVER['PHP_SELF']}?p={$next_page}'>�U�@��</a>
  </div>";

  $main=<<<LIST_ALL
  <script language="JavaScript" type="text/JavaScript">
  <!--
  //�R���T�{
  function delete_data(sn, p){
    var sure = window.confirm('�T�w�n�R������ơH');
    if (!sure)	return;
    location.href="{$_SERVER['PHP_SELF']}?op=del&p="+p+"&sn="+sn;
  }
  //-->
  </script>
  $nav
  <table class="list">
  <tr align="center">
  <th nowrap>�\��</th>
  <th>�Y��</th>
  <th>�m�W</th>
  <th>Email</th>
  <th>�p���q��</th>
  <th>��ʹq��</th>
  <th>�Ƶ�</th>
  </tr>
  $addr_list
  </table>
  $nav
LIST_ALL;

  fclose($fp);
  return $main;
}

//���o�Y�@���q�T���
function get_data($the_sn=""){
  if(empty($the_sn))return;
  $fp = fopen(_DATA_FILE, "r");
  while ((list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note) = fgetcsv($fp, 1000))) {
    if($the_sn==$sn){
      return array("sn"=>$sn,"name"=>$name,"email"=>$email,"birthday"=>$birthday,"pic"=>$pic,"tel"=>$tel,"mtel"=>$mtel,"addr"=>$addr,"note"=>$note);
    }else{
      continue;
    }
  }
  fclose($fp);
}

//�ܧ�Y���q�T���
function modify($the_sn=""){
  if(empty($the_sn))return;

  $fp = fopen (_DATA_FILE, "r");
  $content="";
  while ((list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note) = fgetcsv($fp, 1000))) {
    if($sn==$the_sn){
      if($_REQUEST['op']=="del") continue;

      foreach($_POST['data'] as $col=>$val){
        if(!get_magic_quotes_gpc()){
          $val=addslashes($val);
        }
        ${$col}=$val;
      }

      //�B�z�W�Ǫ��Y��
      if(!empty($_FILES['pic']['name'])){
        $pic=up_pic($the_sn);
      }
    }
    $content.="{$sn},\"{$name}\",\"{$email}\",\"{$birthday}\",\"{$pic}\",\"{$tel}\",\"{$mtel}\",\"{$addr}\",\"{$note}\"\n";
  }
  fclose($fp);

  $fp = fopen (_DATA_FILE, "w");
  fwrite ($fp, $content,strlen($content));
  fclose($fp);
}
?>
