package com.bn.gjxq;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import static com.bn.gjxq.Constant.UNIT_SIZE;
public class FoundationSquar 
{
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
    private FloatBuffer   mTextureBuffer;//���I���z�ƾڽw�� 
    int vCount;//�O���w�I��
    public FoundationSquar(float whith_1,float whith_2,float height)
    {
    	vCount=6;
    	float []vertices=new float[]
    	 {
    		-whith_1/2*UNIT_SIZE,0,0,
    		-whith_2/2*UNIT_SIZE,-height*UNIT_SIZE,(whith_2/2-whith_1/2)*UNIT_SIZE,
    		whith_2/2*UNIT_SIZE,-height*UNIT_SIZE,(whith_2/2-whith_1/2)*UNIT_SIZE,
    		
    		whith_2/2*UNIT_SIZE,-height*UNIT_SIZE,(whith_2/2-whith_1/2)*UNIT_SIZE,
    		whith_1/2*UNIT_SIZE,0,0,
    		-whith_1/2*UNIT_SIZE,0,0,
    	};
    	
    	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m
        
        float textures[]=new float[]
        {
        		1.5f/11,0,
        		0,1f,
        		1f,1f,
        		
        		1f,1f,
        		9.5f/11,0,
        		1.5f/11
        };
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTextureBuffer= tbb.asFloatBuffer();//�ഫ��Float���w��
        mTextureBuffer.put(textures);//�V�w�İϤ���J���I�ۦ�ƾ�
        mTextureBuffer.position(0);//�]�m�w�İϰ_�l��m
    }
    
    public void drawSelf(GL10 gl,int texId)
    {
    	//���e�����w���I���мƾ�
        gl.glVertexPointer
        (
        		3,				//�C�ӳ��I�����мƶq��3  xyz 
        		GL10.GL_FLOAT,	//���I���ЭȪ������� GL_FIXED
        		0, 				//�s���I���мƾڤ��������j
        		mVertexBuffer	//���I���мƾ�
        );
        
      //�}�ү��z
        gl.glEnable(GL10.GL_TEXTURE_2D);   
        //���\�ϥί��zST���нw��
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
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
    }
}

