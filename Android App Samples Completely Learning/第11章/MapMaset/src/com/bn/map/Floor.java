package com.bn.map;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;
import static com.bn.map.Constant.*;
//��ܦa�O����
public class Floor {
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
    private FloatBuffer mTextureBuffer;//���I���z�ƾڽw��
    private FloatBuffer mNormalBuffer;
    int vCount=0;//���I�ƶq
    int width;//�a�O��Vwidth�ӳ��
    int height;//�a�O�a�Vheight�ӳ��
    public Floor(int width,int height)
    {
    	this.width=width;
    	this.height=height;
    	//���I���мƾڪ���l��================begin============================
        vCount=6;//�C�Ӧa�O��6�ӳ��I
    	float []vertices=new float[]
    	{
    			-width*UNIT_SIZE/2,0,-height*UNIT_SIZE/2,
    			-width*UNIT_SIZE/2,0,height*UNIT_SIZE/2,
    			width*UNIT_SIZE/2,0,height*UNIT_SIZE/2,
    		
    			width*UNIT_SIZE/2,0,height*UNIT_SIZE/2,
    			width*UNIT_SIZE/2,0,-height*UNIT_SIZE/2,
    			-width*UNIT_SIZE/2,0,-height*UNIT_SIZE/2
    	};    	
    	ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m       
        float textures[]=new float[]
        {
        		0,0,
        		0,2,
        		2,2,
        		
        		2,2,
        		2,0,
        		0,0
        };
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(textures.length*4);
        tbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTextureBuffer= tbb.asFloatBuffer();//�ഫ��Float���w��
        mTextureBuffer.put(textures);//�V�w�İϤ���J���I�ۦ�ƾ�
        mTextureBuffer.position(0);//�]�m�w�İϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�r�`���Ǥ��P�ƾڳ椸���O�r�`���@�w�n�g�LByteBuffer
        //�ഫ�A����O�n�q�LByteOrder�]�mnativeOrder()�A�_�h���i��|�X���D
        //���I���мƾڪ���l��================end============================
        
//        ���I�k�V�q�ƾڪ���l��================begin============================
        float normals[]=new float[vCount*3];
        for(int i=0;i<vCount;i++)
        {
        	normals[i*3]=0;
        	normals[i*3+1]=1;
        	normals[i*3+2]=0;
        }

        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length*4);
        nbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mNormalBuffer = nbb.asFloatBuffer();//�ഫ��int���w��
        mNormalBuffer.put(normals);//�V�w�İϤ���J���I�ۦ�ƾ�
        mNormalBuffer.position(0);//�]�m�w�İϰ_�l��m
        //�S�O���ܡG�ѩ󤣦P���x�r�`���Ǥ��P�ƾڳ椸���O�r�`���@�w�n�g�LByteBuffer
        //�ഫ�A����O�n�q�LByteOrder�]�mnativeOrder()�A�_�h���i��|�X���D
        //���I�ۦ�ƾڪ���l��================end============================
        
        
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
        
        //���e�����w���zST���нw��
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //�j�w��e���z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
		
        gl.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
        //ø�s�ϧ�
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//�H�T���Τ覡��R
        		0, 			 			//�}�l�I�s��
        		vCount					//���I���ƶq
        );
    }
}

