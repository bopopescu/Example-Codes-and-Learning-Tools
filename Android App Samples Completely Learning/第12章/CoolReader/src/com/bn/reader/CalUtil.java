package com.bn.reader;


public class CalUtil 
{
	//�D�魶����W����I�����Ъ���k
	public static int[] calCD(float ax,float ay,float bx,float by)
	{
		//���ucd���ײv
		float kq=(bx-ax)/(ay-by);
		//p�I������
		float px=(ax+bx)/2;
		float py=(ay+by)/2;		
		//���ucd��b��
		float bq=py-kq*px;
		//c�I������
		float cx=(by-bq)/kq;
		float cy=by;
		//d�I������
		float dx=bx;
		float dy=kq*bx+bq;		
		return new int[]{(int)cx,(int)cy,(int)dx,(int)dy};
	}
}

