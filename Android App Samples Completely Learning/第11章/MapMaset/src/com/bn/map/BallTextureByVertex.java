package com.bn.map;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import static com.bn.map.Constant.*;

import javax.microedition.khronos.opengles.GL10;

public class BallTextureByVertex {   
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
	private FloatBuffer   mNormalBuffer;//���I�k�V�q�ƾڽw��
    private FloatBuffer mTextureBuffer;//���I���z�ƾڽw��
    public float mAngleX;//�ux�b���ਤ��
    public float mAngleY;//�uy�b���ਤ�� 
    public float mAngleZ;//�uz�b���ਤ�� 
    int vCount=0;//���I�ƶq
    public BallTextureByVertex(float scale,float angleSpan) 
    {    	    	
    	//���������Ϫ����z�Ʋ�
    	float[] texCoorArray= 
         generateTexCoor
    	 (
    			 (int)(360/angleSpan), //���z�Ϥ������C��
    			 (int)(180/angleSpan)  //���z�Ϥ��������
    	);
        int tc=0;//���z�Ʋխp�ƾ�
        int ts=texCoorArray.length;//���z�Ʋժ���
    	
    	ArrayList<Float> alVertix=new ArrayList<Float>();//�s���I���Ъ�ArrayList
    	ArrayList<Float> alTexture=new ArrayList<Float>();//�s�񯾲z���Ъ�ArrayList
    	
        for(float vAngle=90;vAngle>-90;vAngle=vAngle-angleSpan)//������VangleSpan�פ@��
        {
        	for(float hAngle=360;hAngle>0;hAngle=hAngle-angleSpan)//������VangleSpan�פ@��
        	{
        		//�a�V��V�U��@�Ө��׫�p����������I�b�y���W���|��γ��I����
        		//�úc�ب�Ӳզ��|��Ϊ��T����
        		double xozLength=scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle));
        		float x1=(float)(xozLength*Math.cos(Math.toRadians(hAngle)));
        		float z1=(float)(xozLength*Math.sin(Math.toRadians(hAngle)));
        		float y1=(float)(scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));
        		
