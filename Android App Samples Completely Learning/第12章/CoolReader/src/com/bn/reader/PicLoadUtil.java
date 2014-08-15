package com.bn.reader;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class PicLoadUtil 
{

   //�q�귽���[���@�T�Ϥ�
   public static Bitmap LoadBitmap(Resources res,int picId)
   {
	   Bitmap result=BitmapFactory.decodeResource(res, picId);
	   return result;
   }
   
   //�Y�����Ϥ�����k
   public static Bitmap scaleToFit(Bitmap bm,float targetWidth,float targetHeight)//�Y��Ϥ�����k
   {
   	float width = bm.getWidth(); //�Ϥ��e��
   	float height = bm.getHeight();//�Ϥ�����	
   	
   	Matrix m1 = new Matrix(); 
   	m1.postScale(targetWidth/width, targetHeight/height);//����	targetWidth/targetHeight �Y��Ϥ�
   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, m1, true);//�n�����        	
   	return bmResult;
   }
}

