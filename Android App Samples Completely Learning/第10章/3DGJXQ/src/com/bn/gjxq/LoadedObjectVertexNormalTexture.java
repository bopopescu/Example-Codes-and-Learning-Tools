package com.bn.gjxq;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
//�[���᪺����X�X��a���I�B�k�V�q�B���z�H��
public class LoadedObjectVertexNormalTexture 
{
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
	private FloatBuffer   mNormalBuffer;//���I�k�V�q�ƾڽw��
	private FloatBuffer   mTexBuffer;//���I���z�ƾڽw��
    int vCount=0;  
    public LoadedObjectVertexNormalTexture(float[] vertices,float[] normals,float texCoors[]) 
    {	
    	//���I���мƾڪ���l��================begin============================
        vCount=vertices.length/3;    
        //�Ыس��I���мƾڽw��
        //vertices.length*4�O�]���@�Ӿ�ƥ|�Ӧr�`
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��Float���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�r�`���Ǥ��P�ƾڳ椸���O�r�`���@�w�n�g�LByteBuffer
        //�ഫ�A����O�n�q�LByteOrder�]�mnativeOrder()�A�_�h���i��|�X���D
        //���I���мƾڪ���l��================end============================
        
        //�k�V�q�H����l��
        ByteBuffer vbn = ByteBuffer.allocateDirect(normals.length*4);
        vbn.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mNormalBuffer = vbn.asFloatBuffer();//�ഫ��Float���w��
        mNormalBuffer.put(normals);//�V�w�İϤ���J���I���мƾ�
        mNormalBuffer.position(0);//�]�m�w�İϰ_�l��m 
        
        //���z���нw�Ī�l��
        ByteBuffer vbt = ByteBuffer.allocateDirect(texCoors.length*4);
        vbt.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTexBuffer = vbt.asFloatBuffer();//�ഫ��Float���w��
        mTexBuffer.put(texCoors);//�V�w�İϤ���J���I���мƾ�
        mTexBuffer.position(0);//�]�m�w�İϰ_�l��m 
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
        
        //���e�����w���I�k�V�q�ƾ�
        gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
        
        //���e�����w���zST���нw��
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTexBuffer);
        //�j�w��e���z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
		
        //ø�s�ϧ�
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//�H�T���Τ覡��R
        		0, 			 			//�}�l�I�s��
        		vCount					//���I���ƶq
        );        
        
       
    }
}

