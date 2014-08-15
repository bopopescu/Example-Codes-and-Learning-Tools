package com.bn.reader;

import java.util.HashMap;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.widget.Toast;
import static com.bn.reader.Constant.*;

enum TurnDir{
	noTurning,left,right,//��½���A�V�e½�A�V��½
}

public class ReaderView extends SurfaceView implements SurfaceHolder.Callback
{
	ReaderActivity readerActivity;//ReaderActivity���ޥ�
	Paint paint;//�e�����ޥ�
	//�N�nø�s�����k��T�Ϫ��ޥ�
	Bitmap bmLeft;//���䪺
	Bitmap bmRight;//�k�䪺
	
	ReadRecord currRR;//��e���ƾ�
	
	//½�����Ψ쪺�{�ɹﹳ
	Bitmap bmLeft_temp;//����Ϥ��{�ɤޥ�
	Bitmap bmRight_temp;//�k��Ϥ��{�ɤޥ�
	
	ReadRecord currRR_temp;//�O��ReadRecord���@���{�ɹﹳ
	Bitmap bmBack;// �����Ϥ�
	Bitmap title;// ���Y�Ϥ�

	AdThread at;//�s�i������s�u�{
	//�s�i�Ϥ��Ʋ�
	int ad[]={R.drawable.ad_a,R.drawable.ad_b,R.drawable.ad_c,R.drawable.ad_d,
			R.drawable.ad_e,R.drawable.ad_f,R.drawable.ad_g,R.drawable.ad_h};
	//�[�����s�i�Ϥ��Ʋ�
	Bitmap adb[]=new Bitmap[ad.length];
	
	//��e�o�Ӥ奻���]�����ѡ^���\Ū�ƾ�
	HashMap<Integer,ReadRecord> currBook=new HashMap<Integer,ReadRecord>();
	
	//��e½��Ĳ���I����
	float ax=-1;
	float ay=-1;	
	//�k�U������
	int bx;
	int by;
	
