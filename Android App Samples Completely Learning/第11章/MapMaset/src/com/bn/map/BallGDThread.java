package com.bn.map;
import static com.bn.map.GameSurfaceView.*;
import static  com.bn.map.Constant.*;
public class BallGDThread extends Thread
{
	GameSurfaceView gameSurface;//surfaceView���ޥ�
	public Boolean flag=true;//�u�{�лx��
	public static float t=0.1f;//�C�������ɶ�
	public static int ZJS_Time;//�`�p�ɶ��A�C�@�����A�q�}�l�짹���}�l�p��
	public static float ballRD;
	public float ballGX;
	public float ballGZ;//�C�������[�t��
	
	
	int ballXx=0;//����Ҧb����C
	int ballZz=0;
	public static Boolean flagSY=true;//�P�_�O�_���i�}�лx      
	public BallGDThread(GameSurfaceView gameSurface)
	{
		this.gameSurface=gameSurface;
		ballRD=ballR/2;
	}
	@Override   
	public void run()
	{
		while(flag)
		{
			ballGX=GameSurfaceView.ballGX;//�����[�t��
			ballGZ=GameSurfaceView.ballGZ;
			try{
			PZJC(ballX,ballZ,ballVX*t+ballGX*t*t/2,ballVZ*t+ballGZ*t*t/2);//�P�_�I�����p��k
			}catch (Exception tt) {
				tt.printStackTrace();
			}
			ballVX+=ballGX*t;
			ballVZ+=ballGZ*t;//�̲׳t��
			
			ballX=ballX+ballVX*t+ballGX*t*t/2;//VT+1/2A*T*T
			ballZ=ballZ+ballVZ*t+ballGZ*t*t/2;//�̲צ�m		
			gameSurface.ball.mAngleX+=(float)Math.toDegrees(((ballVZ*t+ballGZ*t*t/2))/ballR);
			gameSurface.ball.mAngleZ-=(float)Math.toDegrees((ballVX*t+ballGX*t*t/2)/ballR);//���઺����
			if(Math.abs((ballVZ*t+ballGZ*t*t/2))<0.005f)//�p�G��e�e�i�Ȥp��վ�ȡA�h��������ʤ�V���k�s
			{
				gameSurface.ball.mAngleX=0;
			}
			if(Math.abs(ballVX*t+ballGX*t*t/2)<0.005f)
			{
				gameSurface.ball.mAngleZ=0;
			}
			//*********************�P�_�O�_����*****************//
			pdZJ();//�P�_�i�}��ơA�ά������ާ@
			
			if(!flagSY)//�p�G���i�}�̤F
			{
				flagSY=true;//��l��}�l
				if(ballXx==ballMbX&&ballZz==ballMbZ)//�p�G�OĹ�F
				{
					try 
					{
						Thread.sleep(1000);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
					flag=false;
					gameSurface.father.curr_grade=GD_TIME[guankaID]-STIME;
					gameSurface.father.hd.sendEmptyMessage(1);//�i�JĹ���ɭ�
				}
				else//�_�h�O�i�}�F
				{
					try
			        {
						Thread.sleep(1000);//���y1��
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				ballX=ballCsX*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;
				ballZ=ballCsZ*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
				ballY=ballR;
//				ZJS_Time=0;
				}
				ballVX=0;
				ballVZ=0;//�̲׳t�׳���s
//				ballGX=0;
//				ballGZ=0;
			}		
			ballVX*=V_TENUATION;
			ballVZ*=V_TENUATION;//�I��
			if(Math.abs(ballVX)<0.04)//��t�פp��Y�ӽվ�Ȯ�
			{
				ballVX=0;//�t���k�s
				gameSurface.ball.mAngleZ=0;//�N¶�b��ܪ��ȸm���s
			}
			if(Math.abs(ballVZ)<0.04)
			{
				ballVZ=0;
				gameSurface.ball.mAngleX=0;
			}
			
			
			ZJS_Time+=50;//�C�����`�ɶ��a
			STIME=(ZJS_Time/1000);//���L���ɶ����
			if(GD_TIME[guankaID]-STIME<=0)//�p�G�ɶ����s�A�����S���q�L
			{
				flag=false;
				gameSurface.father.hd.sendEmptyMessage(2);
			}
			try //��50�@��A�i�J�U�@���`��
			{
				Thread.sleep(50);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static void initDiTu()//��l�Ʀa�Ϥ�k
	{
		guankaID%=MAPP.length;//����ƲնV��
        ballY=0;
        MAP=MAPP[guankaID];//�a�ϼƲ�
        
        Wall walll=new Wall();//�s�ئa��
        try
        {
			Thread.sleep(1000);//���y1��
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}		
		wall=walll;//�N�s�ئa�ϥI����e����ø�s�a��
		ballCsX=CAMERA_COL_ROW[guankaID][0];//��l�y�Ҧb����l��C
        ballCsZ=CAMERA_COL_ROW[guankaID][1];
        
        ballMbX=CAMERA_COL_ROW[guankaID][2];//�����v�ؼЦ�C
        ballMbZ=CAMERA_COL_ROW[guankaID][3];
		MAP_OBJECT=MAP_OBJECTT[guankaID];//�Ӧa�Ϫ��}�Ʋ�
        ballX=ballCsX*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;//�N�y�e���l��m
		ballZ=ballCsZ*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
		STIME=GD_TIME[guankaID];//����ɶ��A�٭�
		ZJS_Time=0;//�`�ɶ��k�s
	}
	public Boolean PZJC(float ballX,float ballZ,float BX,float BZ)
	{
		Boolean flag=false;
		ballX=MAP[0].length*UNIT_SIZE/2+ballX;//�N�a�ϲ���XZ���j��s���H��
		ballZ=MAP.length*UNIT_SIZE/2+ballZ;
		if(BZ>0)//�p�G�VZ�b����V�B��
		{
			for(int i=(int)((ballZ+ballR)/UNIT_SIZE);i<=(int)((ballZ+ballR+BZ)/UNIT_SIZE);i++)
				//�`���A���p���@�U��L�X�Ӯ�l�A����q�Ĥ@�Ӯ�l�}�l�P�_
			{
				if(MAP[i][(int)(ballX/UNIT_SIZE)]==BKTG&&MAP[i-1][(int)(ballX/UNIT_SIZE)]==KTG){//�P�_�O�_�I����F
					ballVZ=-ballVZ*VZ_TENUATION;//�N�t�׸m�ϡA�ýվ�
					if((GameSurfaceView.ballZ+ballVZ*t+ballGZ*t*t/2)>=(i*UNIT_SIZE-ballR-MAP.length*UNIT_SIZE/2))//�p�G�t�׽դϫ��٬O�|����A����N�[�t���k�s�A�ñN�y�e�b�M�������۪��a��
					{
						GameSurfaceView.ballZ=(i*UNIT_SIZE-ballR-MAP.length*UNIT_SIZE/2);
						ballVZ=0;
						ballGZ=0;
					}
					else{					
					gameSurface.father.playSound(1, 0);//���񲾰��n��
					gameSurface.father.shake();//�_��
					}
					flag=true;//�лx��m��true
				}
				else if(BX<=0&&((int)((ballX-ballR)/UNIT_SIZE)>=0)&&(MAP[i][(int)((ballX-ballR)/UNIT_SIZE)]==BKTG)
						&&MAP[i-1][(int)((ballX-ballR)/UNIT_SIZE)]==KTG)//�p�G�O�y��Z�t��V��I���A�i��|�I�쨤
				{
					float sina=(ballX-((int)((ballX-ballR)/UNIT_SIZE)+1)*UNIT_SIZE)/ballR;//�o��I���ɪ����׬�����
					float cosa=(float)Math.sqrt(1-sina*sina);
					ballVX=jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=-jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					ballGX=0;
					ballGZ=0;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ){//�p�G�I���ܤj�A�h�����n��
					gameSurface.father.playSound(1, 0);//���񲾰��n��
					gameSurface.father.shake();
					}else if(Math.abs(ballVZ)<SD_TZZ)//�p�G�t�פp��վ�ȡA�h���u�_�A�ӥB�t�׭Ȭ��s
					{
						GameSurfaceView.ballZ=i*UNIT_SIZE-ballR-MAP.length*UNIT_SIZE/2;
						ballVZ=0;
						ballGZ=0;
					}else if(Math.abs(ballVX)<SD_TZZ)//�p�G�t�פp��վ�ȡA�h���u�_�A�ӥB�t�׭Ȭ��s
					{
						GameSurfaceView.ballX=(1+i)*UNIT_SIZE-ballR-MAP.length*UNIT_SIZE/2;
						ballVZ=0;
						ballGZ=0;
					}
					flag=true;
				}
				else if(BX>=0&&((int)((ballX+ballR)/UNIT_SIZE)>=0)&&MAP[i][(int)((ballX+ballR)/UNIT_SIZE)]==BKTG
						&&MAP[i-1][(int)((ballX+ballR)/UNIT_SIZE)]==KTG){//�p�G�OZ����V�B�ʮɸI���F
					float sina=(ballX-((int)((ballX+ballR)/UNIT_SIZE)*UNIT_SIZE))/ballR;
					float cosa=(float)Math.sqrt(1-sina*sina);
					ballVX=-jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					ballVZ=-jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ){
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();
						}else{
							ballGX=0;
							ballGZ=0;
						}
					flag=true;
				}
			}	
		}
		
		if(BX>0)//�p�G�VX�b����V��
		{
			for(int i=(int)((ballX+ballR)/UNIT_SIZE);i<=(int)((ballX+ballR+BX)/UNIT_SIZE);i++)
			{//�`���A���p���@�U��L�X�Ӯ�l�A����q�Ĥ@�Ӯ�l�}�l�P�_
				if(MAP[(int)(ballZ/UNIT_SIZE)][i]==BKTG&&MAP[(int)(ballZ/UNIT_SIZE)][i-1]==KTG){//�p�G�I���F			
					
					ballVX=-ballVX*VZ_TENUATION;//�t�׸m�ϡA�ýվ�
					if((GameSurfaceView.ballX+ballVX*t+ballGX*t*t/2)>
					((i)*UNIT_SIZE-ballR-MAP[0].length*UNIT_SIZE/2))//�p�G�w�g��K����F�A����t�׬��s
					{
						GameSurfaceView.ballX=(i)*UNIT_SIZE-ballR-MAP[0].length*UNIT_SIZE/2;
						ballGX=0;//�[�t�שM�t�׳]�m���s
						ballVX=0;
					}
					else
					{						
					gameSurface.father.playSound(1, 0);//���񲾰��n��
					gameSurface.father.shake();
					}
					if(ballGX>0)//�t�פp��վ�ȫh�k�s
					{
						ballGX=0;
					}
					 flag=true;
					 
				}
				else if(BZ<=0&&((int)((ballZ-ballR)/UNIT_SIZE)>=0)&&(MAP[(int)((ballZ-ballR)/UNIT_SIZE)][i]==BKTG)
						&&(MAP[(int)((ballZ-ballR)/UNIT_SIZE)][i-1]==KTG))//�y�����伲��
				{
					float sina=(ballZ-((int)((ballZ-ballR)/UNIT_SIZE)+1)*UNIT_SIZE)/ballR;//�o��������ת������ȩM�l����
					float cosa=(float)Math.sqrt(1-sina*sina);
					ballVX=-jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					ballVZ=jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ){//�t�׹F��@�w�j��~�����n���M�_��
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
						}else{
							ballGX=0;//�t���k�s
							ballGZ=0;
						}
					flag=true;
				}
				else if(BZ>=0&&((int)((ballZ+ballR)/UNIT_SIZE)>=0)&&MAP[(int)((ballZ+ballR)/UNIT_SIZE)][i]==BKTG
						&&(MAP[(int)((ballZ+ballR)/UNIT_SIZE)][i-1]==KTG)){//�p�G�k��I��
					float sina=-(ballZ-((int)((ballZ+ballR)/UNIT_SIZE))*UNIT_SIZE)/ballR;//�o�������
					float cosa=(float)Math.sqrt(1-sina*sina);
					ballVX=-jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=-jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ)
					{
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
						}else{
							ballGX=0;
							ballGZ=0;//�_�h�t���k�s
						}
					flag=true;
				}
			}		
		}
		 if(BX<0)
		{
			for(int i=(int)((ballX-ballR)/UNIT_SIZE);i>=(int)((ballX-ballR+BX)/UNIT_SIZE);i--)
			{//�`���P�_�O�_�I��
				if(MAP[(int)(ballZ/UNIT_SIZE)][i]==BKTG&&MAP[(int)(ballZ/UNIT_SIZE)][i+1]==KTG)
				{//�p�G�I��
					
						ballVX=-ballVX*VZ_TENUATION;//�t�׸m�Ϩýվ�A
						if((GameSurfaceView.ballX+ballVX*t+ballGX*t*t/2)<
						((1+i)*UNIT_SIZE+ballR-MAP[0].length*UNIT_SIZE/2))//�p�G�w�g��K����F�A����t���k�s
						{
							GameSurfaceView.ballX=(1+i)*UNIT_SIZE+ballR-MAP[0].length*UNIT_SIZE/2;
							ballGX=0;//�[�t�שM�t�׳]�m���s
							ballVX=0;
						}
						else
						{						
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();
						}
					
					if(ballVX<0)
					{
						ballVX=-ballVX;
					}
					flag=true;
//					return true;
				}
				else if(BZ<=0&&((int)((ballZ-ballR)/UNIT_SIZE)>=0)&&(MAP[(int)((ballZ-ballR)/UNIT_SIZE)][i]==BKTG)
						&&MAP[(int)((ballZ-ballR)/UNIT_SIZE)][i+1]==KTG)//�y���伲��
				{
					float sina=(ballZ-((int)((ballZ-ballR)/UNIT_SIZE)+1)*UNIT_SIZE)/ballR;
					float cosa=(float)Math.sqrt(1-sina*sina);//�o���������
					ballVX=jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ)//�ݬO�_�n�����n��
					{
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
						}else{
							ballGX=0;
							ballGZ=0;
						}
					flag=true;
				}
				else if(BZ>=0&&((int)((ballZ+ballR)/UNIT_SIZE)>=0)&&MAP[(int)((ballZ+ballR)/UNIT_SIZE)][i]==BKTG
						&&MAP[(int)((ballZ+ballR)/UNIT_SIZE)][i+1]==KTG){//�p�G�k��I��
					float sina=-(ballZ-((int)((ballZ+ballR)/UNIT_SIZE))*UNIT_SIZE)/ballR;
					float cosa=(float)Math.sqrt(1-sina*sina);//�o�������
					ballVX=jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=-jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ)//�P�_�O�_�n�����n��
					{
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
						}else{
							ballGX=0;
							ballGZ=0;
						}
					flag=true;
				}
			}
		}
		
