<?php
$link = @mysql_connect("localhost", "tad", "12345")  or die("�V�|�I�L�k�s�W��Ʈw��I" . mysql_error());

//��ưϡ]�Ҧ�function�Ҹm�󦹳B�^


//�y�{����ϡ]�P�_�ϥΪ̭n�����ʧ@�A�h�I�s��������ƩΪ����k�^
switch ($_REQUEST['op']) {
case "input_form":
  $main=input_form();
  break;
case "add_diary":
  $date=add_diary();
  header("location: {$_SERVER['PHP_SELF']}?op=show_diary&date={$date}");
  break;
case "show_diary":
  $main=show_diary($_GET['date']);
  break;
case "modify_diary":
  $main=input_form($_GET['sn']);
  break;
case "del_diary":
  del_diary($_GET['sn']);
  header("location: {$_SERVER['PHP_SELF']}");
  break;
case "mkpdf":
  require('fpdf/mypdf.php');
  mkpdf($_GET['date']);
  break;
default:
  $main=list_diary();
  break;
}

//�e�{�e���ϡ]�Y���ʧ@�O�ݭn�e�{�b�e���W���A����Τ@�b����X�^
echo make_page($main);

//�e���s�@��
function make_page($main){
  $page="
  <html>
  <head>
  <meta http-equiv='content-type' content='text/html; charset=Big5'>
  <link rel='stylesheet' type='text/css' media='screen' href='style.css'>
  <title>�ڪ���O��</title>
  <script language='JavaScript' type='text/JavaScript'>
  <!--
  function delete_data(sn){
    var sure = window.confirm('�T�w�n�R������ơH');
    if (!sure)	return;
    location.href='{$_SERVER['PHP_SELF']}?op=del_diary&sn=' + sn;
  }
  //-->
  </script>
  </head>
  <body background='images/bg.gif'>
  
  <div class='center_block'>
    <img src='images/logo.png' class='logo'>
    <div class='toolbar'>
      <a href='{$_SERVER['PHP_SELF']}?op=input_form'>�g��O</a>
      <a href='{$_SERVER['PHP_SELF']}'>��O�`��</a>
    </div>
    $main
  </div>
  <div class='copyright'>Powered by Tad (tadbook5@gmail.com) (c) 2001-2005</div>
  </body>
  </html>";
  
  return $page;
}

//��J��O������
function input_form($sn=""){
  global $link;
  if(!empty($sn)){
    $sql="select * from diary where sn='$sn'";
    $result=mysql_db_query("myweb",$sql,$link) or die("�L�k����O��ơI<br>".$sql);
    $diary= mysql_fetch_assoc($result);
    $date=$diary['diary_date'];
    $sn_col="<input type='hidden' name ='sn' value='{$diary['sn']}'>";
  }else{
    $date=(empty($_GET['date']))?date("Y-m-d"):$_GET['date'];
    $sn_col="";
  }
      
  $main="<form action='{$_SERVER['PHP_SELF']}' method='post'>
  <textarea name='diary_content' cols=20 rows=15>{$diary['diary_content']}</textarea>
  $sn_col
  <input type='hidden' name ='date' value='{$date}'>
  <input type='hidden' name ='op' value='add_diary'>
  <input type='submit' value='�x�s' class='input_btn'>
  </form>";
  return $main;
}

//�s�W���
function add_diary(){
  global $link;
  if(!get_magic_quotes_gpc()){
    $_POST['diary_content']=addslashes($_POST['diary_content']);
  }
  $sql="replace into diary (sn,diary_date,diary_content) values('{$_POST['sn']}','{$_POST['date']}', '{$_POST['diary_content']} ')";
  mysql_db_query("myweb",$sql,$link) or die("�L�k�g�J��O��I<br>".$sql);
  return $_POST['date'];
}

//��ܳ�@�����e
function show_diary($date){
  global $link;
  $sql="select sn,diary_content from diary where diary_date='{$date}' order by sn";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k����O��ơI<br>".$sql);
  $main="<div class='date'>$date</div>";
  while(list($sn,$diary_content) = mysql_fetch_row($result)){
    $diary_content=nl2br($diary_content);
    $main.="<p class='diary_content'>{$diary_content}
      <div class='admin_tool'>|
      <a href='{$_SERVER['PHP_SELF']}?op=mkpdf&date={$date}'>�ץXPDF</a> |
      <a href='{$_SERVER['PHP_SELF']}?op=modify_diary&sn={$sn}'>�s��</a> |
      <a href='javascript:delete_data($sn)'>�R��</a> |
      </div>
    </p>";
  }
  return $main;
}

//�R���@�����
function del_diary($sn){
  global $link;
  $sql="delete from diary where sn='$sn'";
  mysql_db_query("myweb",$sql,$link) or die("�L�k�R����O��I<br>".$sql);
}

//�q�X��O����
function list_diary(){
  global $link;
  $sql="select DISTINCT diary_date from diary order by diary_date desc";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k����O��ơI<br>".$sql);
  $main="";
  while(list($diary_date) = mysql_fetch_row($result)){
    $day_array[]=$diary_date;    
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
    $pic="<a href='{$_SERVER['PHP_SELF']}?op=show_diary&date={$date}'>
    <img src='images/diary.png' border=0>
    </a>";
  }else{
    $pic="<a href='{$_SERVER['PHP_SELF']}?op=input_form&date={$date}'>
    <img src='images/write.png' border=0>
    </a>";
  }
  return $pic;
}

//�s�@PDF��
function mkpdf($date=""){
  global $link;
  //�����MyPDF�A����$pdf����
  $pdf=new MyPDF();
  //�]�w�w�]�e��
  $pdf->FPDF("P","mm","A5");
  //�]�w���D
  $pdf->SetTitle("My Diary");
  //�]�w�D�D
  $pdf->SetSubject($date);
  //�]�w�@��
  $pdf->SetAuthor("tad");
  //�]�w���
  $pdf->SetMargins(15, 15 ,15);
  //�]�w�۰ʴ���
  $pdf->SetAutoPageBreak("on","20");
  //�]�w��ܼҦ�
  $pdf->SetDisplayMode("real");
  //�s�W�@�u���ѡv����
  $pdf->AddPage('P');
  //�[�J����r��
  $pdf->AddBig5hwFont();  
  //�]�w��r�C��]�¦�^
  $pdf->SetTextColor(0,0,0);
  //�]�w����r���B����B12��
  $pdf->SetFont('Big5-hw','B',12);
  
  //�����ѩҦ���O
  $sql="select sn,diary_content from diary where diary_date='{$date}' order by sn";
  $result=mysql_db_query("myweb",$sql,$link) or die("�L�k����O��ơI<br>".$sql);
  while(list($sn,$diary_content) = mysql_fetch_row($result)){
    //��J��r�A��Z9
    $pdf->Write(9,$diary_content);
    //���J�ťզ�
    $pdf->Ln();
  }

  //��XPDF
  $pdf->Output("�ڪ���O_{$date}","I");
}
?>
