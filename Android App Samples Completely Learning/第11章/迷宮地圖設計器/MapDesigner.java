package wyf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MapDesigner extends JFrame
implements ActionListener
{
	int row;//���
	int col;//�C��
	MapDesignPanel mdp;//�n��MapDesignPanel���ޥ�
	JScrollPane jsp;//�n��JScrollPane���ޥ�
	JButton jbGenerate=new JButton("�ͦ��a��");//�ͦ��a�ϫ��s
	JButton jbGenerateD=new JButton("�ͦ����");//�ͦ���ի��s
	JRadioButton jrBlack=new JRadioButton("��",null,true);//������s
	JRadioButton jrWhite=new JRadioButton("�D��",null,false);//�զ�����s
	JRadioButton jrCrystal=new JRadioButton("���",null,false);//��ճ����s
	ButtonGroup bg=new ButtonGroup();//�Ы�ButtonGroup
	Image icrystal;//��չϼ�
	JPanel jp=new JPanel();//�Ы�JPanel���O
	
	public MapDesigner(int row,int col)
	{
		this.row=row;//�]�m���
		this.col=col;//�]�m�C��		
		this.setTitle("3D�g�c���O�y�a�ϳ]�p��");//�]�m���D
		icrystal=new ImageIcon("img/Diamond.png").getImage();//��ժ��лx��	
		mdp=new MapDesignPanel(row,col,this);//�Ыئa�ϳ]�p�����O
		jsp=new JScrollPane(mdp);//�Ы�JScrollPane���O
		
		this.add(jsp);//�K�[JScrollPane���O
		
		jp.add(jbGenerate);//�K�[�ͦ��a�ϫ��s
		jp.add(jbGenerateD);//�K�[�ͦ���ի��s
		jp.add(jrBlack);bg.add(jrBlack);//�Vjp���K�[�¦�����s
		jp.add(jrWhite);bg.add(jrWhite);//�Vjp���K�[�զ�����s
		jp.add(jrCrystal);bg.add(jrCrystal);//�Vjp���K�[��ճ����s
		this.add(jp,BorderLayout.NORTH);//jp�K�[�i���餤
		jbGenerate.addActionListener(this);//�ͦ��a�ϫ��s�]�m��ť
		jbGenerateD.addActionListener(this);//�ͦ���ի��s�]�m��ť
		this.setBounds(10,10,800,600);//�]�m���f�j�p
		this.setVisible(true);//�]�m�i��
		this.mdp.requestFocus(true);//MapDesignPanel����J�I	
	}
	
	public void actionPerformed(ActionEvent e)
	{		
	    if(e.getSource()==this.jbGenerate)//�ͦ��a�ϥN�X
	    {
	    	String s="public static final int[][] MAP=//0 �i�q�L 1 ���i�q�L\n{";
			for(int i=0;i<mdp.row;i++)
			{
				s=s+"\n\t{";
				for(int j=0;j<mdp.col;j++)
				{
					s=s+mdp.mapData[i][j]+",";
				}
				s=s.substring(0,s.length()-1)+"},";
			}
			s=s.substring(0,s.length()-1)+"\n};";
			
			new CodeFrame(s,"3D���O�y�C���a��");			
	    }
	    else if(e.getSource()==this.jbGenerateD)
	    {//�ͦ���զ�m�N�X
	    	String s="public static final int[][] MAP_OBJECT=//��ܥi�J��զ�m���x�}\n{";
		
			
			for(int i=0;i<mdp.row;i++)
			{
				s=s+"\n\t{";
				for(int j=0;j<mdp.col;j++)
				{
					s=s+mdp.diamondMap[i][j]+",";
				}
				s=s.substring(0,s.length()-1)+"},";//�h���̫᪺�r��
			}
			s=s.substring(0,s.length()-1)+"\n};";
			new CodeFrame(s,"��դ��G�x�}");
	    }
	}
	public static void main(String args[])
	{
		new MapColRowDialog();
	} 
}
