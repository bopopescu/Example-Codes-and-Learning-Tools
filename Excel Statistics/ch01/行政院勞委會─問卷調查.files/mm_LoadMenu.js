function mmLoadMenus() {
if (window.mm_menu_indexMenu01) return;
window.mm_menu_indexMenu01 = new Menu("root",120,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",12,"282628","282628","#ffffff","BBEEBB","left","middle",3,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu01.addMenuItem("�D�e����,101","location='/cgi-bin/SM_theme?page=4185c87d'");
mm_menu_indexMenu01.addMenuItem("�D�ޤ���,102","location='/cgi-bin/SM_theme?page=4185cc79'");
mm_menu_indexMenu01.addMenuItem("���¾�x,103","location='/cgi-bin/SM_theme?page=4185e573'");
mm_menu_indexMenu01.addMenuItem("��´�[�c,104","location='/cgi-bin/SM_theme?page=4185f190'");
mm_menu_indexMenu01.addMenuItem("���ݳ��,105","location='/cgi-bin/SM_theme?page=4185f7c5'");
mm_menu_indexMenu01.fontWeight="bold";
mm_menu_indexMenu01.hideOnMouseOut=true;
mm_menu_indexMenu01.menuBorder=1;
mm_menu_indexMenu01.menuLiteBgColor='#ffffff';
mm_menu_indexMenu01.menuBorderBgColor='#777777';
mm_menu_indexMenu01.bgColor='#555555';

window.mm_menu_indexMenu02 = new Menu("root",120,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu02.addMenuItem("�Ҹ����Y,201","location='/cgi-bin/SM_theme?page=414ea820'");
mm_menu_indexMenu02.addMenuItem("�Ұʱ���,202","location='/cgi-bin/SM_theme?page=414eaa4b'");
mm_menu_indexMenu02.addMenuItem("�Ҥu�֧Q,203","location='/cgi-bin/SM_theme?page=414905ce'");
mm_menu_indexMenu02.addMenuItem("�Ҥu�O�I,204","location='/cgi-bin/SM_theme?page=416f7eeb'");
mm_menu_indexMenu02.addMenuItem("�Ҥu�w���å�,205","location='/cgi-bin/SM_theme?page=4182120b'");
mm_menu_indexMenu02.addMenuItem("�Ұ��ˬd,206","location='/cgi-bin/SM_theme?page=41733649'");
mm_menu_indexMenu02.addMenuItem("�Ұʲέp,207","location='/cgi-bin/SM_theme?page=41761dc1'");
mm_menu_indexMenu02.addMenuItem("�D�@�fĳ,208","location='/cgi-bin/SM_theme?page=414ea3ac'");
mm_menu_indexMenu02.addMenuItem("�@���F,209","location='/cgi-bin/SM_theme?page=414ea166'");

mm_menu_indexMenu02.fontWeight="bold";
mm_menu_indexMenu02.hideOnMouseOut=true;
mm_menu_indexMenu02.menuBorder=1;
mm_menu_indexMenu02.menuLiteBgColor='#ffffff';
mm_menu_indexMenu02.menuBorderBgColor='#777777';
mm_menu_indexMenu02.bgColor='#555555';

window.mm_menu_indexMenu03 = new Menu("root",120,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
//mm_menu_indexMenu03.addMenuItem("���D,301","location='/cgi-bin/SM_theme?page=41b06a06'");
//mm_menu_indexMenu03.addMenuItem("�Ҥu,302","location='/cgi-bin/SM_theme?page=41b069f5'");
for( i = 0 ; i < nameArr.length; i++)
{
	//alert(nameArr[i]+","+(301+i));
	mm_menu_indexMenu03.addMenuItem( nameArr[i]+","+(301+i), urlArr[i]);
	//mm_menu_indexMenu03.addMenuItem( nameArr[i], urlArr[i]);
}

mm_menu_indexMenu03.fontWeight="bold";
mm_menu_indexMenu03.hideOnMouseOut=true;
mm_menu_indexMenu03.menuBorder=1;
mm_menu_indexMenu03.menuLiteBgColor='#ffffff';
mm_menu_indexMenu03.menuBorderBgColor='#777777';
mm_menu_indexMenu03.bgColor='#555555';

window.mm_menu_indexMenu04 = new Menu("root",120,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu04.addMenuItem("���ʦ�ƾ�,401","location='/cgi-bin/MessageCal/my_cal'");
mm_menu_indexMenu04.addMenuItem("�ۼи�T,402","location='/cgi-bin/SM_theme?page=41820639'");
mm_menu_indexMenu04.addMenuItem("���U��,403","location='/cgi-bin/org_adm/OM_list_organ?mode=download'");
mm_menu_indexMenu04.addMenuItem("�ݨ��լd,404","location='/cgi-bin/poll_all'");
mm_menu_indexMenu04.addMenuItem("�|���M��,405","location='/Template/themes/member/member/joinmember.html'");
mm_menu_indexMenu04.addMenuItem("���Ȱ�,406","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:'");
mm_menu_indexMenu04.addMenuItem("���������Ʀ�],407","location='/cgi-bin/SM_theme?page=41b86508'");
mm_menu_indexMenu04.fontWeight="bold";
mm_menu_indexMenu04.hideOnMouseOut=true;
mm_menu_indexMenu04.menuBorder=1;
mm_menu_indexMenu04.menuLiteBgColor='#ffffff';
mm_menu_indexMenu04.menuBorderBgColor='#777777';
mm_menu_indexMenu04.bgColor='#555555';

window.mm_menu_indexMenu05 = new Menu("root",168,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu05.addMenuItem("¾�~�V�m��,801","location='http://www.evta.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("�Ҥu�O�I��,802","location='http://www.bli.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("�w���åͬ�s��,803","location='http://www.iosh.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("�����줽��,804","location='http://www.labor.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("�_�ϳҰ��ˬd��,805","location='http://www.nlio.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("���ϳҰ��ˬd��,806","location='http://www.crlio.gov.tw/'");
mm_menu_indexMenu05.addMenuItem("�n�ϳҰ��ˬd��,807","location='http://www.slio.gov.tw/'");
mm_menu_indexMenu05.fontWeight="bold";
mm_menu_indexMenu05.hideOnMouseOut=true;
mm_menu_indexMenu05.menuBorder=1;
mm_menu_indexMenu05.menuLiteBgColor='#ffffff';
mm_menu_indexMenu05.menuBorderBgColor='#777777';
mm_menu_indexMenu05.bgColor='#555555';

window.mm_menu_indexMenu06 = new Menu("root",168,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu06.addMenuItem("English","location='http://www.via.com.tw/'");
mm_menu_indexMenu06.addMenuItem("Japanese","location='http://www.viatech.co.jp/jp/index/index.jsp'");
mm_menu_indexMenu06.addMenuItem("Chinese Big5","location='http://www.viatech.com.tw/big5/index/index.jsp'");
mm_menu_indexMenu06.addMenuItem("Chinese Simplified","location='http://www.viatech.com.cn/gb/index/index.jsp'");
mm_menu_indexMenu06.fontWeight="bold";
mm_menu_indexMenu06.hideOnMouseOut=true;
mm_menu_indexMenu06.menuBorder=1;
mm_menu_indexMenu06.menuLiteBgColor='#ffffff';
mm_menu_indexMenu06.menuBorderBgColor='#777777';
mm_menu_indexMenu06.bgColor='#555555';

window.mm_menu_indexMenu07 = new Menu("root",120,17,"Trebuchet MS, Tahoma, Verdana, Arial, Helvetica, sans-serif",11,"282628","282628","#ffffff","BBEEBB","left","middle",0,0,1000,-5,7,true,true,true,0,true,true);
mm_menu_indexMenu07.addMenuItem("�Ҹ����Y,201","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP07'");
mm_menu_indexMenu07.addMenuItem("�Ұʱ���,202","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP01'");
mm_menu_indexMenu07.addMenuItem("�Ҥu�֧Q,203","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP02'");
mm_menu_indexMenu07.addMenuItem("�Ҥu�O�I,204","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP03'");
mm_menu_indexMenu07.addMenuItem("�Ҥu�w���å�,205","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP04'");
mm_menu_indexMenu07.addMenuItem("�Ұ��ˬd,206","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP05'");
mm_menu_indexMenu07.addMenuItem("�Ұʲέp,207","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP08'");
mm_menu_indexMenu07.addMenuItem("�D�@�fĳ,208","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP09'");
mm_menu_indexMenu07.addMenuItem("�@���F,209","location='/cgi-bin/Message/MM_gen_search?cond=citizen:::41b41f02||:MP10'");

mm_menu_indexMenu07.fontWeight="bold";
mm_menu_indexMenu07.hideOnMouseOut=true;
mm_menu_indexMenu07.menuBorder=1;
mm_menu_indexMenu07.menuLiteBgColor='#ffffff';
mm_menu_indexMenu07.menuBorderBgColor='#777777';
mm_menu_indexMenu07.bgColor='#555555';

mm_menu_indexMenu06.writeMenus();
} // mmLoadMenus()
