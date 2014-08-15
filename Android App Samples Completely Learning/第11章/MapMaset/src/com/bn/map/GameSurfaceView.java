package com.bn.map;
import static com.bn.map.Constant.*;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.opengl.GLUtils;
import android.view.MotionEvent;
class GameSurfaceView extends GLSurfaceView 
{		
	MapMasetActivity father;//�n��Activity
	private float mPreviousY;//�W����Ĳ����mY����
    private float mPreviousX;//�W����Ĳ����mY����
    
    public static int guankaID;//�ޥdID
    public static int[][]MAP;//�������d���a�ϼƲ�
    public static int[][] MAP_OBJECT;//�������d���}�Ʋ�
    public static int STIME;//�C�@���������ɶ�����
    
    public static float yAngle=0f;//��쨤
    public static float xAngle=90f;//���� 
    public static float cx;//�ṳ��x����
    public static float cy;//�ṳ��y����
    public static float cz;//�ṳ��z����
    public static float tx=0;//�[��ؼ��Ix����  
    public static float ty=0;//�[��ؼ��Iy����
    public static float tz=0f;//�[��ؼ��Iz����      
    public static float upX=0;
    public static float upY=1;
    public static float upZ=0;//up�b
    
    public static float ballX;//�y���U�ӧ���
    public static float ballY;
    public static float ballZ;
    public static float ballGX=0f;//x��V�W���[�t��
    public static float ballGZ=0f;//y��V�W���[�t��
    
    public static int ballCsX;//��l��l
    public static int ballCsZ;
    public static int ballMbX;//�ؼЮ�l
    public static int ballMbZ;
    
    public static float ballVX=0;//XZ��V�W���t��
    public static float ballVZ=0;
   
    private SceneRenderer mRenderer;//������V��	
    
    public static int floorId;//�a�O���zID
    public static int wallId;//�𯾲z
    public static int yuankonId;//��կ��zId
    public static int ballId;//�y���zID
    public static int ballYZId;//�y���v�l���zID
    public static int numberId;//�ƦrID
    public static int time_DH_Id;//�y��ID
    public static int mbyuankonId;
    
	public RectWall yuankon;//��կx��
	public Floor floor;//�a�O
	public static  Wall wall;//��
	public BallTextureByVertex ball;//�y
	public RectWall ballYZ;//�y���v�l�x��
	public Number number;//�Ʀr
	public TextureRect time_DH;//�y���A�Ω�ɶ�
	
	BallGDThread ballgdT;//�y�B�ʽu�{
	
