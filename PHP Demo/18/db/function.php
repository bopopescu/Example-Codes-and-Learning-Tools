<?php
//�u��C
function toolbar(){
  if(check_user($_SESSION["id"],$_SESSION["passwd"])){
    $main="
    <div class='toolbar'>
    <a href='{$_SERVER['PHP_SELF']}'>�q�T���@��</a>
    <a href='{$_SERVER['PHP_SELF']}?op=add_form'>�s�W���</a>
    <a href='{$_SERVER['PHP_SELF']}?op=search_form'>�j�M</a>
    <a href='{$_SERVER['PHP_SELF']}?op=export'>��Ƴƥ�</a>
    <a href='{$_SERVER['PHP_SELF']}?op=import_form'>��ƶפJ</a>
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
  global $link;
  if(empty($user['id']) or empty($user['passwd']))die("�ж�n�����I");
  if($user['passwd']!=$user['passwd2'])die("�K�X�e���J���@�P��I");
  if(!eregi("[_.0-9a-z-]+@([0-9a-z-]+.)+[a-z]{2,3}$",$user['email']))die("Email�榡�����T��I");
  if(eregi("[^a-zA-Z0-9]",$user['id']))die("�b���u��έ^��Ʀr��I");

  //�Q�ο�J���b���h���o���
  $mem=get_mem_data($user['id']);
  if(!empty($mem['id']))die("���b���w���H�ϥ��o�I");

  if(!get_magic_quotes_gpc()){
    foreach($user as $col=>$val){
      $val=addslashes($val);
      $user[$col]=$val;
    }
  }

  $passwd=md5($user['passwd']);
  $sql="insert into id_passwd (name,email,id,passwd) values('{$user['name']} ' , '{$user['email']}' , '{$user['id']}' , '{$passwd}')";
  mysql_db_query("myweb",$sql,$link) or die("���U��ƵL�k�g�J��I<br>".$sql);
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
  global $link;
  if(empty($the_id))return;
  $sql="select * from id_passwd where id='{$the_id}'";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���o{$the_id}����ơI<br>".$sql);
  $data = mysql_fetch_assoc($result);
  return $data;
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
      $_SESSION["usn"]=$user['sn'];
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
  global $link;
  if(empty($user['id']) or empty($user['passwd']))die("�ж�n�����I");
  if($user['passwd']!=$user['passwd2'])die("�K�X�e���J���@�P��I");
  if(!eregi("[_.0-9a-z-]+@([0-9a-z-]+.)+[a-z]{2,3}$",$user['email']))die("Email�榡�����T��I");
  if(eregi("[^a-zA-Z0-9]",$user['id']))die("�b���u��έ^��Ʀr��I");
  if($_SESSION["id"]!=$user['id'])die("�o���O�z���b����I����ק���I");

  if(!get_magic_quotes_gpc()){
    foreach($user as $col=>$val){
      $val=addslashes($val);
      $user[$col]=$val;
    }
  }
  $passwd=md5($user['passwd']);
  $sql="update id_passwd set name='{$user['name']} ' , email='{$user['email']}' , passwd='{$passwd}' where id='{$user['id']}'";
  mysql_db_query("myweb",$sql,$link) or die("���U��ƵL�k�ק��I<br>".$sql);
  $_SESSION['passwd']=$passwd;
}


/************ �q�T���D�\���� ************/

//�Ψӷs�W�q�T�������
function add_form($sn=""){
  if(!empty($sn)){
    $val=get_data($sn);
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
  global $link;

  foreach($data_array as $col=>$val){
    if(!get_magic_quotes_gpc()){
      $val=addslashes($val);
    }
    ${$col}=$val;
  }
  
  $sql="insert into addrbook (name , email , birthday , tel , mtel , addr , note , usn) values('{$name} ' , '{$email}' , '{$birthday}' , '{$tel}' , '{$mtel}' , '{$addr}' , '{$note} ', '{$_SESSION["usn"]}')";
  mysql_db_query("myweb",$sql,$link) or die("�q�T����ƵL�k�g�J��I<br>".$sql);
  $new_sn=mysql_insert_id();
  
  //�B�z�W�Ǫ��Y��
  if(!empty($_FILES['pic']['name'])){
    $pic=up_pic($new_sn);
    $sql="update addrbook set pic='{$pic}' where sn='{$new_sn}'";
    mysql_db_query("myweb",$sql,$link) or die("�L�k��s�Ϥ��I<br>".$sql);
  }
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
function listall($key=""){
  global $link;
  
  //�B�z����
  $p=(empty($_GET['p']))?1:$_GET['p'];
  $num=7;
  $total=0;
  $start=($p-1)*$num;
  
  $limit=(empty($key))?"limit $start,$num":"";
  $sql="select * from addrbook $limit";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���o�q�T����ơI<br>".$sql);  
  $addr_list="";
  while($db_data = mysql_fetch_row($result)){
    if(!empty($key)){
      $db_data_str=stripslashes(implode(",",$db_data));
      if(!ereg($key,$db_data_str))continue;
      $total++;
    }
    
    list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note,$usn)=$db_data;
    $pic=(empty($pic))?"images/none.gif":_PIC_DIR."/".$pic;
    $note=nl2br($note);
    
    if($_SESSION["id"]==_ROOT or $_SESSION["usn"]==$usn){
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

  if(empty($key)){    
    //���o�`��Ƽ�
    $sql="select count(*) from addrbook";
    $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���o�`��ƼơI<br>".$sql);
    list($total) = mysql_fetch_row($result);
  }
  $n=ceil($total/$num);
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
  �]�����@ $total ���q�T��ơ^
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

  return $main;
}

//���o�Y�@���q�T���
function get_data($the_sn=""){
  global $link;
  if(empty($the_sn))return;
  $sql="select * from addrbook where sn='{$the_sn}'";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���o{$the_sn}����ơI<br>".$sql);
  $data = mysql_fetch_assoc($result);
  return $data;
}

//�ܧ�Y���q�T���
function modify($the_sn=""){
  global $link;
  if(empty($the_sn))return;
  //�B�z�W�Ǫ��Y��
  if(!empty($_FILES['pic']['name'])){
    $pic=up_pic($the_sn);
    $pic_sql="pic='{$pic}' ,";
  }
  $sql="update addrbook set name='{$_POST['data']['name']} ' , email='{$_POST['data']['email']}' , birthday='{$_POST['data']['birthday']}' , {$pic_sql} tel='{$_POST['data']['tel']}' , mtel='{$_POST['data']['mtel']}' , addr='{$_POST['data']['addr']}' , note='{$_POST['data']['note']}' where sn='{$the_sn}'";
  mysql_db_query("myweb",$sql,$link) or die("�L�k�ק�q�T����Ƴ�I<br>".$sql);  
}

/************ �q�T�����[�\���� ************/

//�j�M���
function search_form(){
  $main=<<<FORM
  <form action="{$_SERVER['PHP_SELF']}" method="post">
  <table class="input_table">
  <tr>
  <td class="col_title">�п�J����r�G</td>
  <td class="col"><input type="text" name="key" class="txt"></td>
  <td>
  <input type="hidden" name="op" value="search">
  <input type="submit" value="�j�M" class="input_btn">
  </td>
  </tr>
  </table>
  </form>
FORM;

  return $main;
}

//�ץX���
function export(){
  global $link;
  $sql="select * from addrbook";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���o�Ҧ��q�T����ơI<br>".$sql);
  $content="";
  while($data_array = mysql_fetch_assoc($result)){
    foreach($data_array as $col=>$val){
    if(!get_magic_quotes_runtime()){
      $val=addslashes($val);
    }
    ${$col}=$val;
  }
    $content.="{$sn},\"{$name}\",\"{$email}\",\"{$birthday}\",\"{$pic}\",\"{$tel}\",\"$mtel\",\"$addr\",\"{$note}\",{$usn}\n";
  }
  header("Content-type: text/comma-separated-values");
  header("Content-Disposition: attachment; filename=�q�T�����.csv");
  echo $content;
  exit;
}

//�פJ���
function import_form() {
  $main=<<<FORM
  <form action="{$_SERVER['PHP_SELF']}" method="post" enctype="multipart/form-data">
  <table class="input_table">
  <tr>
  <td class="col_title">�п�ܱ��פJ���ɮסG</td>
  <td class="col"><input type="file" name="file" class="txt"></td>
  </tr>
  <tr>
  <td class="col_title">�п�ܶפJ�Ҧ��G</td>
  <td class="col">
  <input type="radio" name="import_mode" value="a" checked>���[
  <input type="radio" name="import_mode" value="w">�л\
  <input type="hidden" name="op" value="import">
  <input type="submit" value="�}�l�פJ" class="input_btn">
  </td>
  </tr>
  </table>
  </form>
FORM;

  return $main;
}

//�פJ
function import($import_mode="a") {
  global $link;
  if($import_mode=="w"){
    $sql="delete from addrbook";
    mysql_db_query("myweb",$sql,$link) or die("�L�k�M���q�T����Ƴ�I<br>".$sql);
  }
  //���}�����
  $fp = fopen($_FILES['file']['tmp_name'], "r");
  while ((list($sn,$name,$email,$birthday,$pic,$tel,$mtel,$addr,$note,$usn) = fgetcsv($fp, 1000))) {
    if($import_mode=="w"){
      $sql="insert into addrbook (sn,name,email,birthday,pic,tel,mtel,addr,note,usn) values({$sn} , '{$name} ' , '{$email}' , '{$birthday}' , '{$pic}' , '{$tel}' , '{$mtel}' , '{$addr}' , '{$note} ', '{$usn}')";
    }else{
      $sql="insert into addrbook (name,email,birthday,pic,tel,mtel,addr,note,usn) values('{$name} ' , '{$email}' , '{$birthday}' , '{$pic}' , '{$tel}' , '{$mtel}' , '{$addr}' , '{$note} ', '{$usn}')";
    }
    mysql_db_query("myweb",$sql,$link) or die("�q�T����ƵL�k�g�J��I<br>".$sql);
  }
}
?>