        		xozLength=scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle-angleSpan));
        		float x2=(float)(xozLength*Math.cos(Math.toRadians(hAngle)));
        		float z2=(float)(xozLength*Math.sin(Math.toRadians(hAngle)));
        		float y2=(float)(scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle-angleSpan)));
        		
        		xozLength=scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle-angleSpan));
        		float x3=(float)(xozLength*Math.cos(Math.toRadians(hAngle-angleSpan)));
        		float z3=(float)(xozLength*Math.sin(Math.toRadians(hAngle-angleSpan)));
        		float y3=(float)(scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle-angleSpan)));
        		
        		xozLength=scale*UNIT_SIZE*Math.cos(Math.toRadians(vAngle));
        		float x4=(float)(xozLength*Math.cos(Math.toRadians(hAngle-angleSpan)));
        		float z4=(float)(xozLength*Math.sin(Math.toRadians(hAngle-angleSpan)));
        		float y4=(float)(scale*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));   
        		
        		//�c�زĤ@�T����
        		alVertix.add(x1);alVertix.add(y1);alVertix.add(z1);
        		alVertix.add(x2);alVertix.add(y2);alVertix.add(z2);
        		alVertix.add(x4);alVertix.add(y4);alVertix.add(z4);        		
        		//�c�زĤG�T����
        		alVertix.add(x4);alVertix.add(y4);alVertix.add(z4);
        		alVertix.add(x2);alVertix.add(y2);alVertix.add(z2);
        		alVertix.add(x3);alVertix.add(y3);alVertix.add(z3); 
        		
        		//�Ĥ@�T����3�ӳ��I��6�ӯ��z����
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);        		
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);
        		//�ĤG�T����3�ӳ��I��6�ӯ��z����
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);        		
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);
        		alTexture.add(texCoorArray[tc++%ts]);       		
        	}
        } 	
        
        
        
        vCount=alVertix.size()/3;//���I���ƶq�����Эȼƶq��1/3�A�]���@�ӳ��I��3�ӧ���
    	
        //�NalVertix�������Э���s��@��int�Ʋդ�
        float vertices[]=new float[vCount*3];
    	for(int i=0;i<alVertix.size();i++)
    	{
    		vertices[i]=alVertix.get(i);
    	}
        
        //�Ы�ø�s���I�ƾڽw��
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
        vbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mVertexBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mVertexBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mVertexBuffer.position(0);//�]�m�w�İϰ_�l��m     
        
        //�Ыس��I�k�V�q�ƾڽw��
        ByteBuffer nbb = ByteBuffer.allocateDirect(vertices.length*4);
        nbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mNormalBuffer = vbb.asFloatBuffer();//�ഫ��int���w��
        mNormalBuffer.put(vertices);//�V�w�İϤ���J���I���мƾ�
        mNormalBuffer.position(0);//�]�m�w�İϰ_�l��m
        
        //�Ыد��z���нw��
        float textureCoors[]=new float[alTexture.size()];//���I���z�ȼƲ�
        for(int i=0;i<alTexture.size();i++) 
        {
        	textureCoors[i]=alTexture.get(i);
        }
        
        ByteBuffer tbb = ByteBuffer.allocateDirect(textureCoors.length*4);
        tbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mTextureBuffer = tbb.asFloatBuffer();//�ഫ��int���w��
        mTextureBuffer.put(textureCoors);//�V�w�İϤ���J���I�ۦ�ƾ�
        mTextureBuffer.position(0);//�]�m�w�İϰ_�l��m
    }

    public void drawSelf(GL10 gl,int texId)
    {
    	gl.glRotatef(mAngleZ, 0, 0, 1);//�uZ�b����
    	gl.glRotatef(mAngleX, 1, 0, 0);//�uX�b����
        gl.glRotatef(mAngleY, 0, 1, 0);//�uY�b����
        
        //���\�ϥγ��I�Ʋ�
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
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
		
        //�}�ү��z
//        gl.glEnable(GL10.GL_TEXTURE_2D);   
//        //���\�ϥί��zST���нw��
//        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        //���e�����w���zST���нw��
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mTextureBuffer);
        //�j�w��e���z
        gl.glBindTexture(GL10.GL_TEXTURE_2D, texId);
        
        //ø�s�ϧ�
        gl.glDrawArrays
        (
        		GL10.GL_TRIANGLES, 		//�H�T���Τ覡��R
        		0, 			 			//�}�l�I�s��
        		vCount					//���I�ƶq
        );
    }
    
    //�۰ʤ������z���ͯ��z�Ʋժ���k
    public float[] generateTexCoor(int bw,int bh)//�ǤJ�������C��  �A ���
    {
    	float[] result=new float[bw*bh*6*2]; 
    	float sizew=1.0f/bw;//�C�e
    	float sizeh=1.0f/bh;//��e
    	int c=0;
    	for(int i=0;i<bh;i++)
    	{
    		for(int j=0;j<bw;j++)
    		{
    			//�C��C�@�ӯx�ΡA�Ѩ�ӤT���κc���A�@�����I�A12�ӯ��z����
    			float s=j*sizew;
    			float t=i*sizeh;
    			
    			result[c++]=s;
    			result[c++]=t;
    			
    			result[c++]=s;
    			result[c++]=t+sizeh;
    			
    			result[c++]=s+sizew;
    			result[c++]=t;
    			
    			
    			result[c++]=s+sizew;
    			result[c++]=t;
    			
    			result[c++]=s;
    			result[c++]=t+sizeh;
    			
    			result[c++]=s+sizew;
    			result[c++]=t+sizeh;    			
    		}
    	}
    	return result;
    }
    
}

