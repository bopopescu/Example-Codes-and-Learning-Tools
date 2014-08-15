package com.bn.gjxq;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ClientAgent extends Thread
{
	GJXQActivity father;//Activity�ޥ�
	Socket sc;
	DataInputStream din;//��J�y
	DataOutputStream dout;//��X�y
	boolean flag=true;//�O�_�����ȪA�ݽu�{
	int num;//��e�s��
	boolean perFlag=false;//�O�_���ۤv���Ѽлx��,true���ۤv��,false����訫
	
	public ClientAgent(GJXQActivity father,Socket sc,DataInputStream din,DataOutputStream dout)
	{
		this.father=father;//activity���ޥ�
		this.sc=sc;//Socket�ޥ�
		this.din=din;//��J�y
		this.dout=dout;//��X�y
	}
	public void run()
	{
		while(flag)
		{
			try
			{
				String msg=din.readUTF();//���ݮ���
				if(msg.startsWith("<#ACCEPT#>"))//�p�G�O���\���[�J�F�C��,����i�J���ݬɭ�
				{
					String numStr=msg.substring(10);
					num=Integer.parseInt(numStr);//�o���e���ۤv������
					father.hd.sendEmptyMessage(0);//�o�ഫ���ݬɭ�����	
				}
				else if(msg.startsWith("<#START#>"))//�p�G�O�ĤG�Ӫ��a�i�J,����i�J3D�ɭ�
				{
					father.hd.sendEmptyMessage(1);//�o�i�J3d�ɭ�����
				}
				else if(msg.startsWith("<#PERMISIION#>"))//�C���U���Ѥl��d�ݪ��a�O�_����Ĺ�����p
				{
					perFlag=true;
					if(father.msv!=null)
					{
						switch(GuiZe.isFinish(father.msv.currBoard))
						{
						  case BLACK_WIN:
							 dout.writeUTF("<#FINISH#>0");//�p�G�O�¤�Ĺ�F
						  break;
						  case WHITE_WIN:
							 dout.writeUTF("<#FINISH#>1");///�p�G�O�դ�Ĺ�F
						  break;
						}
					}
				}
				else if(msg.startsWith("<#MOVE#>"))//���ʼлx,
				{
					String temps=msg.substring(8);
					String[] sa=temps.split("\\,");
					int srcRow=Integer.parseInt(sa[0]);
	      			int srcCol=Integer.parseInt(sa[1]);
	      			int dstRow=Integer.parseInt(sa[2]);
	      			int dstCol=Integer.parseInt(sa[3]);
	      			
	      			ChessForControl[][] currBoard=father.msv.currBoard;//����ѽL���Ѥl���ޥ�
	      			currBoard[srcRow][srcCol].y=0;//�N�Ѥl�����׳]���s
	    			currBoard[srcRow][srcCol].row=dstRow;//����m�]���s��m
	    			currBoard[srcRow][srcCol].col=dstCol;
	    			currBoard[dstRow][dstCol]=currBoard[srcRow][srcCol];//���ޥΫ��V��Ӫ���m����H
	    			currBoard[srcRow][srcCol]=null;//��Ӧ�m���Ʋդޥαˬ���
	    			father.playSound(1, 0);//���񲾰��n��
				}
				else if(msg.startsWith("<#FINISH#>"))//�p�G�O�������Ĺ�H���F.
				{
					father.msv=null;
					int pTemp=Integer.parseInt(msg.substring(10));
					if(pTemp==0&&this.num==1||pTemp==1&&this.num==2)//�p�G�OĹ��
					{
						father.hd.sendEmptyMessage(2);
					}
					else//���
					{
						father.hd.sendEmptyMessage(3);
					}
					this.flag=false;
					this.din.close();//������J�y
					this.dout.close();//������X�y
					this.sc.close();//����������sc					
				}
				else if(msg.startsWith("<#FULL#>"))
				{
					this.flag=false;
					this.din.close();//������J�y
					this.dout.close();//������X�y
					this.sc.close();//����������sc	
					father.hd.sendEmptyMessage(7);
				}
				else if(msg.startsWith("<#EXIT#>"))
				{
					father.hd.sendEmptyMessage(4);
					this.flag=false;
					this.din.close();//������J�y
					this.dout.close();//������X�y
					this.sc.close();//����������sc	
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	//�o�e����
	public void sendMessage(String msg)
	{
		try 
		{
			dout.writeUTF(msg);
		} catch (IOException e) 
		{			
			e.printStackTrace();
		}
	}
}