	int[] cd;//c�Bd���I���мƲ�,�䤤c�Bd���I���O��½��u�P�����e�M�������I
	TurnDir turnDir=TurnDir.noTurning;//½����V�A�T�|����
	boolean repaintAdFlag=true;//ø�s�s�i���лx
	//ReaderView���c�y��k
    public ReaderView(ReaderActivity readerActivity) {
		super(readerActivity);		
		this.readerActivity=readerActivity;
		
		this.getHolder().addCallback((Callback) this);
		//�Ыصe��
		paint=new Paint();		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) 
	{
		at=new AdThread(this);//�Ыؼs�i��s�u�{
		bmBack=PicLoadUtil.LoadBitmap(this.getResources(), BITMAP);//�۾A���̹����I���Ϥ�
		bmBack=PicLoadUtil.scaleToFit(bmBack, PAGE_WIDTH, PAGE_HEIGHT);
		
		title=PicLoadUtil.LoadBitmap(this.getResources(), R.drawable.bt);//�۾A���̹������Y�Ϥ�
		title=PicLoadUtil.scaleToFit(title, SCREEN_WIDTH, BLANK);
		
		for(int i=0;i<ad.length;i++)//�۾A���̹����s�i�Ϥ�
		{
			adb[i]=PicLoadUtil.LoadBitmap(this.getResources(), ad[i]);
			adb[i]=PicLoadUtil.scaleToFit(adb[i], AD_WIDTH, BLANK);
		}
		//��l�ƨ��e����X��
		currRR=new ReadRecord(CURRENT_LEFT_START,0,CURRENT_PAGE);
		
		if(CURRENT_PAGE==0)//�p�G�O�Ĥ@�����}�Y�@����
		{
			currBook.put(currRR.pageNo, currRR);//�Ĥ@�����H����JhashMap��			
		}
		
		//ø�s���k��T�Ϥ�
		bmLeft=this.drawPage(currRR);
		bmRight=this.drawPage(currRR);
		repaint();
		at.start();//�}�Ҽs�i��s�u�{
	}
    @Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
    }

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		at.stopCurrentThread();//�פ�i�{��s
	}
	public void onDraw(Canvas canvas)
	{
		synchronized(paint)
		{	
			canvas.drawColor(Color.BLACK);//���Ŭɭ�
			canvas.drawBitmap(title, 0, 0, paint);//ø�s���Y�Ϥ�
			canvas.drawBitmap(adb[NUM],Constant.SCREEN_WIDTH-AD_WIDTH, 0, paint);//ø�s�s�i��
			drawcut_line(canvas);//ø�s���νu
			drawTitle(canvas);//ø�s�D��			
			if(turnDir==TurnDir.right)
			{	
				canvas.drawBitmap(bmLeft, LEFT_OR_RIGHT_X, BLANK, paint);//ø�s�����۩w�q���Ϥ�
	
				//�ھ�a�I����mø�s�k��ΨӬݪ���====================begin===========================
				//�O�s�e�����A
				canvas.save();
				//�s�ؤ@�Ӹ��|
				Path path2=new Path();
				//�����|����d��e�Bf�Bc�Bd�Bg
				path2.moveTo(RIGHT_OR_LEFT_x,0);
				path2.lineTo(RIGHT_OR_LEFT_x, PAGE_HEIGHT+BLANK);			
				path2.lineTo(cd[0], cd[1]);
				path2.lineTo(cd[2], cd[3]);
				path2.lineTo(RIGHT_OR_LEFT_x+PAGE_WIDTH, BLANK);
				path2.lineTo(RIGHT_OR_LEFT_x,0);
				//���e���]�mø�s�ŵ�
				canvas.clipPath(path2);
				
				canvas.drawBitmap(bmRight, RIGHT_OR_LEFT_x,BLANK, paint);//ø�s�k���Ϥ�
				//��_�e�����A
				canvas.restore();
				//�ھ�a�I����mø�s�k��ΨӬݪ���=====================end============================
				
				
				//�ھ�a�I����mø�s�Q½�X�Ӫ��Ϫ����e��===============begin==========================
				//�p��ϹL�ӭ���������ץ���
				float angle=(float)Math.toDegrees(Math.atan((ay-cd[3])/(ax-cd[2])));
				//�Ыبó]�m����x�}
				Matrix m1=new Matrix();
				m1.setRotate(90+angle, LEFT_OR_RIGHT_X,PAGE_HEIGHT);
				//�N�ϹL�ӱ��୶�����U�����Ĳ���I�W
				Matrix m2=new Matrix();
				m2.setTranslate
				(
					ax,	
					ay-PAGE_HEIGHT
				);
				//�Ы��`�x�}
				Matrix mz=new Matrix();
				mz.setConcat(m2, m1);
				//�O�s�e�����A
				canvas.save();
				//�����|����d��a�Bc�Bd
				Path path3=new Path();
				path3.moveTo(ax,ay);		
				path3.lineTo(cd[0], cd[1]);
				path3.lineTo(cd[2], cd[3]);		
				path3.lineTo(ax,ay);
				canvas.clipPath(path3);
							
	
				canvas.drawBitmap(bmLeft_temp, mz, paint);//ø�s�����۩w�q���Ϥ�
	
				//��_�e�����A
				canvas.restore();
				//�ھ�a�I����mø�s�Q½�X�Ӫ��Ϫ����e��================end===========================
				
				
				//�ھ�a�I����mø�s�̤U���n�Q½�X�Ӫ������e��================begin====================
				//�O�s�e�����A
				canvas.save();
				//�s�ؤ@�Ӹ��|
				Path path1=new Path();
				//�����|����d��c�Bb�Bd
				path1.moveTo(cd[0], cd[1]);
				path1.lineTo(bx, by);
				path1.lineTo(cd[2], cd[3]);
				path1.lineTo(cd[0], cd[1]);
				//���e���]�mø�s�ŵ�
				canvas.clipPath(path1);
				paint.setAlpha(220);
				
				canvas.drawBitmap(bmRight_temp, RIGHT_OR_LEFT_x,BLANK, paint);//ø�s�k���Ϥ�
				paint.setAlpha(255);
				//��_�e�����A
				canvas.restore();	
				//�ھ�a�I����mø�s�̤U���n�Q½�X�Ӫ������e��=================end=====================			
	
			}//�p�G�V��½
			else if(turnDir==TurnDir.left)			
			{
				//�ھ�a�I����mø�s����ΨӬݪ���====================begin===========================
				//�O�s�e�����A
				canvas.save();
				//�s�ؤ@�Ӹ��|
				Path path2=new Path();
				//�����|����d��e�Bf�Bc�Bd�Bg
				path2.moveTo(PAGE_WIDTH,0);
				path2.lineTo(PAGE_WIDTH, PAGE_HEIGHT+BLANK);			
				path2.lineTo(cd[0], cd[1]);
				path2.lineTo(cd[2], cd[3]);
				path2.lineTo(0, BLANK);	
				path2.lineTo(PAGE_WIDTH,0);	
				//���e���]�mø�s�ŵ�
				canvas.clipPath(path2);
				canvas.drawBitmap(bmLeft, LEFT_OR_RIGHT_X, BLANK, paint);//ø�s�����۩w�q���Ϥ�
				//��_�e�����A
				canvas.restore();
				//�ھ�a�I����mø�s����ΨӬݪ���=====================end============================
				
				//ø�s�k��ΨӬݪ���
				canvas.drawBitmap(bmRight, RIGHT_OR_LEFT_x,BLANK, paint);
				
				//�ھ�a�I����mø�s�̤U���n�Q½�X�Ӫ������e��================begin====================
				//�O�s�e�����A
				canvas.save();
				//�s�ؤ@�Ӹ��|
				Path path1=new Path();
				//�����|����d��c�Bb�Bd
				path1.moveTo(cd[0], cd[1]);
				path1.lineTo(bx, by);
				path1.lineTo(cd[2], cd[3]);
				path1.lineTo(cd[0], cd[1]);
				//���e���]�mø�s�ŵ�
				canvas.clipPath(path1);
				paint.setAlpha(220);//½�X�������b�z��
				
				canvas.drawBitmap(bmLeft_temp, LEFT_OR_RIGHT_X, BLANK, paint);//ø�s�����۩w�q���Ϥ�
			
				paint.setAlpha(255);
				//��_�e�����A
				canvas.restore();
				//�ھ�a�I����mø�s�̤U���n�Q½�X�Ӫ������e��=================end=====================
				
				
				//�ھ�a�I����mø�s�Q½�X�Ӫ��Ϫ����e��===============begin==========================
				//�p��ϹL�ӭ���������ץ���
				float angle=(float)Math.toDegrees(Math.atan((ax-cd[0])/(ay-cd[1])));//�V�e½���ɡA�p�⨤�ץΪ�c�I����
				//�Ыبó]�m����x�}
				Matrix m1=new Matrix();
				m1.setRotate(-90-angle, PAGE_WIDTH ,PAGE_HEIGHT );//�H�Ϥ��k�U�������त���I�A�f�ɰw����90+angle
				//�N�ϹL�ӱ��୶���k�U�����Ĳ���I�W
				Matrix m2=new Matrix();
				m2.setTranslate
				(
					ax-PAGE_WIDTH ,	
					ay-PAGE_HEIGHT
				);
				//�Ы��`�x�}
				Matrix mz=new Matrix();
				mz.setConcat(m2, m1);
				//�O�s�e�����A
				canvas.save();
				//�����|����d��a�Bc�Bd
				Path path3=new Path();
				path3.moveTo(ax,ay);		
				path3.lineTo(cd[0], cd[1]);
				path3.lineTo(cd[2], cd[3]);		
				path3.lineTo(ax,ay);
				canvas.clipPath(path3);
				
				canvas.drawBitmap(bmRight_temp, mz, paint);
				//��_�e�����A
				canvas.restore();
				//�ھ�a�I����mø�s�Q½�X�Ӫ��Ϫ����e��================end===========================
			}
			else
			{
				canvas.drawBitmap(bmLeft, LEFT_OR_RIGHT_X, BLANK, paint);//ø�s�����۩w�q���Ϥ�
				canvas.drawBitmap(bmRight, RIGHT_OR_LEFT_x,BLANK, paint);//ø�s�k���۩w�q���Ϥ�
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		
		 switch(keyCode)
		 {
		    case 4:
		    	readerActivity.showDialog(readerActivity.EXIT_READER);//�h�X��ܮ�
				break;
		    case 22:
		    	repaintAdFlag=false;//ø�s�s�i���лx�]��false
				//��l�ƨ�U�@���ƾ�
		    	currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
	
		    	Constant.CURRENT_LEFT_START=currRR.leftStart;//�O����eŪ��Bleftstart����
		    	Constant.CURRENT_PAGE=currRR.pageNo;//�O����eŪ��B��page����
	    	
		    	currBook.put(currRR.pageNo, currRR);//��e�����H���[�JhashMap
		    	
		    	
		    	if(currRR.leftStart>Constant.CONTENTCOUNT){
				   Toast.makeText
				   (
						this.getContext(), 
						"�w�g��̫�@���F�A���i�H�A����F�I", 
						Toast.LENGTH_SHORT
					).show();
				}else
				{
					//ø�s���k��T�Ϥ�
					bmLeft=drawPage(currRR);
					bmRight=drawPage(currRR);
					repaint();
				}
		    	repaintAdFlag=true;//�����Ϥ��A��ø
				break;
			    case 21:
			    	repaintAdFlag=false;//ø�s�s�i���лx�]��false
				   if(currRR.pageNo==0){
						Toast.makeText
						(
							this.getContext(), 
							"�w�g��Ĥ@���A���i�H�A���e½�F�I", 
							Toast.LENGTH_SHORT
						).show();				
					}
					else
					{
						currRR=currBook.get(currRR.pageNo-1);
						
						Constant.CURRENT_LEFT_START=currRR.leftStart;//�O����eŪ��Bleftstart����
						Constant.CURRENT_PAGE=currRR.pageNo;//�O����eŪ��B��page����

						currRR.isLeft=true;
						bmLeft=drawPage(currRR);
						bmRight=drawPage(currRR);
						repaint();
					}
				   repaintAdFlag=true;//�����Ϥ��A��ø
				   break;		    	
		    case 82:
		    	readerActivity.openOptionsMenu();
				   break; 
		 }
		   return true;
	}
	public boolean onTouchEvent(MotionEvent e) 
	{  
		repaintAdFlag=false;
    	float x = e.getX();//���Ĳ���IX����
        float y = e.getY();//���Ĳ���IY����    	
        
        switch (e.getAction()) 
        {
            case MotionEvent.ACTION_DOWN:
            	/*
            	 * ����U�ɧP�_�O�n�V��½�٬O�n�V�e½�A
            	 * �A��l�ƹ�����b�I�����Э�
            	 */
      	
            	if(x>RIGHT_OR_LEFT_x )//�p�G���b�k��A�T�w���n�V��½��
            	{
            		//��l�Ƭ��k�U������
            		bx=SCREEN_WIDTH;
            		by=SCREEN_HEIGHT;
            	}
            	else//�p�G���b����A�T�w���n�V�e½��
            	{
            		//��l�Ƭ����U������
            		bx=0;
            		by=PAGE_HEIGHT+BLANK;
            	}
            	//�p��c�Bd���I����
            	cd=CalUtil.calCD(x, y, bx, by);

            	//�Y�즸���U����m�b�k�U�����w�d�򤺫h���\ø�s½���ĪG
            	if(x>PAGE_WIDTH*1.7f&&x<SCREEN_WIDTH&&cd[0]>RIGHT_OR_LEFT_x)   
                {
            		if(Constant.nextPageStart>Constant.CONTENTCOUNT){
        				Toast.makeText
        				(
        					this.getContext(), 
        					"�w�g��̫�@���F�A���i�H�A����F�I", 
        					Toast.LENGTH_SHORT
        				).show();
        				repaintAdFlag=true;//�����Ϥ��A��ø
        				return true;
        			}

            	turnDir=TurnDir.right;             	   
            	 //ø�s�{�ɤU�@����ReadRecord������H
       			currRR_temp=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
       			//�O�@Constant.nextPageNo�BConstant.nextPageNo��ӭ�
           		int t1=Constant.nextPageNo;
           		int t2=Constant.nextPageStart;
           		//�ЫؤU�@������i�Ϥ�
       			bmLeft_temp=drawPage(currRR_temp);        			
       			bmRight_temp=drawPage(currRR_temp);        			
				Constant.nextPageNo=t1;
           		Constant.nextPageStart=t2;        	
            	   
                }//�Y�즸���U����m�b���U�����w�d�򤺫h���\ø�s�V�e��½���ĪG
            	else 
            		if(x<PAGE_WIDTH*0.3&&cd[0]<PAGE_WIDTH)
                {
        			if(currRR.pageNo<=0){
						Toast.makeText
						(
							this.getContext(), 
							"�w�g��Ĥ@���A���i�H�A���e½�F�I", 
							Toast.LENGTH_SHORT
						).show();	
						repaintAdFlag=true;//�����Ϥ��A��ø
						return true;
					}
        			
            		turnDir=TurnDir.left;
            		
            		currRR_temp=currBook.get(currRR.pageNo-1);

            		int t1=Constant.nextPageNo;
            		int t2=Constant.nextPageStart;
            		currRR_temp.isLeft=true;    		
					bmLeft_temp=drawPage(currRR_temp);
					bmRight_temp=drawPage(currRR_temp);
					Constant.nextPageNo=t1;
            		Constant.nextPageStart=t2;	
                }
            	ax=x;
          	   	ay=y;          	 
            break;        
            case MotionEvent.ACTION_MOVE: 
            	//½���ɰʺA�p��c�Bd���I����
            	cd=CalUtil.calCD(x, y, bx, by);
            	
            	//�Y���ʹL�{���S�����ȫh���\ø�s½���ĪG
                if(x>0&&x<SCREEN_WIDTH&&
             		   (turnDir==TurnDir.right&&cd[0]>PAGE_WIDTH)||//�V��½���ɨS������
             		   turnDir==TurnDir.left&&cd[0]<PAGE_WIDTH)//�V�e½���ɨS������ 
            	
                {
             	   ax=x;
             	   ay=y;            	   
                }
                else
                {
             	   turnDir=TurnDir.noTurning;
                }
              //�Y��_����m�b������w�d��h��I�V��½���åB�U�@�������ޭȤp��峹�`�r��
                if(turnDir==TurnDir.right && ax<PAGE_WIDTH*0.1f)	
                { 
                	currRR=new ReadRecord(Constant.nextPageStart,0,Constant.nextPageNo);
  				   	Constant.CURRENT_LEFT_START=currRR.leftStart;//�O����eŪ��Bleftstart����
  				   	Constant.CURRENT_PAGE=currRR.pageNo;//�O����eŪ��B��page����
  				   	
  				   	currBook.put(currRR.pageNo, currRR);//��e�����H���[�JhashMap
			   
					//ø�s���k��T�Ϥ�
					bmLeft=drawPage(currRR);
					bmRight=drawPage(currRR);  
					
					turnDir=TurnDir.noTurning;
                }
                //�Y��_����m�b�k����w�d��h��I�V�e½��
                else if(turnDir==TurnDir.left && ax>PAGE_WIDTH*1.9f)	
                {
					currRR=currBook.get(currRR.pageNo-1);
					
					Constant.CURRENT_LEFT_START=currRR.leftStart;//�O����eŪ��Bleftstart����
					Constant.CURRENT_PAGE=currRR.pageNo;//�O����eŪ��B��page����
					
					currRR.isLeft=true;
					bmLeft=drawPage(currRR);
					bmRight=drawPage(currRR);
					
					turnDir=TurnDir.noTurning;
                }
            break;
            case MotionEvent.ACTION_UP:            	
                turnDir=TurnDir.noTurning;                 
              break;              
        }  
        this.repaint();
        repaintAdFlag=true;//�����Ϥ��A��ø
        return true;
    }
   //ø�sBitmap����k
	public Bitmap drawPage(ReadRecord rr)
	{
		int start=0;
		if(rr.isLeft)
		{
			start=rr.leftStart;
		}
		else
		{
			start=rr.rightStart;
		}
		
		Bitmap bm=Bitmap.createBitmap(PAGE_WIDTH, PAGE_HEIGHT,Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(bm);
		
		canvas.drawBitmap(bmBack,0,0, paint);
		canvas.drawBitmap(bmBack,0,0, paint);
		
		try
		{
			synchronized(paint)
			{
				String str=null;
				paint.setColor(COLOR);
				paint.setTextSize(TEXT_SIZE);//�]�m�r���j�p
				if(Constant.FILE_PATH==null)
				{
					str=TextLoadUtil.loadFromSDFile(this,start,PAGE_LENGTH,Constant.DIRECTIONSNAME);//Ū������
					CONTENTCOUNT=TextLoadUtil.getCharacterCountApk(this, Constant.DIRECTIONSNAME);
				}else//�_�hŪ����
				{
					str=TextLoadUtil.readFragment(start, PAGE_LENGTH, FILE_PATH);//Ū������
				}
				int index=0;
				int index2=0;//�z\n'����Ӧr��
				char c=str.charAt(index);
				boolean finishFlag=false;		
				int currRow=0;
				int currX=0;
				while(!finishFlag)
				{
					if(c=='\n')  
					{//�p�G�O���� 
						currRow++;
						currX=0;
						index2++;
					}
					else if((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0'))
					{//�^��j�p�g�μƦr
						canvas.drawText(c+"", currX+TEXT_SIZE/2, currRow*TEXT_SIZE+TEXT_SIZE, paint);
						currX=currX+TEXT_SPACE_BETWEEN_EN;
					}
					else
					{//����
						canvas.drawText(c+"", currX+TEXT_SIZE/2, currRow*TEXT_SIZE+TEXT_SIZE, paint);
						currX=currX+TEXT_SPACE_BETWEEN_CN;
					}
					index++;
					c=str.charAt(index);
					
					if(currX>=Constant.PAGE_WIDTH-TEXT_SIZE)
					{
						currRow=currRow+1;
						currX=0;
					}
					if(currRow==ROWS)
					{
						finishFlag=true;
						if(rr.isLeft)
						{
							rr.isLeft=false;
							rr.rightStart=index+index2+rr.leftStart;
						}
						else
						{
							nextPageStart=rr.rightStart+index+index2;
							nextPageNo=rr.pageNo+1;
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return bm;
	}
	
	   //ø�s������Bitmap����k
	public void drawVirtualPage(ReadRecord rr)
	{
		int start=0;
		if(rr.isLeft)
		{
			start=rr.leftStart;
		}
		else
		{
			start=rr.rightStart;
		}
		
		try
		{
			synchronized(paint)
			{
				String str=null;
				paint.setColor(COLOR);
				paint.setTextSize(TEXT_SIZE);//�]�m�r���j�p
				
				
				str=TextLoadUtil.readFragment(start, PAGE_LENGTH, FILE_PATH);//Ū������
				
				int index=0;
				int index2=0;//�z\n'����Ӧr��
				char c=str.charAt(index);
				boolean finishFlag=false;		
				int currRow=0;
				int currX=0;
				while(!finishFlag)
				{
					if(c=='\n')  
					{//�p�G�O���� 
						currRow++;
						currX=0;
						index2++;
					}
					else if((c<='z'&&c>='a')||(c<='Z'&&c>='A')||(c<='9'&&c>='0'))
					{//�^��j�p�g�μƦr
						currX=currX+TEXT_SPACE_BETWEEN_EN;
					}
					else
					{//����
						currX=currX+TEXT_SPACE_BETWEEN_CN;
					}
					index++;
					c=str.charAt(index);
					
					if(currX>=Constant.PAGE_WIDTH-TEXT_SIZE)
					{
						currRow=currRow+1;
						currX=0;
					}
					if(currRow==ROWS)
					{
						finishFlag=true;
						if(rr.isLeft)
						{
							rr.isLeft=false;
							rr.rightStart=index+index2+rr.leftStart;
						}
						else
						{
							nextPageStart=rr.rightStart+index+index2;
							nextPageNo=rr.pageNo+1;
						}
					}
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	public void drawTitle(Canvas canvas)
	{
		try
		{
			synchronized(paint)
			{
				paint.setColor(Color.BLACK);
				paint.setTextSize(TITLE_SIZE);
				canvas.drawText("��Ū�\Ū��", 0, TITLE_SIZE, paint);
				if(Constant.FILE_PATH==null)
				{
					canvas.drawText("����", Constant.SCREEN_WIDTH/2-TITLE_SIZE, TITLE_SIZE, paint);//ø�s�u�����v
					
				}else//�_�h�Ѽg�峹txt���W�r
				{
					//�N�ѦW�r�j���e�b������m
					canvas.drawText(Constant.TEXTNAME,Constant.SCREEN_WIDTH/2-3*TITLE_SIZE,TITLE_SIZE, paint);//����ݭn��
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void drawcut_line(Canvas canvas)
	{
		try
		{
			synchronized(paint)
			{
				paint.setColor(Color.YELLOW);//ø�s���νu
				canvas.drawRect(CENTER_LEFT_X, CENTER_LEFT_Y, CENTER_RIGHT_X, CENTER_RIGHT_Y, paint);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	//���sø�s����k
    public void repaint()
	{
		Canvas canvas=this.getHolder().lockCanvas();
		try
		{
			synchronized(canvas)
			{
				onDraw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}
    
}

