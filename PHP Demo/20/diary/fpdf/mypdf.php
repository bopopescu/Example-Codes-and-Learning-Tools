<?php
require("chinese.php");

class MyPDF extends PDF_Chinese{
  //����
  function Header(){
    $this->Image("images/logo.png", 0, 0,148);
    $this->SetXY(15, 40);
    //��r�~�[�]�w
    $this->SetTextColor(0,0,0);
    $this->SetFont('Arial','BI',16);
    $this->Text(10, 26, $_GET['date']);
    $this->SetTextColor(255,255,255);
    $this->SetFont('Arial','BI',16);
    $this->Text(9.5, 25.5, $_GET['date']);
  }
  
  //����
  function Footer(){
    $this->SetY(-18);
    $this->SetFont('Big5-hw','B',9);
    $page=$this->PageNo();
    $this->Cell(0,10,"�� {$page} ��","T",0,'C');
  }
}
?>
