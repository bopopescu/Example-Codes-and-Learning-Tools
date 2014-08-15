package com.bn.gjxq;
import static com.bn.gjxq.Constant.UNIT_SIZE;

import javax.microedition.khronos.opengles.GL10;
//�ѽL�����L
public class ChessFoundation 
{
	FoundationSquar squar;//FoundationSquar���ޥ�
	RectWall cc;//RectWall���ޥ�
	float whith_1=9.5f;//�e��1
	float whith_2=10.5f;//�e��2
	float height=1;//����
    public ChessFoundation()
    {
    	squar=new FoundationSquar(whith_1,whith_2,height);//�Ыة��L�C�@�����x��
    	cc=new RectWall(whith_1+0.01f,whith_1+0.01f);
    	cc.z=0;
    }
    public void  drawSelf(GL10 gl,int texId,int texIdd)
    {
    	gl.glPushMatrix();//�O�@�x�}
    	gl.glTranslatef(0, -0.05f,0 );//�V�~�b����
    	
    	gl.glPushMatrix();
    	gl.glRotatef(-90, 1, 0, 0);//¶X�b�ϦV����90
    	
    	cc.drawSelf(gl, texIdd);//�i��ø�s
    	gl.glPopMatrix();
    	
    	gl.glPushMatrix();//�O�@��e�x�}
		gl.glTranslatef(0, 0, whith_1/2*UNIT_SIZE);//�N�䲾�ʨ�X�A��m
		squar.drawSelf(gl, texId);//�e���e�誺�@��
		gl.glTranslatef(0, 0, -whith_1*UNIT_SIZE);
		gl.glRotatef(180f, 0, 1, 0);
		squar.drawSelf(gl, texId);//�e�̫�誺�@��
		gl.glPopMatrix();//�e���e��⭱���_�x�}
		
		gl.glPushMatrix();	//�O�@��e�x�}		
		gl.glTranslatef(whith_1/2*UNIT_SIZE, 0, 0);//���ʨ�X�A��m
		gl.glRotatef(90f, 0, 1, 0);
		squar.drawSelf(gl, texId);
		gl.glTranslatef(0,0, -whith_1*UNIT_SIZE);
		gl.glRotatef(180f, 0, 1, 0);
		squar.drawSelf(gl, texId);
		gl.glPopMatrix();//��_�x�}
		gl.glPopMatrix();
    }
}

