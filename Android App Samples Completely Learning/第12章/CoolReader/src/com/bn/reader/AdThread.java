package com.bn.reader;

public class AdThread  extends Thread
{
	boolean adFlag=true;//�����s���лx��
	ReaderView reader;//�\Ū�ɭ����ޥ�
	//�ЫاY�i�o��ޥ�
	public AdThread(ReaderView reader)//�ЫاY�i�o��ޥ�
	{
		this.reader=reader;//����ReaderView���ޥ�
	}
	//�u�{�B��
	public void run()//�u�{�B��
	{ 
		while(adFlag)
		{				
		 try{
			 Constant.NUM=(Constant.NUM+1)%reader.ad.length;
			 if(reader.repaintAdFlag){				 
				 reader.repaint();//��ø�ɭ�
			 }
			 Thread.sleep(1000);//���j1000ms���sø�s�@��
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	//�ΨӰ���u�{
	public synchronized void stopCurrentThread() {
        this.adFlag = false;//�ΨӰ���u�{
    }
}
