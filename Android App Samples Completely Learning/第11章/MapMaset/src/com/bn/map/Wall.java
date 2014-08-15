package com.bn.map;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;
import static com.bn.map.GameSurfaceView.*;
import static com.bn.map.Constant.*;

//�������
public class Wall {
	private FloatBuffer   mVertexBuffer;//���I���мƾڽw��
    private FloatBuffer   mTextureBuffer;//���I���z�ƾڽw��
    private FloatBuffer   mNormalBuffer;//���I�k�V�q�ƾڽw��
    int vCount;//���I�ƶq
    private int[][] indexFlag;//�Ω�O����e�I�O�_���˹L   1 ��ܦ��I���ݭn�b����   0   ��ܦ��I�ݭn����
    
    public Wall()
    {
    	//���I���мƾڪ���l��================begin============================
        int rows=MAP.length;
        int cols=MAP[0].length;
        indexFlag=new int[rows][cols];
        
        ArrayList<Float> alVertex=new ArrayList<Float>();
        ArrayList<Float> alNormal=new ArrayList<Float>();
        ArrayList<Float> alTexture=new ArrayList<Float>();
        
        for(int i=0;i<rows;i++)//�汽��
        {
        	for(int j=0;j<cols;j++)//�C����
        	{//��a�Ϥ����C�@���i��B�z
        		if(MAP[i][j]==1)//��e�I����
        		{
        			int [][] area=returnMaxBlock(i,j);// area[0]��ܰ_�l�I    �� �C  area[1]��ܼe�שM����
        			for(int k=area[0][0];k<area[0][0]+area[1][1];k++)//��ϰ줺���C�� �I    �سy����
        			{
        				for(int t=area[0][1];t<area[0][1]+area[1][0];t++)
        				{
        					//�سy���h��
        					float xx1=t*UNIT_SIZE;       //    1
                			float y=FLOOR_Y+WALL_HEIGHT;
                			float zz1=k*UNIT_SIZE;
                			
                			float xx2=t*UNIT_SIZE;       //     2
                			float zz2=(k+1)*UNIT_SIZE;
                			
                			float xx3=(t+1)* UNIT_SIZE;    //   3
                			float zz3=(k+1)*UNIT_SIZE;
                			
                			float xx4=(t+1)*UNIT_SIZE;       //    4
                			float zz4=k*UNIT_SIZE;
                			//�c�y�T����
                			alVertex.add(xx1);alVertex.add(y);alVertex.add(zz1);
            				alVertex.add(xx2);alVertex.add(y);alVertex.add(zz2);
            				alVertex.add(xx3);alVertex.add(y);alVertex.add(zz3);

            				alVertex.add(xx3);alVertex.add(y);alVertex.add(zz3);
            				alVertex.add(xx4);alVertex.add(y);alVertex.add(zz4);
            				alVertex.add(xx1);alVertex.add(y);alVertex.add(zz1);
            				
            				//�K�[���z   ������Q
            				alTexture.add((float)((float)t/cols));alTexture.add((float)k/rows);
            				alTexture.add((float)((float)t/cols));alTexture.add((float)((float)(k+1)/rows));        				
            				alTexture.add((float)((float)(t+1)/cols));alTexture.add((float)((float)(k+1)/rows));
            				
            				alTexture.add((float)((float)(t+1)/cols));alTexture.add((float)((float)(k+1)/rows));
            				alTexture.add((float)((float)(t+1)/cols));alTexture.add((float)k/rows);
            				alTexture.add((float)((float)t/cols));alTexture.add((float)k/rows);
            				
            			

            				//�سy�V�q
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);
            				
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);
            				alNormal.add(0f);alNormal.add(1f);alNormal.add(0f);

            				//�سy�𪺤W��
            				if(k==0||MAP[k-1][t]==0)
            				{
            					float x1=t*UNIT_SIZE;
                				float y1=FLOOR_Y;
                				float z1=k*UNIT_SIZE;   //  1
                				
                				float x2=t*UNIT_SIZE;
                				float y2=FLOOR_Y+WALL_HEIGHT;
                				float z2=k*UNIT_SIZE;    // 2
                				
                				float x3=(t+1)*UNIT_SIZE;
                				float y3=FLOOR_Y+WALL_HEIGHT;
                				float z3=k*UNIT_SIZE;    //  3
                				
                				float x4=(t+1)*UNIT_SIZE;
                				float y4=FLOOR_Y;
                				float z4=k*UNIT_SIZE;    //  4
                				//�سy�T����
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				alVertex.add(x2);alVertex.add(y2);alVertex.add(z2);
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				alVertex.add(x4);alVertex.add(y4);alVertex.add(z4);
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				//�سy���z
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add(0f);
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add((float)((float)1/rows));        				
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add((float)((float)1/rows));
                				
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add((float)((float)1/rows));
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add(0f);
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add((float)1/rows);
                                //�سy�V�q
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
                				
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(-1f);
            				}
            				//�سy�𪺤U��
            				if(k==rows-1||MAP[k+1][t]==0)
            				{
            					float x2=t*UNIT_SIZE;
                				float y2=FLOOR_Y;
                				float z2=(k+1)*UNIT_SIZE;
                				
                				float x1=t*UNIT_SIZE;
                				float y1=FLOOR_Y+WALL_HEIGHT;
                				float z1=(k+1)*UNIT_SIZE;
                				
                				float x4=(t+1)*UNIT_SIZE;
                				float y4=FLOOR_Y+WALL_HEIGHT;
                				float z4=(k+1)*UNIT_SIZE;
                				
                				float x3=(t+1)*UNIT_SIZE;
                				float y3=FLOOR_Y;
                				float z3=(k+1)*UNIT_SIZE;
                				//�سy�T����
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				alVertex.add(x2);alVertex.add(y2);alVertex.add(z2);
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				alVertex.add(x4);alVertex.add(y4);alVertex.add(z4);
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				//�سy���z
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add(0f);
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add((float)((float)1/rows));        				
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add((float)((float)1/rows));
                				
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add((float)((float)1/rows));
                				alTexture.add((float)((float)(t+1-area[0][1])/cols));alTexture.add(0f);
                				alTexture.add((float)((float)(t-area[0][1])/cols));alTexture.add((float)1/rows);
                				//�سy�V�q
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
                				
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
                				alNormal.add(0f);alNormal.add(0f);alNormal.add(1f);
            				}
            				//�سy�𪺥���
            				if(t==0||MAP[k][t-1]==0)
            				{
            					float x2=t*UNIT_SIZE;
                				float y2=FLOOR_Y;
                				float z2=(k+1)*UNIT_SIZE;
                				
                				float x3=t*UNIT_SIZE;
                				float y3=FLOOR_Y+WALL_HEIGHT;
                				float z3=(k+1)*UNIT_SIZE;
                				
                				float x4=t*UNIT_SIZE;
                				float y4=FLOOR_Y+WALL_HEIGHT;
                				float z4=k*UNIT_SIZE;
                				
                				float x1=t*UNIT_SIZE;
                				float y1=FLOOR_Y;
                				float z1=k*UNIT_SIZE;
                				//�سy�T����
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				alVertex.add(x2);alVertex.add(y2);alVertex.add(z2);
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				alVertex.add(x4);alVertex.add(y4);alVertex.add(z4);
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				//�سy���z
                				alTexture.add(0f);alTexture.add((float)(k-area[0][0])/rows);
                				alTexture.add(0f);alTexture.add((float)((float)(k+1-area[0][0])/rows));        				
                				alTexture.add((float)((float)1/cols));alTexture.add((float)((float)(k+1-area[0][0])/rows));
                				
                				alTexture.add((float)((float)1/cols));alTexture.add((float)((float)(k+1-area[0][0])/rows));
                				alTexture.add((float)((float)1/cols));alTexture.add((float)(k-area[0][0])/rows);
                				alTexture.add(0f);alTexture.add((float)(k-area[0][0])/rows);
                				//�سy�V�q
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
                				
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(-1f);alNormal.add(0f);alNormal.add(0f);
            				}
            				//�سy�𪺥k��
            				if(t==cols-1||MAP[k][t+1]==0)
            				{
            					float x3=(t+1)*UNIT_SIZE;
                				float y3=FLOOR_Y;
                				float z3=(k+1)*UNIT_SIZE;
                				
                				float x2=(t+1)*UNIT_SIZE;
                				float y2=FLOOR_Y+WALL_HEIGHT;
                				float z2=(k+1)*UNIT_SIZE;
                				
                				float x1=(t+1)*UNIT_SIZE;
                				float y1=FLOOR_Y+WALL_HEIGHT;
                				float z1=k*UNIT_SIZE;
                				
                				float x4=(t+1)*UNIT_SIZE;
                				float y4=FLOOR_Y;
                				float z4=k*UNIT_SIZE;
                				//�سy�T����
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				alVertex.add(x2);alVertex.add(y2);alVertex.add(z2);
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				
                				alVertex.add(x3);alVertex.add(y3);alVertex.add(z3);
                				alVertex.add(x4);alVertex.add(y4);alVertex.add(z4);
                				alVertex.add(x1);alVertex.add(y1);alVertex.add(z1);
                				//�سy���z
                				alTexture.add(0f);alTexture.add((float)(k-area[0][0])/rows);
                				alTexture.add(0f);alTexture.add((float)((float)(k+1-area[0][0])/rows));        				
                				alTexture.add((float)((float)1/cols));alTexture.add((float)((float)(k+1-area[0][0])/rows));
                				
                				alTexture.add((float)((float)1/cols));alTexture.add((float)((float)(k+1-area[0][0])/rows));
                				alTexture.add((float)((float)1/cols));alTexture.add((float)(k-area[0][0])/rows);
                				alTexture.add(0f);alTexture.add((float)(k-area[0][0])/rows);
                				//�سy�V�q
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
                				
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
                				alNormal.add(1f);alNormal.add(0f);alNormal.add(0f);
            				}
        				}
        			}
        		}
        	}
        }
    	vCount=alVertex.size()/3;        
        float vertices[]=new float[alVertex.size()];
        for(int i=0;i<alVertex.size();i++)
        {
        	vertices[i]=alVertex.get(i);
        }
		
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
        
        //���I�k�V�q�ƾڪ�l��================begin============================
        float normals[]=new float[vCount*3];
        for(int i=0;i<vCount*3;i++)
        {
        	normals[i]=alNormal.get(i);
        }
        
        ByteBuffer nbb = ByteBuffer.allocateDirect(normals.length*4);
        nbb.order(ByteOrder.nativeOrder());//�]�m�r�`����
        mNormalBuffer = nbb.asFloatBuffer();//�ഫ��int���w��
        mNormalBuffer.put(normals);//�V�w�İϤ���J���I�ۦ�ƾ�
        mNormalBuffer.position(0);//�]�m�w�İϰ_�l��m
        //���I�k�V�q�ƾڪ�l��================end============================ 
        
        //���I���z�ƾڪ���l��================begin============================
        float textures[]=new float[alTexture.size()];
        for(int i=0;i<alTexture.size();i++)
        {
        	textures[i]=alTexture.get(i);
        }
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
    //�ھڷ�e�I  ��e�I��������   �P�_�X���I�P��̤j�����n����
    public int[][] returnMaxBlock(int rowIndex,int colIndex)
    {
    	int rowindex=rowIndex;
    	int colindex=colIndex;
    	int rowsize;//�Ω�O���檺�j�p
    	int colsize;//�Ω�O���C���j�p
    
    	int [][] area=new int[2][2];//�Ω�O����m area[0]��ܰ_�l�I����  area[1]��ܼe�שM����
    	area[0][0]=rowIndex;area[0][1]=colIndex;
    	//��V ���׬�1
    	int tempRowSize=1;//����
    	int tempColSize=1;//�e��
    	while(colindex+1<MAP[0].length&&MAP[rowindex][colindex+1]==1&&indexFlag[rowindex][colindex+1]==0)
    	{
    		tempColSize++;//�e�ץ[�@
    		colindex++;//�C���ޥ[�@
    	}
    	rowsize=tempRowSize;colsize=tempColSize;
    	area[1][0]=colsize;area[1][1]=rowsize;//��^��e�o���n��m
    	
    	//��V�e�׬�2
    	tempRowSize=0;tempColSize=0;rowindex=rowIndex;colindex=colIndex;//�ƾڪ�l��
    	while(colindex+1<MAP[0].length&&rowindex+1<MAP.length&&MAP[rowindex][colindex]==1&&
    	   indexFlag[rowindex][colindex]==0&&MAP[rowindex+1][colindex]==1&&indexFlag[rowindex+1][colindex]==0)
    	{
    		colindex++;
    		tempRowSize=2;
    		tempColSize++;//�C�ƥ[�@
    	}
    	if(tempColSize*tempRowSize>colsize)
    	{
    		rowsize=tempRowSize;colsize=tempColSize;
    		area[1][0]=colsize;area[1][1]=rowsize;//��^��e�o���n��m
    	}
//    	�a�V  �e�׬�1
    	tempRowSize=1;tempColSize=1;rowindex=rowIndex;colindex=colIndex;//�ƾڪ�l��
    	while(rowindex+1<MAP.length&&MAP[rowindex+1][colindex]==1&&indexFlag[rowindex+1][colindex]==0)
    	{
    		rowindex++;
    		tempRowSize++;
    	}
    	if(tempRowSize*tempColSize>rowsize*colsize)
    	{
    		rowsize=tempRowSize;colsize=tempColSize;
    		area[1][0]=1;area[1][1]=rowsize;//��^��e�o���n��m
    	}
    	//�a�V�e�׬�2
    	tempRowSize=0;tempColSize=0;rowindex=rowIndex;colindex=colIndex;//�ƾڪ�l��
    	while(colindex+1<MAP[0].length&&rowindex+1<MAP.length&&MAP[rowindex][colindex]==1&&
    	    	   indexFlag[rowindex][colindex]==0&&MAP[rowindex][colindex+1]==1&&indexFlag[rowindex][colindex+1]==0)
    	{
    	    rowindex++;
    	    tempColSize=2;
    	    tempRowSize++;//���ץ[1
    	}
    	if(tempColSize*tempRowSize>colsize*rowsize)
    	{
    	    rowsize=tempRowSize;colsize=tempColSize;
    	    area[1][0]=colsize;area[1][1]=rowsize;//��^��e�o���n��m
    	}
    	//�NindexFlag���˹L����l�m��1
    	for(int i=area[0][0];i<area[0][0]+area[1][1];i++)
    	{
    		for(int j=area[0][1];j<area[0][1]+area[1][0];j++)
    		{
    			indexFlag[i][j]=1;//�N��ȳ]�m��    1     ��ܤ��Φb����
    		}
    	}
    	return area;
    }
}

