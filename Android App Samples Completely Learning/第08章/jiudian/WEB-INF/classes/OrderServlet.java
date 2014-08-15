package wyf.wyy;
import java.io.*; 
import java.util.*;
import javax.servlet.*; 
import javax.servlet.http.*;
public class OrderServlet extends HttpServlet{
	public void init(ServletConfig conf) throws ServletException 
	{ //Servlet��inti��l�Ƥ�k
		super.init(conf);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException{//doGet��k
		doPost(req,res);//�ե�doPost��k
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException{
		req.setCharacterEncoding("big5");
		res.setCharacterEncoding("big5");		
		String action = req.getParameter("action");//�o��ШD���T��action		
		HttpSession session=req.getSession(true);//�o��session�ﹳ
		PrintWriter pw=res.getWriter();//�o���X�y�ﹳ
		String msg="";//�n�����ܮ���		
		String uname = (String)session.getAttribute("uname");//�o��n���Τ�W
		//�귽���X�ت��A
		String ostatus1 = "�w�q��";
		String ostatus2 = "�w�q���\";
		String ostatus3 = "�w�q����";
		Vector<String[]> OrderList = //�o��q��C��
					(Vector<String[]>)session.getAttribute("OrderList");
		if(OrderList==null)//�p�G���ūh�Ыؤ@�ӭq��C��ﹳ
		{OrderList = new Vector<String[]>();}		
		/*if(action.equals("ADD")){	
			String orderNum = (String)req.getParameter("orderNum");//�o��귽�W		
			if(orderNum!=null){//�o��w�q�}�l�ɶ�
				String fyear = (String)req.getParameter("fyear");
				String fmonth = (String)req.getParameter("fmonth");
				String fday = (String)req.getParameter("fday");
				String fhour = (String)req.getParameter("fhour");
				//�o��w�q�����ɶ�
				String eyear = (String)req.getParameter("eyear");
				String emonth = (String)req.getParameter("emonth");
				String eday = (String)req.getParameter("eday");
				String ehour = (String)req.getParameter("ehour");
				//�o��ҹw�q���귽����
				String group = (String)req.getParameter("group");
				String ftime = fyear+"-"+fmonth+"-"+fday+"-"+fhour+":"+"00";
				String etime = eyear+"-"+emonth+"-"+eday+"-"+ehour+":"+"00";				
				String[] s = new String[4];
				s[0] = orderNum; s[1] = group;
				s[2] = ftime;    s[3] = etime;				
				OrderList.add(s);//�N�q��[�J��q��C��				
				session.setAttribute("OrderList",OrderList);//�N�q��C����session								
			}
			res.sendRedirect("groupList.jsp");//�o�^��groupList����
		}
		else if(action.equals("DELETE")){			
			String id = req.getParameter("index");//�o��n�R���q�檺�U��			
			int index = Integer.parseInt(id);//�ഫ��int��			
			OrderList.remove(index);//�R�����襤���q��
			res.sendRedirect("myOrder.jsp");//�o�^�q�歶��
		}
		else if(action.equals("REMOVE")){		
			OrderList.removeAllElements();//�����Ҧ��q��			
			session.setAttribute("OrderList",OrderList);//�N�q��C���isession			
			res.sendRedirect("myOrder.jsp");//�o�^�q�歶��			
		}
		else if(action.equals("SUBMIT")){			
			if(uname!=null){//�P�_�Τ�O�_�n��
				if(Order_DB.addOrder(uname,OrderList)!=-1){					
					OrderList.removeAllElements();//���槹���A�M�ŭq��C��
					msg = "�q�洣�榨�\�A���s���w��z�����{<br><br>"+
					       "<a href=main.jsp>��^�D��";//���X���\����
				}
				else{
					msg = "�藍�_�A�t�ο��~�A���楢�ѡI<br><br>"+
					       "<a href=main.jsp>��^�D��";//���X���Ѯ���
				}			
			}
			else{//�Τ�S���n�������p
				msg = "�z�٨S���n���A�Х��n���C<br><br>"+
				     "<a href=login.jsp>�{�b�n��>>";		
			}
			req.setAttribute("msg",msg);//�N�����o�e�������ܭ���
			req.getRequestDispatcher("info.jsp").forward(req,res);	
		}
		else if(action.equals("order")){
			if(uname==null){//�Τ�S���n��
				msg = "�z�٨S���n���A�Х��n���C<br><br>"+
				     "<a href=login.jsp>�{�b�n��>>";
				req.setAttribute("msg",msg);
				req.getRequestDispatcher("info.jsp").forward(req,res);
			}
			else{//�d�߸ӥΤ᪺�Ҧ��q��H��
				String sql = "select * from olist where oname='"+uname+"'";
				Vector<String []> list = Order_DB.getOrderList(sql);//����d��				
				req.setAttribute("list",list);
				req.getRequestDispatcher("list.jsp").forward(req,res);
			}
		}*/
		if(action.equals("ListDetail")){
			String oid = req.getParameter("oid");//�o��q��s��
			Vector<String []> ListDetail = Order_DB.getOrderDetail(oid);//����d��				
			req.setAttribute("ListDetail",ListDetail);
			req.setAttribute("oid",oid);
			req.getRequestDispatcher("detail.jsp").forward(req,res);
		}
		
		else if(action.equals("allOrders")){//������d�߭q��
			if(session.getAttribute("adname")!=null){//�޲z���O�_�n��
				String sql = "";//�n��SQL�ޥ�
				int conditon = Integer.parseInt(req.getParameter("condition"));
				switch(conditon){
					case 1://1��ܩҦ��q��
					sql = "select * from olist";
					break;
					case 2://2��ܤw�g�B�z���q��
					sql = "select * from olist where ostatus='"+ostatus2+"' or ostatus='"+ostatus3+"'";
					break;
					case 3://3��ܥ��B�z���q��
					sql = "select * from olist where ostatus='"+ostatus1+"'";
					break;
				}
				Vector<String []> list = Order_DB.getOrderList(sql);				
				req.setAttribute("list",list);//�N�q��C���^				
				req.getRequestDispatcher("adminOrders.jsp").forward(req,res);
			}
			else{
				msg = "�Х��n��";//�S���n�������ܮ���
				req.setAttribute("msg",msg);
				req.getRequestDispatcher("adinfo.jsp").forward(req,res);
			}
		}
		else if(action.equals("query")){//���s���d�߭q��
		    Vector<String []> list = null;
		    try{
		    	int oid = Integer.parseInt(req.getParameter("oid"));
				String sql = "select * from olist where oid="+oid;
				list = Order_DB.getOrderList(sql);
		    }
		    catch(NumberFormatException nfe)//��J�q�渹�榡�����T
		    {list = new Vector<String []>();}//��^�@�ӪŪ��V�q							
			req.setAttribute("list",list);
			req.getRequestDispatcher("adminOrders.jsp").forward(req,res);
		}
		else if(action.equals("dealOrder")){//�B�z�q��
			String adname = (String)session.getAttribute("adname");
			String reason = req.getParameter("reason");
			String ostatus = req.getParameter("ostatus");
			int oid = Integer.parseInt(req.getParameter("oid"));
			//����SQL
			String sqla = "update olist set ostatus='"+ostatus+"',oreason='"+
							reason+"',odeal='"+adname+"'where oid="+oid;
			String sqlb = "update oinfo set ostatus='"+ostatus+"' where oid="+oid;			
			boolean b = DB.update(sqla,sqlb);//�����s		
			if(b==true){
				msg = "�q��B�z���\<br><br>"
					+"<a href=OrderServlet?action=allOrders&&condition=1>��^";
			}
			else{msg = "�q��B�z�o�Ϳ��~�A�B�z����";}
			req.setAttribute("msg",msg);//��^�B�z����
			req.getRequestDispatcher("adinfo.jsp").forward(req,res);
		}
	}
}
