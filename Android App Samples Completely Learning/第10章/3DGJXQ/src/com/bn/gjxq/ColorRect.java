package com.bn.gjxq;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import static com.bn.gjxq.Constant.*;
//�o�Ӭ�ø�s�ѽL�����C�@���C��x��
public class ColorRect 
{
	private FloatBuffer mVertexBuffer;//�n�����I�ƾڽw��
	private FloatBuffer mColorBuffer;//�n�����I�C��ƾڽw��
	int vCount;	//�n�����I���ƶq	
	public ColorRect(float[] colorArr)
	{
		 vCount=6;//�]�m���I�`�Ƭ�6
		 //���I�ƾ�
		 float vertices[]=new float[]//�Ω�s�x���I���Ʋ�
		 {
				 0,0,0,
				 0,0,UNIT_SIZE,
				 UNIT_SIZE,0,0,         //�Ĥ@�Ӱf�ɰw��¶���T����
				 
				 UNIT_SIZE,0,0,
				 0,0,UNIT_SIZE,
				 UNIT_SIZE,0,UNIT_SIZE   //�ĤG�Ӱf�ɰw��¶���T����
		 };
		 	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);//�r�`�w��
	        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
	        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��float���w��
	        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
	        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m
	        
	        //���I�C�� �Ʋ�
	        float colors[]=new float[vCount*4];
	        int c=0;//�n���@�Ӽлx
	        for(int i=0;i<vCount;i++)
	        {
	        	colors[c++]=colorArr[0];//�C����q R
	        	colors[c++]=colorArr[1];//�C����q G
	        	colors[c++]=colorArr[2];//�C����qB
	        	colors[c++]=colorArr[3];//�C����qA
	        }
	        ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
	        cbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
	        mColorBuffer= cbb.asFloatBuffer();//�ഫ��Float���w��
	        mColorBuffer.put(colors);//�V�w�İϤ���J���I�ۦ�ƾ�
	        mColorBuffer.position(0);//�]�m�w�İϰ_�l��m	        
	}
	public void drawSelf(GL10 gl)
	{
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);//�[�����I�Ʋի��w
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, mColorBuffer);//�[���C��Ʋի��w
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, vCount);//�H���I�T���Τ覡�i��ø�s
	}
}

