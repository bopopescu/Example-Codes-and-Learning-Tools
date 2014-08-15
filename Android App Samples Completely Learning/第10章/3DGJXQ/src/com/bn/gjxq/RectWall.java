package com.bn.gjxq;

import static com.bn.gjxq.Constant.UNIT_SIZE;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class RectWall
{
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
    private FloatBuffer   mTextureBuffer;//���I���z�ƾڽw��
    int vCount;//���I��
    float x;//�V�X�b���ʨ쪺�a��
	float y;
	float z;
	public RectWall(float width,float height)
	{
		vCount=6;   	
    	float []vertices=new float[]
    	{
    			-width*UNIT_SIZE/2,height*UNIT_SIZE/2,0,
    			-width*UNIT_SIZE/2,-height*UNIT_SIZE/2,0,
    			width*UNIT_SIZE/2,-height*UNIT_SIZE/2,0,
    		
    			width*UNIT_SIZE/2,-height*UNIT_SIZE/2,0,
    			width*UNIT_SIZE/2,height*UNIT_SIZE/2,0,
    			-width*UNIT_SIZE/2,height*UNIT_SIZE/2,0
    	};    	
    	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m       
        float textures[]=new float[]
        {
        		0,0,
        		0,1f,
        		1f,1f,
        		
        		1f,1f,
        		1,0,
        		0,0
        };
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTextureBuffer= tbb.asFloatBuffer();//�ഫ��Float���w��
        mTextureBuffer.put(textures);//�V�w�İϤ���J���I�ۦ�ƾ�
        mTextureBuffer.position(0);//�]�m�w�İϰ_�l��m
	}
	
	 public void drawSelf(GL10 gl,int texId)
	    {
	    	
	        gl.glPushMatrix();
	        gl.glTranslatef(x, y, z);
	      //���e�����w���I���мƾ�
	        gl.glVertexPointer
	        (
	        		3,				//�C�ӳ��I�����мƶq��3  xyz 
	        		GL10.GL_FLOAT,	//���I���ЭȪ������� GL_FIXED
	        		0, 				//�s���I���мƾڤ��������j
	        		mVertexBuffer	//���I���мƾ�
	        );

	        //���e�����w���zST���нw��
	        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
	        //�j�w��e���z
	        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
			
	        //ø�s�ϧ�
	        gl.glDrawArrays
	        (
	        		GL10.GL_TRIANGLES, 		//�H�T���Τ覡��R
	        		0,
	        		vCount 
	        );
	        gl.glPopMatrix();
	    }
}