		if(BZ<0)//�VZ�b�t��V�W�B�ʮ�
		{
			for(int i=(int)((ballZ-ballR)/UNIT_SIZE);i>=(int)((ballZ-ballR+BZ)/UNIT_SIZE);i--)
			{//�`���ݬO�_�I���F
				if(MAP[i][(int)(ballX/UNIT_SIZE)]==BKTG&&MAP[i+1][(int)(ballX/UNIT_SIZE)]==KTG){
					ballVZ=-ballVZ*VZ_TENUATION;//�N�t�׸m�ϡA�ýվ�
					if((GameSurfaceView.ballZ+ballVZ*t+ballGZ*t*t/2)<=((1+i)*UNIT_SIZE+ballR-MAP.length*UNIT_SIZE/2))
					{//�ݽվ�᪺�t�פU�A�٬O�_�|����
						GameSurfaceView.ballZ=(1+i)*UNIT_SIZE+ballR-MAP.length*UNIT_SIZE/2;
						ballVZ=0;
						ballGZ=0;
					}
					else
					{					
					gameSurface.father.playSound(1, 0);//���񲾰��n��
					gameSurface.father.shake();//�_��
					}					
					if(ballVZ<0)//�p�G�t���٤p��s�A�h�m��
					{
						ballVZ=-ballVZ;
					}					
					flag=true;
				}
				else if(BX<=0&&((int)((ballX-ballR)/UNIT_SIZE)>=0)&&(MAP[i][(int)((ballX-ballR)/UNIT_SIZE)]==BKTG)
						&&MAP[i+1][(int)((ballX-ballR)/UNIT_SIZE)]==KTG)//����I��
				{
					float sina=(ballX-((int)((ballX-ballR)/UNIT_SIZE)+1)*UNIT_SIZE)/ballR;//�o�쨤�׬�����
					float cosa=(float)Math.sqrt(1-sina*sina);					
					ballVX=jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ)//�P�_�O�_�n�����n��
					{
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
					}
					else
					{
							ballGX=0;
							ballGZ=0;
					}
					flag=true;
				}
				else if(BX>=0&&((int)((ballX+ballR)/UNIT_SIZE)<MAP[0].length)&&MAP[i][(int)((ballX+ballR)/UNIT_SIZE)]==BKTG
						&&MAP[i+1][(int)((ballX+ballR)/UNIT_SIZE)]==KTG){//�k��I��
					float sina=-(ballX-((int)((ballX+ballR)/UNIT_SIZE))*UNIT_SIZE)/ballR;
					float cosa=(float)Math.sqrt(1-sina*sina);//�o�������
					ballVX=-jsSDX(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;//�o��I���᪺�t��
					ballVZ=jsSDZ(ballVX,ballVZ,cosa,sina)*VZ_TENUATION;
					if(Math.abs(ballVX)>SD_TZZ||Math.abs(ballVZ)>SD_TZZ)//�ݬO�_�n�����n��
					{
						gameSurface.father.playSound(1, 0);//���񲾰��n��
						gameSurface.father.shake();//�_��
					}
					else
					{
							ballGX=0;
							ballGZ=0;
					}
					flag=true;
				}
				
			}	
		}
		return flag;
	}
	public void pdZJ()//�P�_�O�_�i�}��k
	{
		ballXx=(int)((MAP[0].length*UNIT_SIZE/2+ballX)/UNIT_SIZE);//����Ҧb���������Ʋզ�C
		ballZz=(int)((MAP.length*UNIT_SIZE/2+ballZ)/UNIT_SIZE);
		if(MAP_OBJECT[ballZz][ballXx]==1){//���W����l�O�}
				if((float)Math.sqrt(
				(ballX-ballXx*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)*(ballX-ballXx*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)
				+(ballZ-ballZz*UNIT_SIZE+MAP.length*UNIT_SIZE/2)*(ballZ-ballZz*UNIT_SIZE+MAP.length*UNIT_SIZE/2))
				<ballR+ballRD//�h�P�_�y�߬O�_�b�}��
				){//���i�}�̤F
					flagSY=false;//�лx��
					ballX=ballXx*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;//�N�y�e��}��
					ballZ=ballZz*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
					ballY=0;
					
		}}
		else if(MAP_OBJECT[ballZz][ballXx+1]==1)//���U����l�O�}
		{
			if((float)Math.sqrt(
					(ballX-(1+ballXx)*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)*(ballX-(1+ballXx)*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)
					+(ballZ-ballZz*UNIT_SIZE+MAP.length*UNIT_SIZE/2)*(ballZ-ballZz*UNIT_SIZE+MAP.length*UNIT_SIZE/2))
					<ballR+ballRD//�h�P�_�y�߬O�_�b�}��
					){
				flagSY=false;//���i�}�̤F
				ballX=(1+ballXx)*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;
				ballZ=ballZz*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
				ballY=0;
				ballXx=ballXx+1;
			}
		}
		else if(MAP_OBJECT[ballZz+1][ballXx+1]==1){//�k�U����l�O�}
			if((float)Math.sqrt(
					(ballX-(1+ballXx)*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)*(ballX-(1+ballXx)*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)
					+(ballZ-(1+ballZz)*UNIT_SIZE+MAP.length*UNIT_SIZE/2)*(ballZ-(1+ballZz)*UNIT_SIZE+MAP.length*UNIT_SIZE/2))
					<ballR+ballRD//�h�P�_�y�߬O�_�b�}��
					){
				flagSY=false;//���i�}�̤F
				ballX=(1+ballXx)*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;
				ballZ=(ballZz+1)*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
				ballY=0;
				ballXx=ballXx+1;
				ballZz=ballZz+1;
			}
		}
		else if(MAP_OBJECT[ballZz+1][ballXx]==1){//�k�W����l�O�}
			if((float)Math.sqrt(
					(ballX-ballXx*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)*(ballX-ballXx*UNIT_SIZE+MAP[0].length*UNIT_SIZE/2)
					+(ballZ-(1+ballZz)*UNIT_SIZE+MAP.length*UNIT_SIZE/2)*(ballZ-(1+ballZz)*UNIT_SIZE+MAP.length*UNIT_SIZE/2))
					<ballR+ballRD//�h�P�_�y�߬O�_�b�}��
					){
						flagSY=false;//���i�}�̤F
						ballX=ballXx*UNIT_SIZE-MAP[0].length*UNIT_SIZE/2;
						ballZ=(ballZz+1)*UNIT_SIZE-MAP.length*UNIT_SIZE/2;
						ballY=0;
						ballZz=ballZz+1;
			}
		}
		
	}
	public float jsSDX(float vx,float vz,float cosa,float sina)//�J�쨤�ɭp��X��V���t�סA����Ӥ�k���q�b�t��V�e�i��
	{
	float vvx;
	vvx=-2*vz*sina*cosa+vx*cosa*cosa-vx*sina*sina;//�p�⦹��X�b��V���t��
		return Math.abs(vvx);//��^��
	}
	public float jsSDZ(float vx,float vz,float cosa,float sina)//�J�쨤�ɭp��Z��V���t�סC����Ӥ�k���q�b�t��V�e�i��
	{
	float vvz;	
	vvz=vz*sina*sina-vz*cosa*cosa+2*vx*cosa*sina;//�p�⦹�ɪ�Z��V�W���t��
		return Math.abs(vvz);
	}
}

