package com.bn.gjxq;
import static com.bn.gjxq.Constant.*;

import javax.microedition.khronos.opengles.GL10;
//ø�s�ѽL��
public class ChessboardForDraw
{
	//�C��x�μƲ�
    ColorRect[] cr;
	public ChessboardForDraw()//�c�y��
	{
		ColorRect cr1=new ColorRect(COLORARR[0]);//�զ�x��
		ColorRect cr2=new ColorRect(COLORARR[1]);//�¦�x��
		ColorRect cr3=new ColorRect(COLORARR[2]);//����x��
		//�ЫؤT�ӹﹳ
		cr=new ColorRect[]
		        {
				 cr1,
				 cr2,
				 cr3
		        };
	}
	public void drawself(GL10 gl)
	{
		//colorRect����
		for(int j=-4;j<4;j++)//�`��ø�s�ѽL
		{
			for(int i=-4;i<4;i++)
			{ 
				if(MySurfaceView.road[j+4][i+4]!=1)//�p�G��e�S������
				{
			      gl.glPushMatrix();
	              gl.glTranslatef(i*UNIT_SIZE, 0, j*UNIT_SIZE);//�N�C��x�β��ʨ���w��m
	              cr[Math.abs((i+j)%2)].drawSelf(gl);//ø�s�ѽL
	              gl.glPopMatrix();
				}
				else//�p�G��e������,�ѽL�C���ܬ�
				{
					 gl.glPushMatrix();
		             gl.glTranslatef(i*UNIT_SIZE, 0, j*UNIT_SIZE);//�N�C��x�β��ʨ���w��m
		             cr[2].drawSelf(gl);//ø�s���|
		             gl.glPopMatrix();
				}
			}
		}
	}
}
