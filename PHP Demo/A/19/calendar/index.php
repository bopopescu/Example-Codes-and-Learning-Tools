<?php
$link = @mysql_connect("localhost", "tad", "12345")  or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

//�y�{����ϡ]�P�_�ϥΪ̭n�����ʧ@�A�h�I�s��������ƩΪ����k�^
switch ($_REQUEST['op']) {
case "input_form":
  $main=input_form();
  break;
case "add_event":
  $date=add_event();
  header("location: {$_SERVER['PHP_SELF']}?op=show_event&date={$date}");
  break;
case "show_event":
  $main=show_event($_GET['date']);
  break;
case "modify_event":
  $main=input_form($_GET['sn']);
  break;
case "del_event":
  del_event($_GET['sn']);
  header("location: {$_SERVER['PHP_SELF']}");
  break;
default:
  $main=list_event();
  break;
}

//�e�{�e���ϡ]�Y���ʧ@�O�ݭn�e�{�b�e���W���A����Τ@�b����X�^
echo make_page($main);

//��ưϡ]�Ҧ�function�Ҹm�󦹳B�^

//�e���s�@��
function make_page($main){
  $page="
  <html>
  <head>
  <meta http-equiv='content-type' content='text/html; charset=Big5'>
  <link rel='stylesheet' type='text/css' media='screen' href='style.css'>
  <title>�ڪ��ƥ�</title>
  <script language='JavaScript' type='text/JavaScript'>
  <!--
  function delete_data(sn){
    var sure = window.confirm('�T�w�n�R������ơH');
    if (!sure)	return;
    location.href='{$_SERVER['PHP_SELF']}?op=del_event&sn=' + sn;
  }
  //-->
  </script>
  </head>
  <body background='images/bg.gif'>
  
  <div class='center_block'>
    <img src='images/logo.png' class='logo'>
    <div class='toolbar'>
      <a href='{$_SERVER['PHP_SELF']}?op=input_form'>�s�W�ƥ�</a>
      <a href='{$_SERVER['PHP_SELF']}'>�ƥ��`��</a>
    </div>
    $main
  </div>
  <div class='copyright'>Powered by Tad (tadbook5@gmail.com) (c) 2001-2005</div>
  </body>
  </html>";
  
  return $page;
}

//��J�ƥ󪺤���
function input_form($sn=""){
  global $link;
  if(!empty($sn)){
    $sql="select * from calendar where sn='$sn'";
    $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���ƥ��ơI<br>".$sql);
    $event= mysql_fetch_assoc($result);
    $start_time=$event['start_time'];
    $end_time=$event['end_time'];
    $event_title=$event['event_title'];
    $sn_col="<input type='hidden' name ='sn' value='{$event['sn']}'>";
  }else{
    $event_title="";
    $d=explode("-",$_GET['date']);
    $start_time=(empty($_GET['date']))?date("Y-m-d H:i:s"):$_GET['date'].date(" H:i:s",mktime(8,0,0,$d[1],$d[2],$d[0]));
    $end_time=(empty($_GET['date']))?date("Y-m-d H:i:s"):$_GET['date'].date(" H:i:s",mktime(15,0,0,$d[1],$d[2],$d[0]));
    $sn_col="";
  }
      
  $main="<form action='{$_SERVER['PHP_SELF']}' method='post'>
  �ƥ���D�G<input type='text' name ='event_title' value='{$event_title}' class='input'>
  <textarea name='event_content' cols=20 rows=10>{$event['event_content']}</textarea>
  $sn_col
  <br>
  �_�l�ɶ��G<input type='text' name ='start_time' value='{$start_time}'>
  �����ɶ��G<input type='text' name ='end_time' value='{$end_time}'>
  <input type='hidden' name ='op' value='add_event'>
  <input type='submit' value='�x�s' class='input_btn'>
  </form>";
  return $main;
}

//�s�W���
function add_event(){
  global $link;
  if(!get_magic_quotes_gpc()){
    $_POST['event_content']=addslashes($_POST['event_content']);
  }
  $sql="replace into calendar (sn,start_time,end_time,event_title,event_content) values('{$_POST['sn']}','{$_POST['start_time']}','{$_POST['end_time']}', '{$_POST['event_title']} ', '{$_POST['event_content']} ')";
  mysql_db_query("myweb",$sql,$link) or die("�L�k�g�J�ƥ��I<br>".$sql);
  return $_POST['date'];
}

//��ܳ�@�����e
function show_event($date){
  global $link;
  $sql="select sn,start_time,end_time,event_title,event_content from calendar where start_time like '{$date}%' order by sn";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���ƥ��ơI<br>".$sql);
  $main="<div class='date'>$date</div>";
  while(list($sn,$start_time,$end_time,$event_title,$event_content) = mysql_fetch_row($result)){
    $event_content=nl2br($event_content);
    $main.="
      <div class='event_title'>$event_title</div>
      <p class='event_content'>      
      {$event_content}
      <div class='time'>$start_time �� $end_time</div>
      <div class='admin_tool'>|
      <a href='{$_SERVER['PHP_SELF']}?op=modify_event&sn={$sn}'>�s��</a> |
      <a href='javascript:delete_data($sn)'>�R��</a> |
      </div>
    </p>";
  }
  return $main;
}

//�R���@�����
function del_event($sn){
  global $link;
  $sql="delete from calendar where sn='$sn'";
  mysql_db_query("myweb",$sql,$link) or die("�L�k�R���ƥ��I<br>".$sql);
}

//�q�X�ƥ����
function list_event(){
  global $link;
  $sql="select DISTINCT left(start_time,10) from calendar order by start_time desc";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k���ƥ��ơI<br>".$sql);
  $main="";
  while(list($start_time) = mysql_fetch_row($result)){
    $day_array[]=$start_time;    
  }
  $main=make_calendar($day_array,$_GET['year'],$_GET['mon']);
  return $main;
}

//�s�@���
function make_calendar($day_array=array(),$year="",$mon=""){
  //������~��
  if(empty($year))$year=date("Y");
  //���������
  if(empty($mon))$mon=date("m");
  //���Ѥ��
  $today=date("Y-m-d");
  //������o�Ӥ릳�X��
  $day_num=date("t",mktime(0,0,0,$mon,1,$year));
  //�������1��O�P���X
  $first_w=date("w",mktime(0,0,0,$mon,1,$year));
  //�p��һݦC��
  $need_cell=$day_num+$first_w;
  $rows=ceil($need_cell/7);
  
  $pre_year=date("Y",mktime(0,0,0,$mon-1,1,$year));
  $pre_mon=date("m",mktime(0,0,0,$mon-1,1,$year));
  $next_year=date("Y",mktime(0,0,0,$mon+1,1,$year));
  $next_mon=date("m",mktime(0,0,0,$mon+1,1,$year));
  
  $cal_tool="<div class='date'>
  <a href='{$_SERVER['PHP_SELF']}?year={$pre_year}&mon={$pre_mon}'>
  <img src='images/pre.png' border=0></a>
  {$year}�~{$mon}��
  <a href='{$_SERVER['PHP_SELF']}?year={$next_year}&mon={$next_mon}'>
  <img src='images/next.png' border=0></a>
  </div>";
  
  $calendar="
  $cal_tool
  <table class='cal'><tr>
  <td class='day_w'>��</td><td class='day_w'>�@</td>
  <td class='day_w'>�G</td><td class='day_w'>�T</td>
  <td class='day_w'>�|</td><td class='day_w'>��</td>
  <td class='day_w'>��</td></tr>";

  for($i=0;$i<$rows;$i++){
    $calendar.="<tr>";
    for($j=0;$j<7;$j++){      
      if($j==$first_w and $i==0){
        $show_day="1";
        $date=sprintf ("%04d-%02d-%02d",$year,$mon,$show_day);
        $bgcolor=($today==$date)?"#d5d9ea":"#f8f8f8";
        $pic=get_pic($date,$day_array);
      }elseif($show_day>=$day_num){
        $show_day="";
        $bgcolor="#ffffff";
        $pic="";
      }elseif(!empty($show_day)){
        $show_day++;
        $date=sprintf ("%04d-%02d-%02d",$year,$mon,$show_day);
        $bgcolor=($today==$date)?"#d5d9ea":"#f8f8f8";      
        $pic=get_pic($date,$day_array);
      }       
      
      $calendar.="
      <td class='day' bgcolor='$bgcolor'>
      $show_day
      <div class='day_pic'>$pic</div>
      </td>";
    }
    $calendar.="</tr>";
  }
  $calendar.="</table>";
  return $calendar;
}

//���o�Ϥ�
function get_pic($date,$day_array){
  if(is_array($day_array) and in_array($date,$day_array)){
    $pic="<a href='{$_SERVER['PHP_SELF']}?op=show_event&date={$date}'>
    <img src='images/diary.png' border=0>
    </a>
    <a href='{$_SERVER['PHP_SELF']}?op=input_form&date={$date}'>
    <img src='images/write.png' border=0>
    </a>";
  }else{
    $pic="<a href='{$_SERVER['PHP_SELF']}?op=input_form&date={$date}'>
    <img src='images/write.png' border=0>
    </a>";
  }
  return $pic;
}
?>
