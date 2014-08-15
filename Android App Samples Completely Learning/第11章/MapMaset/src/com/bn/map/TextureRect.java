package com.bn.map;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

// ��ܳ�ӯ��z�x�Ϊ���
public class TextureRect
{
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
    private FloatBuffer   mTextureBuffer;//���I�ۦ�ƾڽw��
    int vCount;    
    public TextureRect(float X_UNIT_SIZE,float Y_UNIT_SIZE,float[] textures)
    {
    	//���I���мƾڪ���l��================begin============================
        vCount=6;
        float vertices[]=new float[]
        {
        	-1*X_UNIT_SIZE,1*Y_UNIT_SIZE,0,
        	-1*X_UNIT_SIZE,-1*Y_UNIT_SIZE,0,
        	1*X_UNIT_SIZE,1*Y_UNIT_SIZE,0,
        	
        	-1*X_UNIT_SIZE,-1*Y_UNIT_SIZE,0,
        	1*X_UNIT_SIZE,-1*Y_UNIT_SIZE,0,
        	1*X_UNIT_SIZE,1*Y_UNIT_SIZE,0
        };
		
        //�Ыس��I���мƾڽw��
        //vertices.length*4�O�]���@�Ӿ�ƥ|�Ӧr�`
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�r�`���Ǥ��P�ƾڳ椸���O�r�`���@�w�n�g�LByteBuffer
        //�ഫ�A����O�n�q�LByteOrder�]�mnativeOrder()�A�_�h���i��|�X���D
        //���I���мƾڪ���l��================end============================
        
        //���I���z�ƾڪ���l��================begin============================
        

        
        //�Ыس��I���z�ƾڽw��
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTextureBuffer= tbb.asFloatBuffer();//�ഫ��Float���w��
        mTextureBuffer.put(textures);//�V�w�İϤ���J���I�ۦ�ƾ�
        mTextureBuffer.position(0);//�]�m�w�İϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�r�`���Ǥ��P�ƾڳ椸���O�r�`���@�w�n�g�LByteBuffer
        //�ഫ�A����O�n�q�LByteOrder�]�mnativeOrder()�A�_�h���i��|�X���D
        //���I���z�ƾڪ���l��================end============================
    }

    public void drawSelf(GL10 gl,int texId)
    {        
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//�ҥγ��I���мƲ�
        
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
    }
}

