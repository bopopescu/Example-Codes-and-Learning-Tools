package com.bn.reader;

public class TurnPageThread extends Thread 
{
	ReaderView readerView;
	private boolean pageflag=false;//�u�{������Ѧ�
	private boolean thirtySecond=false;//30��۰�½�������Ѧ�
	private boolean fortySecond=false;//40��۰�½�������Ѧ�
	private boolean fiftySecond=false;//50��۰�½��������
	private int sleepSpan1=30000;//30��᭫�e
	private int sleepSpan2=40000;
	private int sleepSpan3=50000;
	
	TurnPageThread(ReaderView readerView)
	{
		this.readerView=readerView;
	}
	@Override
	public void run()
	{
		while(pageflag)
		{
			try{
				if(thirtySecond)
				{
					Thread.sleep(sleepSpan1);//�ίv���w�@���
				}else
					if(fortySecond)
					{
						Thread.sleep(sleepSpan2);//�ίv���w�@���
					}else
						if(fiftySecond)
						{
							Thread.sleep(sleepSpan3);//�ίv���w�@���
						}
            }
            catch(Exception e){
            	e.printStackTrace();//���L��̫H��
            }
            
			readerView.currBook.put(readerView.currRR.pageNo, readerView.currRR);
		   
			//��l�ƨ�U�@���ƾ�
			readerView.currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
			Constant.CURRENT_LEFT_START=readerView.currRR.leftStart;//�O����eŪ��Bleftstart����
			Constant.CURRENT_PAGE=readerView.currRR.pageNo;//�O����eŪ��B��page����
		
			if(readerView.currRR.leftStart>Constant.CONTENTCOUNT){
				pageflag=false;//�p�G½��̫�@���A�h����½��
			}else
			{
				//ø�s���k��T�Ϥ�
				readerView.bmLeft=readerView.drawPage(readerView.currRR);
				readerView.bmRight=readerView.drawPage(readerView.currRR);
				readerView.repaint();
			}
		}
	}
	
	public void setPageflag(boolean pageflag) {
		this.pageflag = pageflag;
	}
	public boolean isPageflag() {
		return pageflag;
	}
	
	public void setFortySecond(boolean fortySecond) {
		this.fortySecond= fortySecond;
	}
	public boolean isFortySecond() {
		return fortySecond;
	}
	public void setThirtySecond(boolean thirtySecond) {
		this.thirtySecond = thirtySecond;
	}
	public boolean isThirtySecond() {
		return thirtySecond;
	}
	public void setFiftySecond(boolean fiftySecond) {
		this.fiftySecond = fiftySecond;
	}
	public boolean isFiftySecond() {
		return fiftySecond;
	}
}