	public GameSurfaceView(Context context)
	{
        super(context);
        this.father=(MapMasetActivity)context;
        ballCsX=CAMERA_COL_ROW[guankaID][0];//��l��C
        ballCsZ=CAMERA_COL_ROW[guankaID][1];
        
        ballMbX=CAMERA_COL_ROW[guankaID][2];//�ؼЦ�C
        ballMbZ=CAMERA_COL_ROW[guankaID][3];
        
        MAP=MAPP[guankaID];//�a�ϼƲ�
        MAP_OBJECT=MAP_OBJECTT[guankaID];//�}�Ʋ�
        STIME=GD_TIME[guankaID];//����ɶ�
        
        ballX=ballCsX*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;//��l�Ʋy��m
		ballZ=ballCsZ*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
		ballY=ballR;
       
        tx=0;//�ṳ���ؼЦ�m
        ty=0;
        tz=0;
        ballgdT=new BallGDThread(this);
        //�]�m�ṳ������m
        cx=(float)(tx+Math.cos(Math.toRadians(xAngle))*Math.sin(Math.toRadians(yAngle))*DISTANCE);//�ṳ��x���� 
        cz=(float)(tz+Math.cos(Math.toRadians(xAngle))*Math.cos(Math.toRadians(yAngle))*DISTANCE);//�ṳ��z���� 
        cy=(float)(ty+Math.sin(Math.toRadians(xAngle))*DISTANCE);//�ṳ��y���� 
        mRenderer = new SceneRenderer();	//�Ыس�����V��
        setRenderer(mRenderer);				//�]�m��V��		
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//�]�m��V�Ҧ����D�ʴ�V      
       
    }	
	@Override 
    public boolean onTouchEvent(MotionEvent e) 
	{
        float y = e.getY();//�o����U��XY����
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
            if(xAngle+dy * TOUCH_SCALE_FACTOR>90)//���ṳ�������j��85,�N��j�85;
            {
            	xAngle=90;
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
	
	private class SceneRenderer implements GLSurfaceView.Renderer 
	{
		
        public void onDrawFrame(GL10 gl) 
        {  
        	//�ĥΥ��Ƶۦ�
            gl.glShadeModel(GL10.GL_SMOOTH);            
        	//�M���C��w�s��`�׽w�s
        	gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);        	
        	//�]�m��e�x�}���Ҧ��x�}
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            //�]�m��e�x�}�����x�}
            gl.glLoadIdentity();    
            //�]�mcamera��m
            GLU.gluLookAt
            (gl, cx,cy,cz, tx,ty, tz,0,1, 0);   //�]�m�ṳ��
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//�ҥγ��I�Ʋ�
            gl.glEnable(GL10.GL_TEXTURE_2D);//�ҥί��z
            gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY); 
            
            gl.glEnable(GL10.GL_LIGHTING);//���\����
	        gl.glEnable(GL10.GL_LIGHT0);//�}0���O  	        
	        //���\�ϥΪk�V�q�Ʋ�
            gl.glEnableClientState(GL10.GL_NORMAL_ARRAY); 
            
            floor.drawSelf(gl, floorId);//ø�s�a�O
            
            gl.glPushMatrix();//�O�@�x�}
            gl.glTranslatef(-MAP[0].length/2*UNIT_SIZE, 0, (-MAP.length/2)*UNIT_SIZE);
            wall.drawSelf(gl, wallId);//ø�s��           
            gl.glPopMatrix();//��_�x�}
            
            gl.glDisable(GL10.GL_LIGHTING);//��������
            gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);//�����k�V�q�Ʋ�
            
            gl.glEnable(GL10.GL_BLEND);//�}�ҲV�X

            gl.glBlendFunc(GL10.GL_SRC_ALPHA ,GL10.GL_ONE_MINUS_SRC_ALPHA);//�]�m�V�X�]�l
            gl.glPushMatrix();//�O�@��e�x�}�A
            gl.glTranslatef(ballMbX*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2,
            		0.015f,
            		ballMbZ*UNIT_SIZE- MAP.length*UNIT_SIZE/2);
            gl.glRotatef(-90, 1, 0, 0);
			yuankon.drawSelf(gl, mbyuankonId);//ø�s�ؼж��
			gl.glPopMatrix();
            drawYuanKong(gl);//ø�s���           
            gl.glPushMatrix();
        	gl.glTranslatef(ballX+ballR-0.2f, 0.01f, ballZ-ballR+0.2f);
        	gl.glRotatef(-90, 1, 0, 0);
        	gl.glRotatef(45, 0, 0, 1);
        	ballYZ.drawSelf(gl, ballYZId);//ø�s�v�l
        	gl.glPopMatrix();            
            gl.glDisable(GL10.GL_BLEND);//�����V�X                    
            drawBall(gl);//ø�s�y            
            drawNumber(gl);//ø�s��e�Ѿl�ɶ��Ʀr            
            
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);   //�������I�Ʋ�         
            gl.glDisable(GL10.GL_TEXTURE_2D);//�������z
            gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);        
         }        
        
        public  void drawNumber(GL10 gl)//ø�s�Ѿl�ɶ���k
        {
        	gl.glMatrixMode(GL10.GL_MODELVIEW);//��_�x�}   
	        gl.glLoadIdentity();   	     //�]�m��e�x�}�����x�}
	      
	        gl.glEnable(GL10.GL_BLEND);//�}�ҲV�X
	        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);//�]�m�V�X�]�l
	        //ø�s�Ʀr����L    ����
	        gl.glPushMatrix();
	        gl.glTranslatef(2.0f,1.6f,-6);//�]�m����O����m.����A�ո`
	        number.y=0;//�Ʀr��Y����
	        number.NumberStr=Math.abs(GD_TIME[guankaID]-STIME)/60+"";//�ѤU��������
	        number.drawSelf(gl,0,numberId);//ø�s����
	        gl.glTranslatef(ICON_WIDTH*0.7f,0f,0);
	        time_DH.drawSelf(gl, time_DH_Id);//�e�y��
	        gl.glTranslatef(ICON_WIDTH*0.7f,0f,0);
	        number.NumberStr=Math.abs(GD_TIME[guankaID]-STIME)%60+"";
	        number.drawSelf(gl,1,numberId);//�e���
	        gl.glPopMatrix();//��_�x�}
	        gl.glDisableClientState(GL10.GL_BLEND);//�����V�X       
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
            gl.glFrustumf(-ratio, ratio, -1, 1, 3, 1000);   
        }
        public void onSurfaceCreated(GL10 gl, EGLConfig config) 
        {
            //�����ܧݰ� 
        	gl.glDisable(GL10.GL_DITHER);
        	//�]�m�S�wHint���ت��Ҧ��A�o�̬��]�m���ϥΧֳt�Ҧ�
            gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT,GL10.GL_FASTEST);
            //�]�m�����}�I���ŵ�
    		gl.glEnable(GL10.GL_CULL_FACE);
    		 //�]�m�ۦ�ҫ������Ƶۦ�   
            gl.glShadeModel(GL10.GL_SMOOTH);
    		//�}�ҲV�X   
            //�]�m�̹��I����¦�RGBA
            gl.glClearColor(0,0,0,0);           
            //�ҥβ`�״���
            gl.glEnable(GL10.GL_DEPTH_TEST);             
            floorId=initTexture(gl,R.drawable.floor);//�a��ID
            wallId=initTexture(gl,R.drawable.wall);    //��ID
            
            yuankonId=initTexture2(gl,R.drawable.yuankon);//���ID
            ballId=initTexture2(gl,R.drawable.ball);//�yID
            ballYZId=initTexture2(gl,R.drawable.ballyingzi);//�y���v�lID
            numberId=initTexture2(gl,R.drawable.number);//�ƦrID
            time_DH_Id=initTexture2(gl,R.drawable.dunhao);//�y�����z
            mbyuankonId=initTexture2(gl,R.drawable.mbyuankon);//�ؼж��Id
            
            
            floor=new Floor(MAP[0].length,MAP.length);//�a�O
            wall=new Wall();//��     
            yuankon=new RectWall(2f*ballR,2f*ballR);//���
            ball=new BallTextureByVertex(ballR,15);//�y
            ballYZ=new RectWall(3.6f*ballR,2.6f*ballR);//�v�l
            number=new Number(GameSurfaceView.this);//�Ʀr�ﹳ
            time_DH=new TextureRect(ICON_WIDTH*0.5f/2,//�Ʀr
            	 ICON_HEIGHT*0.5f/2,
           		 new float[]
		             {
		           	  0,0, 0,1, 1,0,
		           	  0,1, 1,1,  1,0
		             });//�y��
            ballgdT.start();
            
            initLight(gl);//��l�ƿO��
            float[] positionParamsGreen={-4,4,4,0};//�̫᪺0��ܬO�w�V��
            gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, positionParamsGreen,0); 
     
        }
        public void drawBall(GL10 gl)//�e���O�y
        {	
        	gl.glPushMatrix();
        	gl.glTranslatef(ballX, ballY, ballZ);     //���ʬ�������m 	
        	ball.drawSelf(gl, ballId);   //ø�s
        	gl.glPopMatrix();       	
        }
        public void drawYuanKong(GL10 gl)//ø�s���
        {
        	gl.glPushMatrix();
        	gl.glTranslatef(-MAP[0].length*UNIT_SIZE/2, 0.01f,- MAP.length*UNIT_SIZE/2);
        	for(int i=0;i<MAP_OBJECT.length;i++)
        	{
        		for(int j=0;j<MAP_OBJECT[0].length;j++)
        		{
        			if(MAP_OBJECT[i][j]==1)
        			{
        				if(i==ballMbX&&j==ballMbZ)//�p�G���O�ؼЬ}�hø�s
        				{
        				   continue;
        				}
        				gl.glPushMatrix();
        				gl.glTranslatef((j)*UNIT_SIZE, 0.001f, (i)*UNIT_SIZE);
        				gl.glRotatef(-90, 1, 0, 0);
        				yuankon.drawSelf(gl, yuankonId);//ø�s
        				gl.glPopMatrix();
        			}
        		}
        	}
        	gl.glPopMatrix();
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
	 }
	//��l�Ư��z
	public int initTexture2(GL10 gl,int drawableId)//textureId
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

	//��l�Ư��z
	public int initTexture(GL10 gl,int drawableId)//textureId
	{
		//�ͦ����zID
		int[] textures = new int[1];
		gl.glGenTextures(1, textures, 0);    
		int currTextureId=textures[0];    
		gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
		
		//�bMIN_FILTER MAG_FILTER���ϥ�MIPMAP���z
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR_MIPMAP_NEAREST);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR_MIPMAP_LINEAR);
		// �ͦ�Mipmap���z
		((GL11)gl).glTexParameterf(GL10.GL_TEXTURE_2D,GL11.GL_GENERATE_MIPMAP,GL10.GL_TRUE);
        //���z�Ԧ��覡
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S,GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T,GL10.GL_REPEAT);
        
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

