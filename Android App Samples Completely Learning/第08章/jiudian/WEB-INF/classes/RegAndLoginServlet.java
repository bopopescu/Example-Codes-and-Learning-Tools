package wyf.wyy;
import java.io.*; 
import javax.servlet.*; 
import javax.servlet.http.*;
import java.util.*;
public class RegAndLoginServlet extends HttpServlet
{
	public void init(ServletConfig conf) throws ServletException 
	{ 
		super.init(conf);
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException 
	{
		doPost(req,res);
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res)
	throws ServletException, IOException
	{
		req.setCharacterEncoding("big5");
		res.setCharacterEncoding("big5");
		//�o��ШD���T��action		
		String action = req.getParameter("action");
		//�o��session�ﹳ
		HttpSession session=req.getSession(true);
		PrintWriter pw=res.getWriter();
		String msg="";
	/*if(action.equals("reg")){			
		//�o����U�Τ��g�����
		String uname = req.getParameter("uname").trim();			
		String pwd = req.getParameter("pwd").trim();
		String telNum = req.getParameter("telNum").trim();
		String realName = req.getParameter("realName").trim();
		String gender = req.getParameter("gender");
		String email = req.getParameter("email").trim();
		//���ˬd�ݥΤ�O�_�s�b��SQL
		String sqla = "select * from user where uname='"+uname+"'";
		if(DB.isExist(sqla)){
			msg=uname+":�Τ�W�w�s�b�A�иոեt�@�ӡI�I�I<br/><a href=reg.jsp>���s���U</a>";
		}
		else{//���J�s�Τ᪺SQL				
			String sql = "insert into user values ('"+uname+"','"+pwd+"'"+
					",'"+telNum+"','"+realName+"','"+gender+"','"+email+"')";							
			DB.update(sql);//���洡�J�ʧ@
			msg=uname+":���U���\�I�I�I<br/><a href=login.jsp>�{�b�n���H�H�H</a>";
		}
		req.setAttribute("msg",msg);//��o����
		req.getRequestDispatcher("info.jsp").forward(req,res);
	}

		else if(action.equals("login")){			
			session.removeAttribute("uname");//�qsession��������e�n���Τ�
			String uname = req.getParameter("uname").trim();//�o��n���Τ�W
			String pwd =  req.getParameter("pwd").trim();//�o��n���K�X
			//���ˬd�ݥΤ�O�_�s�b��SQL
			String sqla = "select * from user where uname='"+uname+"'";
			if(DB.isExist(sqla)){
				String sql = "select pwd from user where uname='"+uname+"'";
				String password=DB.getInfo(sql).trim();//�q�ƾڮw�o��K�X
				if(pwd.equals(password)){				
					session.setAttribute("uname",uname);//�n�����\
					res.sendRedirect("main.jsp");//�����D��	
				}
				else{
					msg=uname+"�K�X�����T�I�I�I<br><br>"+
					       "<a href=login.jsp>���s�n��</a>";					
					req.setAttribute("msg",msg);//�N�H���o�e��H����ܭ���
					req.getRequestDispatcher("info.jsp").forward(req,res);				
				}
			}
			else{
				msg=uname+"���Τ�W���s�b�I�I�I<br><br>"+
				     "<a href=login.jsp>���s�n��</a>";				
				req.setAttribute("msg",msg);//�N�H���o�e��H����ܭ���
				req.getRequestDispatcher("info.jsp").forward(req,res);
			}	
		}						
		else if(action.equals("logout")){
			session.removeAttribute("uname");//�qsession�������n���Τ�
			msg = "�w��A�����{�I�I�I";//���ܮ���
			req.setAttribute("msg",msg);//�]�m����o���ܮ���
			req.getRequestDispatcher("info.jsp").forward(req,res);
		}		
		else if(action.equals("changeMyInfo")){
			String uname=(String)session.getAttribute("uname");
			//�o��ק�᪺�H��
			String telNum = req.getParameter("telNum").trim();
			String realName = req.getParameter("realName").trim();
			String gender = req.getParameter("gender");
			String email = req.getParameter("email").trim();
			//�ͦ���sSQL
			String sql="update user set telNum='"+telNum+"',realName='"+realName+
			   "',gender='"+gender+"',email='"+email+"'where uname='"+uname+"'";
			if(DB.update(sql)==1){
				msg = "�ק��Ʀ��\�I�I�I<br>";				
				req.setAttribute("msg",msg);//�N�H���o�e��H����ܭ���
				req.getRequestDispatcher("info.jsp").forward(req,res);
			}
		}
		else if(action.equals("changePwd")){
			//�o�촣�檺�H��
			String uname=(String)session.getAttribute("uname");
			String currentPwd = req.getParameter("currentPwd").trim();
			String newPwd = req.getParameter("newPwd").trim();
			//�q�ƾڮw�����e�Τ᪺�K�X
			String sqla = "select pwd from user where uname='"+uname+"'";
			String pwdFromDB =  DB.getInfo(sqla);			
			if(currentPwd.equals(pwdFromDB)){//����K�X
				String sqlb = "update user set pwd='"+newPwd+"'where uname='"+
								uname+"'";//��s���Τ᪺�K�X
				DB.update(sqlb);//�����s
				msg = "�K�X�ק令�\�I�I�I�U�@���Хηs�K�X�n���C<br>";
			}
			else{
				msg = "�z��J���K�X�����T�A�ק異�ѡI�I�I<br>"+
				       "<a href=changeMyInfo.jsp>��^�~��ק�H�H�H";
			}			
			req.setAttribute("msg",msg);//�N�H���o�e��H����ܭ���
			req.getRequestDispatcher("info.jsp").forward(req,res);
		}*/

		if(action.equals("adlogin")){
			session.removeAttribute("adname");
			String adname = req.getParameter("adname").trim();//�o��n���W
			String pwd =  req.getParameter("pwd").trim();//�o��n���K�X
			//���˱q�ƾڮw�o��n���޲z���K�X��SQL�y�y
			String sqla = "select adpwd from adinfo where adname='"+adname+"'";
			String pwdFromDB = DB.getInfo(sqla);//����d�߱o�쥿�T�K�X
			if(pwdFromDB!=null&&pwd.equals(pwdFromDB)){//�n�����\			
				session.setAttribute("adname",adname);//�N�n���޲z���O�s�isession
				msg = "�n�����\�C";//���ܵn�����\
			}
			else{//�n�����Ѫ�����
				msg = "���~���Τ�W�M�K�X�A�Э��s�n��<br><br>"+
				 		"<a href=adindex.jsp>���s�n��";				
			}
			//�N�H���o�e��H����ܭ���
			req.setAttribute("msg",msg);
			req.getRequestDispatcher("adinfo.jsp").forward(req,res);
		}
		else if(action.equals("adlogout")){//���P
			session.removeAttribute("adname");
			msg = "�h�X���\�C";//���ܵ��P���\
			req.setAttribute("msg",msg);
			req.getRequestDispatcher("adinfo.jsp").forward(req,res);
		}
	}
}
