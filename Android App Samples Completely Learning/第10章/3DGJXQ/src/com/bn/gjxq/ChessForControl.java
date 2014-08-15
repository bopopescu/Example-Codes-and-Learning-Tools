package com.bn.gjxq;
import javax.microedition.khronos.opengles.GL10;
import static com.bn.gjxq.Constant.*;

public class ChessForControl
{
	boolean isMoved=false;//�O�_���ʹL
	LoadedObjectVertexNormalTexture object;//�[����3D����
	int chessType;  //0-11  0-5 ��ܶ´�,6-11 ��ܥմ�
	int col;//��e�Ѥl�Ҧb���C
	int row;//��e�Ѥl�Ҧb����
	float y=0;//��e�Ѥl�����׭�
	//�c�y��,�ǤJ�ѼƬ��Ѥl�ҫ��ޥ�,�Ѥl����,�Ѥl����e��m.
	public ChessForControl(LoadedObjectVertexNormalTexture object,int chessType,int row,int col)
	{
		this.object=object;//�Ѥl�ҫ����ޥ�
		this.chessType=chessType;	//�Ѥl������w��
		this.col=col;//�Ѥl�Ҧb��
		this.row=row;//�Ѥl�Ҧb�C
	}
	public void drawSelf(GL10 gl,int texId)//�e�^�������
	{
		gl.glPushMatrix();//�O�@�{��	
		gl.glTranslatef((0.5f+col-4)*UNIT_SIZE,0,(0.5f+row-4)*UNIT_SIZE);//�N�Ѥl���ʨ��������m
		gl.glTranslatef(0, 0.05f, 0);//�ѩ�ҫ�����],�ݭn�V�W���ʤ@�I
    	gl.glRotatef(((chessType>=0&&chessType<=5)?180:0), 0, 1, 0);//�p�G�O�¤�ݭn��ҫ�����180��
    	gl.glTranslatef(0, y, 0);//��Ѥl����w��,���e���@�I.
		object.drawSelf(gl,texId);//�e�^��
		gl.glPopMatrix();//��_�{��
	}
}

