package wyf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MapColRowDialog extends JFrame//�ͦ���ƩM�C�ƹ�ܮ�
implements ActionListener
{
	JLabel jlRow=new JLabel("�a�Ϧ��");//�a�Ϧ��
	JLabel jlCol=new JLabel("�a�ϦC��");//�a�ϦC��
	JTextField jtfRow=new JTextField("20");//�Ыئ��JTextField
	JTextField jtfCol=new JTextField("20");//�Ыئ��JTextField
	
	JButton jbOk=new JButton("�T�w");//�ЫؽT�w���s
	
	public MapColRowDialog()//�c�y��
	{
		this.setTitle("3D���O�y�a�ϳ]�p��");
		
		this.setLayout(null);//�]�m�G������
		jlRow.setBounds(10,5,60,20);//�]�m��m
		this.add(jlRow);//�K�[jlRow
		jtfRow.setBounds(70,5,100,20);//�]�m��m
		this.add(jtfRow);//�K�[jtfRow
		
		jlCol.setBounds(10,30,60,20);//�]�m��m
		this.add(jlCol);//�K�[jlCol
		jtfCol.setBounds(70,30,100,20);//�]�m��m
		this.add(jtfCol);//�K�[jtfCol
		
		jbOk.setBounds(180,5,60,20);//�]�m��m
		this.add(jbOk);//�K�[jbOk
		jbOk.addActionListener(this);//�K�[��ť��
		
		this.setBounds(440,320,300,100);//�]�m��m
		this.setVisible(true);//�]�m�i��
		
	}
	
	public void actionPerformed(ActionEvent e)//��ť�B�z�N�X
	{
		int row=Integer.parseInt(jtfRow.getText().trim());//������
		int col=Integer.parseInt(jtfCol.getText().trim());//����C��
		
		new MapDesigner(row,col);//�Ы�MapDesigner�ﹳ
		this.dispose();//�����f����
	}
	
	public static void main(String args[])
	{
		new MapColRowDialog();//�Ыعﹳ
	}   
}
