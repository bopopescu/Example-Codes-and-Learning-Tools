package wyf.wyy;
import java.io.*; 
import java.util.*;
import javax.servlet.*; 
import javax.servlet.http.*;
public class ListServlet extends HttpServlet
{
	public void init(ServletConfig conf) throws ServletException
	{//Servlet��inti��l�Ƥ�k
		super.init(conf);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException{//doGet��k
		doPost(req,res);//�ե�doPost��k
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException,IOException{
		req.setCharacterEncoding("big5");//�]�mreq�s�X��big5
		res.setCharacterEncoding("big5");//�]�mres�s�X��big5		
		String action = req.getParameter("action");//�o��ШD���T��action		
		HttpSession session=req.getSession(true);//�o��session�ﹳ
		//�o��n�����޲z��ID		
		String adnameSes = (String)session.getAttribute("adname");
		String msg = "";//�n�������r�Ŧ�
		/*if(action.equals("list")){//�o����ժ��w�q�W�h			
			int gId = Integer.parseInt(req.getParameter("gId"));
			Vector<String> list = DB.getGroupInfo(gId);
			session.setAttribute("list",list);
			res.sendRedirect("groupList.jsp");
		}
		else if(action.equals("status")){
			Vector<String []> v = null;//�o�e����ܸ귽���A�������H��
			String isOrdered = "NO";//�O�_�Q�w�w�A��l��"NO"�A�Y�S���Q�w�q
			String rgid = req.getParameter("rgid");//�o��n�d�ݪ��A���귽�s��
			//�귽���b�Q�w�w���A���B�z���e���A�����q��
			if(Order_DB.isOrdered(rgid)) {isOrdered = "YES";}
			//�i�H�w�q�ɡA�q�ƾڮw�o��w�g�Q�w�q�C��
			else {v = Order_DB.getOrderedDay(rgid);}
			//�]�m�귽���A���@�ǫH������o����ܭ���
			req.setAttribute("v",v);
			req.setAttribute("rgid",rgid);
			req.setAttribute("isOrdered",isOrdered);	
			req.getRequestDispatcher("lookRes.jsp").forward(req,res);			
		}*/
//************************************�귽***********************************************
		if(action.equals("adminList")){
			Vector<String> list = new Vector<String>();
			if(session.getAttribute("adname")!=null){//�P�_�޲z���O�_�n��
			    int gId = Integer.parseInt(req.getParameter("gId"));
			    if(gId==0){//0�N��Ҧ�����
			    	list.add("0");
			    	list.add("�Ҧ�����");
			    }
			    else{//�Y�@�S�w����
			    	list = DB.getGroupInfo(gId);
			    }								
				session.setAttribute("list",list);
				res.sendRedirect("adminResource.jsp");
			}
			else{//�޲z���S���n��
				msg = "�Х��n��";
				this.forward(req,res,msg,"adinfo.jsp");		 
			}
		}
		else if(action.equals("queryRes")){//�d��
			String rgid = req.getParameter("rgid");//�o��귽ID
			String sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rgroup,rid "+
										"from resource where rgid='"+rgid+"'";			
			Vector<String[]> v = DB.getResInfo(sql);//����d��
			req.setAttribute("list",v);
			req.getRequestDispatcher("ResQuery.jsp").forward(req,res);	
		}
		
		else if(action.equals("editRes")){//�s��귽
			int rid = Integer.parseInt(req.getParameter("rid"));
			String sql = "select rgid,rlevel,rmoney,rdetail,rstatus,rgroup,rid from resource where rid='"+rid+"'";
			Vector<String []> rinfo = DB.getResInfo(sql);
			req.setAttribute("rinfo",rinfo);
			req.getRequestDispatcher("ResInfo.jsp").forward(req,res);
		}
		else if(action.equals("changeRes")){			
			String rgidBefor = req.getParameter("rgidBefor");//�s��e���s��
			String rgidAfter = req.getParameter("rgidAfter");//�s��᪺�s��			
			int rid = Integer.parseInt(req.getParameter("rid"));//�o��귽ID�D��
			//�o��ק�᪺�H��
			String rgroup = req.getParameter("rgroup");
			String rlevel = req.getParameter("rlevel").trim();
			double rmoney = Double.parseDouble(req.getParameter("rmoney").trim());
			String rdetail = req.getParameter("rdetail").trim();
			String rstatus = req.getParameter("rstatus");			
			String sql = "update resource set rgid='"+rgidAfter+"',rgroup='"+rgroup+"',rlevel='"+rlevel+
			             "',rmoney="+rmoney+",rdetail='"+rdetail+"',rstatus='"+rstatus+"' where rid="+rid;			
			if(rgidBefor.equals(rgidAfter)){//�դ��s���S����
				if(DB.update(sql)>0){
					msg = "�ק�O�s���\<br><br><a href=ListServlet?action=adminList&&gId=0>��^";
				}
			}
			else{//�դ��s�����ܤF
			    String sqla = "select * from resource where rgid='"+rgidAfter+"'";
				if(DB.isExist(sqla)){
					msg = "�w�g�����s�����귽�A�Юֹ��J�C<br><br>"+
				      	  "<a href=ListServlet?action=editRes&&rid="+rid+">��^�~��ק�";
				}
			 	else{
			 		if(DB.update(sql)>0){
			 			msg = "�ק�O�s���\<br><br><a href=ListServlet?action=adminList&&gId=0>��^";
			 		}
			 	}
			}
			this.forward(req,res,msg,"adinfo.jsp");		 	
		}
		else if(action.equals("deleteRes")){
			String rid = req.getParameter("rid");//�o��n�R����ID��
			String sql = "delete from resource where rid='"+rid+"'";
			if(DB.update(sql)>0){
				msg = "�R�����\<br><br><a href=ListServlet?action=adminList&&gId=0>��^";						 
			}
			else{
				msg = "�������~�A�R������";
			}
			this.forward(req,res,msg,"adinfo.jsp");
		}
		else if(action.equals("addRes")){
			//�o��n�K�[�귽���ԲӫH��
			String rgid = req.getParameter("rgid").trim();
			String rgroup = req.getParameter("rgroup");
			String rlevel = req.getParameter("rlevel").trim();
			Double rmoney = Double.parseDouble(req.getParameter("rmoney").trim());
			String rdetail = req.getParameter("rdetail").trim();
			String rstatus = req.getParameter("rstatus");			
			String sql = "select * from resource where rgid='"+rgid+"'";
			if(DB.isExist(sql))	{
				msg="���s���������귽�w�g�s�b�A�Юֹ�s����J�C<br><a href=addRes.jsp>��^";
			}
			else{
				int rid = DB.getId("resource","rid");//�o��귽���D��̤j�ȥ[1
				sql = "insert into resource(rid,rgroup,rgid,rlevel,rmoney,rdetail,rstatus)"+
				      "values("+rid+",'"+rgroup+"','"+rgid+"','"+rlevel+"',"+rmoney+",'"+
				       rdetail+"','"+rstatus+"')";
				if(DB.update(sql)>0){
					msg = "�W�[�귽���\�I�I�I<br><a href=addRes.jsp>��^";
				}
			}
			this.forward(req,res,msg,"adinfo.jsp");		 
		}
//***************************����*******************************************
		else if(action.equals("adminGroup")){
			if(session.getAttribute("adname")!=null){//�P�_�޲z���O�_�n��			   
				res.sendRedirect("adminGroup.jsp");
			}
			else{//�S���n��
				msg = "�Х��n��";
				this.forward(req,res,msg,"adinfo.jsp");//��o����� ����	 
			}
		}
		else if(action.equals("editGroup")){//�s�����
			int gId = Integer.parseInt(req.getParameter("gId"));
			Vector<String> ginfo = DB.getGroupInfo(gId);
			req.setAttribute("ginfo",ginfo);
			req.getRequestDispatcher("GroupInfo.jsp").forward(req,res);
		}
		else if(action.equals("changeGroup")){//�ק���իH���ᴣ��		    
		    int gId = Integer.parseInt(req.getParameter("gId"));//�o�����ID
		    String gNameBefor = req.getParameter("gNameBefor");//�o��ק�e���W�r
		    //�o��ק�᪺�H��
		    String gNameAfter = req.getParameter("gNameAfter");
		    String gImg = req.getParameter("gImg");
		    String gDetail = req.getParameter("gDetail");
		    String gOrderDet = req.getParameter("gOrderDet");		    
		    String sql = "update rgroup set gName='"+gNameAfter+"',gImg='"+gImg+"',gDetail='"+gDetail+
		    			"',gOrderDet='"+gOrderDet+"' where gId="+gId;//����SQL		    
		    if(gNameAfter.equals(gNameBefor)){//�P�_���զW�r�O�_����
		    	if(DB.update(sql)>0){
					msg = "�ק�O�s���\<br><br><a href=ListServlet?action=adminGroup>��^";
				}
		    }
		    else{//���զW�r����
		    	String sqla = "select * from rgroup where gName='"+gNameAfter+"'";
				if(DB.isExist(sqla)){//�W�r�w�g�s�b
					msg = "�w�g�����W�r�����աA�Юֹ��J�C<br>"+
							"<a href=ListServlet?action=editGroup&&gId="+gId+">��^";
				}
			 	else{//���զW���s�b
			 		if(DB.update(sql)>0){
			 			msg = "�ק�O�s���\<br><br><a href=ListServlet?action=adminGroup>��^";
			 		}
			 	}
		    }
		   this.forward(req,res,msg,"adinfo.jsp");		 			
		}
		else if(action.equals("deleteGroup")){
			int gId = Integer.parseInt(req.getParameter("gId"));//�o��n�R����ID
			String sqla = "delete from resource where rgroup="+gId;//�R�����հO����SQL
			String sqlb = "delete from rgroup where gId="+gId;//�R�����դU�귽��SQL
			if(DB.update(sqla,sqlb)==true){
				msg = "�R�����զ��\<br><br><a href=ListServlet?action=adminGroup>��^";
			}
			else{
				msg = "�������~�A�R������";
			}
			this.forward(req,res,msg,"adinfo.jsp");		 
		}
		else if(action.equals("addGroup")){
			int gId = DB.getId("rgroup","gId");//�o��K�[���ժ�ID
			//�o���g���H��
			String gName = req.getParameter("gName");
			String gImg = req.getParameter("gImg");
		    String gDetail = req.getParameter("gDetail");
		    String gOrderDet = req.getParameter("gOrderDet"); 
		    String sql = "insert into rgroup(gId,gName,gImg,gDetail,gOrderDet)values('"+gId+"','"+
		    				gName+"','"+gImg+"','"+gDetail+"','"+gOrderDet+"')";//����SQL   
		    if(DB.update(sql)>0){//�����s
		    	msg = "�K�[���զ��\<br><br><a href=ListServlet?action=adminGroup>��^";
		    }
		    else{
		    	msg = "�������~�A�K�[����";
		    }
		    this.forward(req,res,msg,"adinfo.jsp");	
		}
//********************************�޲z��*****************************************
		else if(action.equals("admanage")){			
			if(adnameSes==null){//�P�_�޲z���O�_�n��
			    msg = "�Х��n��";
			    this.forward(req,res,msg,"adinfo.jsp");		 				
			}
			else{
				String sql = "select adlevel from adinfo where adname='"+adnameSes+"'";				
				int adlevel = Integer.parseInt(DB.getInfo(sql));//�o��޲z���ŧO
				if(adlevel!=1){
					msg = "�藍�_�A�v������";
					this.forward(req,res,msg,"adinfo.jsp");		 
				}
				else{
					res.sendRedirect("adminManage.jsp");
				}
			}
		}
		else if(action.equals("addAdmin")){//�K�[�޲z��
			String adnameAdd = req.getParameter("adname");
			String adpwd = req.getParameter("adpwd");
			String sql = "insert into adinfo(adname,adpwd,adlevel)values"+
			  			 "('"+adnameAdd+"','"+adpwd+"',"+0+")";
			String sqla = "select * from adinfo where adname='"+adnameAdd+"'";
			if(DB.isExist(sqla)){
				msg = "���޲z��ID�w�g�s�b�Юֹ��J";
			}
			else{
				if(DB.update(sql)>0)
				msg = "�K�[�޲z�����\";
			}		
			this.forward(req,res,msg,"adinfo.jsp");		 
		}
		else if(action.equals("deleteAdmin")){
			//�o��n�R�����޲z��ID
			String adnameDel = req.getParameter("adname");
			if(adnameDel.equals(adnameSes)){
				msg = "����R���ۤv";
			}
			else{
				String sql = "delete from adinfo where adname='"+adnameDel+"'";
				DB.update(sql);
				msg = "�R�����\";
			}
			this.forward(req,res,msg,"adinfo.jsp");
		}
		else if(action.equals("resetPwd")){
			String adname = req.getParameter("adname").trim();
			String adpwd = req.getParameter("adpwd").trim();
			String sql = "update adinfo set adpwd='"+adpwd+"' where adname='"+adname+"'";
			if(DB.update(sql)>0){
				msg = "�]�m�K�X���\";
			}
			else{
				msg = "�]�m���ѡA�Э��s�ˬd�]�m";
			}
			this.forward(req,res,msg,"adinfo.jsp");
		}
		else if(action.equals("changePwd")){//�޲z���ק�K�X
			String adname = req.getParameter("adname");
			String adpwd = req.getParameter("adpwd");
			String newPwd = req.getParameter("newPwd");
			String sql = "select adpwd from adinfo where adname='"+adname+"'";
			String pwdFromDB = DB.getInfo(sql);
			if(pwdFromDB==null){
				msg = "�Ӻ޲z���Τᤣ�s�b�A�Э��s�ֹ��J";
			}
			else if(!pwdFromDB.equals(adpwd)){
				msg = "�K�X��J�����T�A�Э��s��J";
			}
			else{
				sql = "update adinfo set adpwd='"+newPwd+"' where adname='"+adname+"'";				
				if(DB.update(sql)>0){
					msg = "�ק令�\";
				}
				else{
					msg = "�������~�A�ק異�ѡI�I�I";
				}				
			}
			this.forward(req,res,msg,"adinfo.jsp");			
		}			
}
	public void forward(HttpServletRequest req,HttpServletResponse res,
	String msg,String url)throws ServletException,IOException
	{
		req.setAttribute("msg",msg);//�]�m����
		req.getRequestDispatcher("adinfo.jsp").forward(req,res);//��o���������
	}
}
