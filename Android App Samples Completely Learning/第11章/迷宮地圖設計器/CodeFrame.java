package wyf;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CodeFrame extends JFrame//�ͦ��G���Ʋյ���
{
	JTextArea jta=new JTextArea();//�Ы�JTextArea
	JScrollPane jsp=new JScrollPane(jta);//�Ы�JScrollPane
	
	public CodeFrame(String codeStr,String title)
	{
		this.setTitle(title);//�]�m���D
		
		this.add(jsp);//�K�[JScrollPane
		
		jta.setText(codeStr);//�VJTextArea���K�[���e
		
		this.setBounds(100,100,400,300);//�]�m�j�p
		this.setVisible(true);//�]�m�i��
	}
	public static void main(String args[])
	{
		new MapColRowDialog();
	}
}
