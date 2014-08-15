package com.bn.gjxq;

import java.io.IOException;
import java.io.InputStream;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Toast;
import static com.bn.gjxq.Constant.*;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class MySurfaceView extends GLSurfaceView
{
	private final float TOUCH_SCALE_FACTOR = 180.0f/320*3;//�����Y����
    private SceneRenderer mRenderer;//������V��
	
	private float mPreviousY;//�W����Ĳ����mY����
    private float mPreviousX;//�W����Ĳ����mY����
    
    static float cx;//�ṳ��x����
    static float cy;//�ṳ��y����
    static float cz;//�ṳ��z����
    
    static float tx;//�[��ؼ��Ix����  
    static float ty;//�[��ؼ��Iy����
    static float tz;//�[��ؼ��Iz����
 
    static float yAngle=0;//��쨤
    static float xAngle=30;//����
    static boolean OKMove=false;//�O�_�ݭn���ʴѤl�лx
    
    static int herosquareZ=7;//�D����ή�l����l���
    static int herosquareX=0;//�D����ή�l����l�C��
    
    static int herosquarez;//��T�w�F�n���Y�ӭ^���ɡA�O���o�ӭ^������өҦb���СC
    static int herosquarex;
    
    static LoadedObjectVertexNormalTexture[] qizi;//�ҫ��Ʋ�
    ChessboardForDraw cb;//�ѽL
    
    ChessForControl[][] currBoard;//�Ѥl�Ʋ�
    static int[][] road;
    GJXQActivity father;//activity�ޥ�
  
	public MySurfaceView(Context context)
	{ 
        super(context);
        father=(GJXQActivity)context;//Activity�ﹳ
        mRenderer = new SceneRenderer();	
        setRenderer(mRenderer);				 	
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�m���s���V�Ҧ�
        tx=0;//�[��ؼ��Ix����  
	    ty=0;//�����[��ؼ��Iy����
	    tz=0;//�[��ؼ��Iz����
	    road=new int[8][8];

	    //��l�ƨ��Ѹ��|
	    if(father.ca.num==1)
	    {
	    	herosquareZ=1;//�p�G�O�¤�,�����l�w��b1,0��l�̭�
	    	herosquareX=0;
	        //�p�G��e���¤�,�h���гB��¤��m
	    }
	    else
	    {
	    	herosquareZ=6;//�p�G�O�դ�,�N�����l��J6,0��l�̭�
	    	herosquareX=0;
	    }
	    road[herosquareZ][herosquareX]=1;//���l��m�������w���  
	    yAngle=((father.ca.num%2)==1)?180:0;//�p�G��e���¤�,�h�ṳ����V������180.
		cx=(float)(tx+Math.cos(Math.toRadians(xAngle))*Math.sin(Math.toRadians(yAngle))*DISTANCE);//�[���x���� 
        cz=(float)(tz+Math.cos(Math.toRadians(xAngle))*Math.cos(Math.toRadians(yAngle))*DISTANCE);//�[���z���� 
        cy=(float)(ty+Math.sin(Math.toRadians(xAngle))*DISTANCE);  
       //��l�ƴѽL
        cb=new ChessboardForDraw();
        initBoard();//��l�ƴѤl
    }
	
	public void initBoard()//��l�ƴѤl�Ʋժ����
	{
		currBoard=new ChessForControl[8][8];
		
		currBoard[0][0]=new ChessForControl(qizi[0] ,0,0,0);//�¨�
		currBoard[0][1]=new ChessForControl(qizi[1] ,1,0,1);//�°�
		currBoard[0][2]=new ChessForControl(qizi[2] ,2,0,2);//�¶H
		currBoard[0][3]=new ChessForControl(qizi[3] ,3,0,3);//�«�
		currBoard[0][4]=new ChessForControl(qizi[4] ,4,0,4);//�¤�
		currBoard[0][5]=new ChessForControl(qizi[2] ,2,0,5);//�¶H
		currBoard[0][6]=new ChessForControl(qizi[1] ,1,0,6);//�°�
		currBoard[0][7]=new ChessForControl(qizi[0] ,0,0,7);//�¨�
		
		currBoard[1][0]=new ChessForControl(qizi[5] ,5,1,0);//�§L
		currBoard[1][1]=new ChessForControl(qizi[5] ,5,1,1);//�§L
		currBoard[1][2]=new ChessForControl(qizi[5] ,5,1,2);//�§L
		currBoard[1][3]=new ChessForControl(qizi[5] ,5,1,3);//�§L
		currBoard[1][4]=new ChessForControl(qizi[5] ,5,1,4);//�§L
		currBoard[1][5]=new ChessForControl(qizi[5] ,5,1,5);//�§L
		currBoard[1][6]=new ChessForControl(qizi[5] ,5,1,6);//�§L
		currBoard[1][7]=new ChessForControl(qizi[5] ,5,1,7);//�§L
		
		currBoard[6][0]=new ChessForControl(qizi[5] ,11,6,0);//�էL
		currBoard[6][1]=new ChessForControl(qizi[5] ,11,6,1);//�էL
		currBoard[6][2]=new ChessForControl(qizi[5] ,11,6,2);//�էL
		currBoard[6][3]=new ChessForControl(qizi[5] ,11,6,3);//�էL
		currBoard[6][4]=new ChessForControl(qizi[5] ,11,6,4);//�էL
		currBoard[6][5]=new ChessForControl(qizi[5] ,11,6,5);//�էL
		currBoard[6][6]=new ChessForControl(qizi[5] ,11,6,6);//�էL
		currBoard[6][7]=new ChessForControl(qizi[5] ,11,6,7);//�էL
		
		currBoard[7][0]=new ChessForControl(qizi[0] ,6,7,0);//�ը�
		currBoard[7][1]=new ChessForControl(qizi[1] ,7,7,1);//�հ�
		currBoard[7][2]=new ChessForControl(qizi[2] ,8,7,2);//�նH
		currBoard[7][3]=new ChessForControl(qizi[3] ,9,7,3);//�ի�
		currBoard[7][4]=new ChessForControl(qizi[4] ,10,7,4);//�դ�
		currBoard[7][5]=new ChessForControl(qizi[2] ,8,7,5);//�նH
		currBoard[7][6]=new ChessForControl(qizi[1] ,7,7,6);//�հ�
		currBoard[7][7]=new ChessForControl(qizi[0] ,6,7,7);//�ը�	
	}

	@Override 
    public boolean onTouchEvent(MotionEvent e) 
	{
        float y = e.getY();
        float x = e.getX();
        switch (e.getAction())
        {
        case MotionEvent.ACTION_MOVE:
            float dy = y - mPreviousY;//�p��Ĳ����Y�첾
            float dx = x - mPreviousX;//�p��Ĳ����X�첾
            yAngle += dx * TOUCH_SCALE_FACTOR;//��������
            xAngle += dy * TOUCH_SCALE_FACTOR;//��쨤����    
            if(xAngle+dy * TOUCH_SCALE_FACTOR<10)//�p�G��e�ṳ�������p��15,�N������j�15
            {
            	xAngle=10;
            }
            if(xAngle+dy * TOUCH_SCALE_FACTOR>85)//���ṳ�������j��85,�N��j�85;
            {
            	xAngle=85;
            }
            requestRender();//��ø�e��
        }
        mPreviousY = y;//�O��Ĳ������m
        mPreviousX = x;//�O��Ĳ������m    
        cx=(float)(tx+Math.cos(Math.toRadians(xAngle))*Math.sin(Math.toRadians(yAngle))*DISTANCE);//�ṳ��x���� 
        cz=(float)(tz+Math.cos(Math.toRadians(xAngle))*Math.cos(Math.toRadians(yAngle))*DISTANCE);//�ṳ��z���� 
        cy=(float)(ty+Math.sin(Math.toRadians(xAngle))*DISTANCE);//�ṳ��y���� 
        return true;
    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent e)
    { 
		if(!father.ca.perFlag)//��e���O�Ӫ��a�U��,���򪽱���^,���a�L����ޱ��v�Q
		{
			return false;
		}
		if(keyCode==19||keyCode==20||keyCode==21||keyCode==22)//�p�G�O���U�e�ᥪ�k�ضi�沾�ʪ����p.
		{
			road[herosquareZ][herosquareX]=0;//�N���B����l�h��
			if(keyCode==19)//���O�N��e���A�Ჾ�A�����A�k��
    		{
				herosquareZ++;
    		}
    		else if(keyCode==20)//�V�Ჾ��
    		{
    			herosquareZ--;
    		}
    		else if(keyCode==21)//�V������
    		{
    			herosquareX++;
    		}
    		else if(keyCode==22)//�V�k����
    		{
    			herosquareX--;
    		}
    	
    		if(herosquareX<0)//�p�G���ʦb����ɤF,���򤣥i����,
    		{
    			herosquareX=0;
			}
			if(herosquareX>7)//���ʨ�F�k��ɤF
			{
				herosquareX=7;
			}
			if(herosquareZ<0)//���ʨ�F�W��ɤF
			{
				herosquareZ=0;
			}
			if(herosquareZ>7)//���ʨ�F�U��ɤF
			{
				herosquareZ=7;
			}
			
			road[herosquareZ][herosquareX]=1;//���l���ʨ��e��m
			
		}
		else if(keyCode==62)//�p�G�O�Ů���,�o�̥N���a�ޱ��Ѥl����.
		{
			if(!OKMove&&currBoard[herosquareZ][herosquareX]!=null)//�Ĥ@�����U�Ů�
			{
				if(currBoard[herosquareZ][herosquareX].chessType>=(father.ca.num%2)*6
				   &&currBoard[herosquareZ][herosquareX].chessType<(father.ca.num%2+1)*6)
					//�Ĥ@�����U�Ů�,����l������,�åB�ӴѤl�O��誺,���򤣥i�ޱ�
						{
							Toast.makeText
							(
									father, 
									"����ʧO�H���Ѥl,�Э��s�ާ@!", 
									Toast.LENGTH_SHORT
							).show();
						
						}
				else//�p�G�O�ۤv���Ѥl,�ӥB�O�Ĥ@���лx,����O����e��m
					{OKMove=true;//�ܦ��лx�쬰true
					herosquarez=herosquareZ;//�åB�O���аO���^��
					herosquarex=herosquareX;
					currBoard[herosquarez][herosquarex].y=0.4f;
					}
			}
			else if(OKMove&&currBoard[herosquarez][herosquarex]!=null
					&&GuiZe.canMove(currBoard[herosquarez][herosquarex], currBoard, herosquareZ, herosquareX)
					)//�[�i�����f�A�w
    			//�ĤG�����U�Ů�A�ӥB�i��.
    		{				
      			OKMove=false;//�P�ɫ�_�аO�A�i��U�@��      			
      			int srcRow=herosquarez;
      			int srcCol=herosquarex;
      			int dstRow=herosquareZ;
      			int dstCol=herosquareX;     
      			String msg="<#MOVE#>"+srcRow+","+srcCol+","+dstRow+","+dstCol;//�V�A�Ⱦ��o�e�U�Ѿާ@,�åB��a�U�ѫH��
      			father.ca.sendMessage(msg);
      			father.ca.perFlag=false;
    		}
    		else if(OKMove&&currBoard[herosquarez][herosquarex]!=null&&!GuiZe.canMove(currBoard[herosquarez][herosquarex], currBoard, herosquareZ, herosquareX))//�ĤG�����U�Ů�A�Ӧa�褣�i���A��ܪ��a���U�o����
    		{//�ĤG�����U�Ѥl,�ӥB���i�U,�����_�аO,�N�Ĥ@����_���Ѥl��_�쪬
    			currBoard[herosquarez][herosquarex].y=0f;
    			OKMove=false;
    			Toast.makeText//�P�ɵoToast.���ܪ��a
    			(
    				father, 
    				"���ŦX�W�h,�Э��s�ާ@!", 
    				Toast.LENGTH_SHORT
    			).show();
    		}
		}
    	return true;
    }
	private class SceneRenderer implements GLSurfaceView.Renderer 
    { 		
		ChessFoundation foundation;//���y
		TriangleS sanjiao1;//�W�����T����
		TriangleX sanjiao2;//�U�����T����
		RectWall heifang;//�¤���ΪO
		RectWall baifang;//�դ���ΪO
		RectWall wall;//�𪺥����
		
		public int foundationTexId;//���y���zID
		public int qipantexId;
		public int walltexId;//��
		public int floortexId;//�a��
		public int rooftexId;//�γ����z
		public int heitexId;//�¤课�z
		public int baitexId;//�դ课�z
		public int triangletexIds;//�T���ΤW�����z
		public int triangletexIdx;//�T���ΤU�����z
		
		public int whitechesstexId;//�Ѥl���z
		public int blackchesstexId;

		public SceneRenderer(){}
      
        public void onDrawFrame(GL10 gl) 
        {                  	
        	//�M���C��w�s��`�׽w�s
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        	//�]�m��e�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�m��e�x�}�����x�}
            gl.glLoadIdentity(); 
            //�]�m�ṳ����m
            GLU.gluLookAt(gl, cx, cy, cz, tx, ty, tz, 0, 1, 0);
            
            gl.glDisable(GL10.GL_LIGHTING);//�����\����
			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//�ҥγ��I�Ʋ�
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);//�ҥ��C��Ʋ�
			cb.drawself(gl);//�e�ѽL
			gl.glDisableClientState(GL10.GL_COLOR_ARRAY);//�����C��Ʋսw��		
			//�}�ү��z
	        gl.glEnable(GL10.GL_TEXTURE_2D);   
	        //���\�ϥί��zST���нw��
	        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
			foundation.drawSelf(gl, foundationTexId,qipantexId);//�e���y    
            drawWall( gl);//�e�ж� 
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY);//�����C��Ʋսw��
	        gl.glEnable(GL10.GL_LIGHTING);//���\����
	        gl.glEnable(GL10.GL_LIGHT0);//�}0���O        
	        gl.glEnable(GL10.GL_LIGHT1);//�}�@���O
	        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);//�ҥγ��I�k�V�q�Ʋ�     
	        for(int i=0;i<currBoard.length;i++)//�e�¦�Ѥl
	        {
	        	for(int j=0;j<currBoard[0].length;j++)
	        	{
	        		if(currBoard[i][j]!=null&&currBoard[i][j].chessType<=5&&currBoard[i][j].chessType>=0)
	        		{
	        			currBoard[i][j].drawSelf(gl,blackchesstexId);
	        		}
	        	}
	        }
	        for(int i=0;i<currBoard.length;i++)//�`���Ѥl�Ʋյe�զ�Ѥl,�Ǥ��P�����zID
	        {
	        	for(int j=0;j<currBoard[0].length;j++)
	        	{
	        		if(currBoard[i][j]!=null&&currBoard[i][j].chessType<=11&&currBoard[i][j].chessType>=6)
	        		{
	        			currBoard[i][j].drawSelf(gl,whitechesstexId);
	        		}
	        	}
	        }        
          gl.glDisable(GL10.GL_LIGHTING);//��������
          gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);//�����o�V�q�Ʋ�
          gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);//�T�γ��I�k�V�q�Ʋ�
          gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//�T�Ψϥί��zST���нw��
          gl.glDisable(GL10.GL_TEXTURE_2D);  
          drawPlayerNum(gl);//�e���T��    
        }

        public void onSurfaceChanged(GL10 gl, int width, int height)
        {
            //�]�m�����j�p�Φ�m 
        	gl.glViewport(0, 0, width, height);
        	//�]�m��e�x�}����v�x�}
            gl.glMatrixMode(GL10.GL_PROJECTION);
            //�]�m��e�x�}�����x�}
            gl.glLoadIdentity();
            //�p��z����v�����
            float ratio = (float) width / height;
            //�եΦ���k�p�ⲣ�ͳz����v�x�}
            gl.glFrustumf(-ratio, ratio, -1f, 1f, 2.0f, 400);    
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config)
        {
            //�����ܧݰ� 
        	gl.glDisable(GL10.GL_DITHER);
        	//�]�m�S�wHint���ت��Ҧ��A�o�̬��]�m���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //�]�m�̹��I����¦�RGBA
            gl.glClearColor(0,0,0,0);     
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);
            //���}�I���ŵ�
            gl.glEnable(GL10.GL_CULL_FACE);
            //���}���Ƶۦ�
            gl.glShadeModel(GL10.GL_SMOOTH);            
            
            foundation=new ChessFoundation();//�إߩ��L
            wall=new RectWall((HOUSE_SIZE+0.5f)*UNIT_SIZE,(HOUSE_SIZE+0.5f)*UNIT_SIZE);
            walltexId=initTexture(gl,R.drawable.qiangbi);//������z
            floortexId=initTexture(gl,R.drawable.diban);//�a�O���z
            rooftexId=initTexture(gl,R.drawable.wuding);//�γ������z
            
            foundationTexId=initTexture(gl,R.drawable.dizuo);//�[�����L���z
            qipantexId=initTexture(gl,R.drawable.qipan);
            triangletexIds=initTexture(gl,R.drawable.sjx);//�W�����¦V�U���T���ί��z
            triangletexIdx=initTexture(gl,R.drawable.sjxs);//�U�誺�¦V�W���T����
            
            heitexId=initTexture(gl,R.drawable.heifang);//�¤课�z
            baitexId=initTexture(gl,R.drawable.baifang);//�դ课�z
            
            whitechesstexId=initTexture(gl,R.drawable.whitechess);
            blackchesstexId=initTexture(gl,R.drawable.blackchess);
            
            heifang=new RectWall(0.7f,0.35f);//�y�¤�Ϥ�
            heifang.x=BLACK_FLAG_X;
            heifang.y=BLACK_FLAG_Y;
            heifang.z=-4;
            
            
            baifang=new RectWall(0.7f,0.35f);//�y�դ�Ϥ�
            baifang.x=WHITE_FLAG_X;
            baifang.y=WHITE_FLAG_Y;
            baifang.z=-4;

            sanjiao1=new TriangleS(0.25f);//�y��ӫ��ܶ¥դ�T����
            sanjiao1.y=PLAYER_TYPE_Y;
            sanjiao1.z=-4;
            
            sanjiao2=new TriangleX(0.255f);//���֤ܽU�Ѫ��T����
            sanjiao2.y=CURR_MOVE_PLAYER_Y;
            sanjiao2.z=-4;
            
            initLight(gl);//��l�ƿO��
            float[] positionParamsGreen={0,16*UNIT_SIZE,16.5f*UNIT_SIZE,1};//�̫᪺1��ܬO�w���
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, positionParamsGreen,0); 
            
            initLight2(gl);
            float[] positionParamsGreen2={0,16*UNIT_SIZE,-16.5f*UNIT_SIZE,1};//�̫᪺1��ܬO�w���
            gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_POSITION, positionParamsGreen2,0);
            
        }
        //ø�s�T���лx�O
        public void drawPlayerNum(GL10 gl)
        {
        	if(father.ca.num==1)//�p�G�O�¤�
        	{
        		sanjiao1.x=PLAYER_TYPE_X1;
        	}
        	else
        	{
        		sanjiao1.x=PLAYER_TYPE_X2;
        	}
        	
        	
        	if((father.ca.perFlag&&father.ca.num==1)||((!father.ca.perFlag)&&father.ca.num==2))
        	{
        		sanjiao2.x=CURR_MOVE_PLAYER_X1;
        	}
        	else
        	{
        		sanjiao2.x=CURR_MOVE_PLAYER_X2;
        	}        	
            //�]�m��e�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�m��e�x�}�����x�}
            gl.glLoadIdentity(); 
            gl.glDisable(GL10.GL_LIGHTING);//���O
            
            //�}�ү��z
	        gl.glEnable(GL10.GL_TEXTURE_2D);   
	        //���\�ϥί��zST���нw��
	        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	        
            sanjiao1.drawSelf(gl, triangletexIds);
            sanjiao2.drawSelf(gl, triangletexIdx);
            gl.glEnable(GL10.GL_BLEND);//�}�ҲV�X
            gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_COLOR);
            heifang.drawSelf(gl, heitexId);
            baifang.drawSelf(gl, baitexId);
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);//�������z
            gl.glDisable(GL10.GL_TEXTURE_2D);
            gl.glDisable(GL10.GL_BLEND);//�����V�X
        	
        }
         public void drawWall(GL10 gl)
        {
        	gl.glPushMatrix();
	        gl.glTranslatef(0,(HOUSE_SIZE/2-1)*UNIT_SIZE,-HOUSE_SIZE/2*UNIT_SIZE);
        	wall.drawSelf(gl, walltexId);
        	gl.glTranslatef(0, 0, HOUSE_SIZE*UNIT_SIZE);
        	gl.glRotatef(180, 0, 1, 0);
        	wall.drawSelf(gl,walltexId );
        	gl.glPopMatrix();
        	
        	gl.glPushMatrix();
        	gl.glTranslatef(-HOUSE_SIZE/2*UNIT_SIZE, (HOUSE_SIZE/2-1)*UNIT_SIZE, 0);
        	gl.glRotatef(90, 0, 1, 0);
        	wall.drawSelf(gl, walltexId);
        	gl.glTranslatef(0, 0, HOUSE_SIZE*UNIT_SIZE);
        	gl.glRotatef(180, 0, 1, 0);
        	wall.drawSelf(gl, walltexId);
        	gl.glPopMatrix();
        	
        	gl.glPushMatrix();
        	gl.glRotatef(-90, 1, 0, 0);
        	gl.glTranslatef(0,0,-UNIT_SIZE);
        	wall.drawSelf(gl,floortexId);
        	gl.glTranslatef(0, 0,HOUSE_SIZE*UNIT_SIZE);
        	gl.glRotatef(180, 0, 1, 0);
        	wall.drawSelf(gl,rooftexId );
        	gl.glPopMatrix();
        }
    }
	
	private void initLight(GL10 gl)
	{
		//�զ�O��
        gl.glEnable(GL10.GL_LIGHT0);//���}0���O  
        
        //���ҥ��]�m
        float[] ambientParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientParams,0);            

        //���g���]�m
        float[] diffuseParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseParams,0); 
        
        //�Ϯg���]�m
        float[] specularParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specularParams,0);     
	}
	
	private void initLight2(GL10 gl)
	{
		//�զ�O��
        gl.glEnable(GL10.GL_LIGHT1);//���}0���O  
        
        //���ҥ��]�m
        float[] ambientParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_AMBIENT, ambientParams,0);            

        //���g���]�m
        float[] diffuseParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_DIFFUSE, diffuseParams,0); 
        
        //�Ϯg���]�m
        float[] specularParams={1f,1f,1f,1.0f};//���Ѽ� RGBA
        gl.glLightfv(GL10.GL_LIGHT1, GL10.GL_SPECULAR, specularParams,0);     
	}
	//�[���Ѥl�ҫ�
	public static void initChessForDraw(Resources r)
	{
		qizi=new LoadedObjectVertexNormalTexture[]
		    {
		        LoadUtil.loadFromFileVertexOnly("che.obj", r),
		        LoadUtil.loadFromFileVertexOnly("ma.obj", r),
		        LoadUtil.loadFromFileVertexOnly("xiang.obj", r),
	            LoadUtil.loadFromFileVertexOnly("hou.obj", r),
		        LoadUtil.loadFromFileVertexOnly("wang.obj", r),
		        LoadUtil.loadFromFileVertexOnly("bing.obj", r)
		     };
	}
	//��l�Ư��z
	public int initTexture(GL10 gl,int drawableId)//textureId
	{
		//�ͦ����zID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);    
		int currTextureId=textures[0];    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,GL10.GL_TEXTURE_MAG_FILTER,GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_CLAMP_TO_EDGE);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_CLAMP_TO_EDGE);
        
        InputStream is = this.getResources().openRawResource(drawableId);
        Bitmap bitmapTmp; 
        try 
        {
        	bitmapTmp = BitmapFactory.decodeStream(is);
        } 
        finally 
        {
            try 
            {
                is.close();
            } 
            catch(IOException e) 
            {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle();  
        return currTextureId;
	}
}

