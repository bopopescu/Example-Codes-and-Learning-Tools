package com.bn.reader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DownLoad
{
	String []txtName;//�s��ؿ��C�����W�r
	String listName;//��Ū���ؿ������e�r�Ŧ�
	ListView lv;//�n��ListView���ޥ�
	ReaderActivity ra;//Activity���ޥ�
	
	public DownLoad(String path,ReaderActivity ra)//�Ψӳ]�m�U���C��
	{
		this.ra=ra;//����Activity ���ޥ�
		ra.goToBackground();
		lv=(ListView)ra.findViewById(R.id.background);
		listName=getTxtInfo(path);
		initListView(listName);//��l�Ƥ奻�ؿ��C��
		
	}
	public String getTxtInfo(String name)
	{
		String result=null;
		try
		{
			URL url=new URL(Constant.ADD_PRE+name);//����s��URL
			URLConnection uc=url.openConnection();//�}�ҳs��
			InputStream is=uc.getInputStream();//�q�L�s�����}��J�y
			int ch=0;
			ByteArrayOutputStream baos = new ByteArrayOutputStream();//�}�Ҧr�`�Ʋտ�X�y
			while((ch=is.read())!=-1)//�bŪ���������e�A�N�C��Ū�������G�s�J��X�y��
		    {
		      	baos.write(ch);
		    }      
		    byte[] bb=baos.toByteArray();//�N��X�y�����e�ɤJ��bb�r�`�Ʋդ�
		    baos.close();//������X�y
		    is.close();//������J�y 
		    result=new String(bb,"UTF-8");//�N�r�`�Ʋդ������e����"UTF-8"�s�X
		    result=result.replaceAll("\\r\\n","\n");//�åδ���Ŵ����^����
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return result;
	}
	public void initListView(String s)
	{
		txtName=s.split("\\|");//�����W�r�Ʋս��
		final int i=txtName.length/2;
		
		BaseAdapter ba=new BaseAdapter()//�ЫؾA�t��
		{
			@Override
			public int getCount() {
				return i;
			}
			@Override
			public Object getItem(int position) {
				return null;
			}

			@Override
			public long getItemId(int position) {				
				return 0;
			}
        			
			@Override
			public View getView(int arg0, View arg1, ViewGroup arg2) {
        				
				//��l��LinearLayout
				LinearLayout ll=new LinearLayout(DownLoad.this.ra);//�Ы�LinearLayout
				ll.setOrientation(LinearLayout.HORIZONTAL);//�]�m�¦V	
				ll.setPadding(5,5,5,5);//�]�m�|�P�d��

				//��l��ImageView
				ImageView  ii=new ImageView(DownLoad.this.ra);//�Ы�ImageView
				ii.setImageDrawable(ra.getResources().getDrawable(R.drawable.sl_txt));//�]�m�Ϥ�
				ii.setScaleType(ImageView.ScaleType.FIT_XY);//���ӭ�Ϥ��
				ii.setLayoutParams(new Gallery.LayoutParams(60,60));//Image���j�p�]�m
				ll.addView(ii);//�K�[��LinearLayout��
						
				//��l��TextView
				TextView tv=new TextView(DownLoad.this.ra);//�Ы�TextView
				tv.setText(txtName[arg0*2]);//�K�[���W��
				tv.setTextSize(18);//�]�m�r��j�p
				tv.setTextColor(DownLoad.this.ra.getResources().getColor(R.color.white));//�]�m�r���C��
				tv.setPadding(5,5,5,5);//�]�m�|�P�d��
				tv.setGravity(Gravity.RIGHT);
				ll.addView(tv);//�K�[��LinearLayout��
				
				return ll;
			}        	
		};
		lv.setAdapter(ba);//�]�m�A�t��
	
	}
	public void downFile(String urlStr,String path,String fileName){
		InputStream inputStream=null;//��J�y�ޥ�
		FileUtils fileUtils=new FileUtils(ra);//�Ыؤ��U���u����  
		try {
			if(fileUtils.isFileExist(path+fileName)){//�p�G�s�b�N���A�U��
				Toast.makeText
				(
						ra, 
						"�w�g�s�b�Ӥ��L�ݤU��"
						,Toast.LENGTH_SHORT 
				).show();
			}else{
				inputStream=fileUtils.getInputStreamFromUrl(urlStr);//�q�L�s�������J�y
				File resultFile=fileUtils.writeToSDFromInput(path, fileName, inputStream);//���J�y�������g�JSD�d
				try{
				inputStream.close();//������������J�y
				}catch(IOException e){
					e.printStackTrace();
				}
				if(resultFile==null){
					Toast.makeText//�p�G�奻��?�M�y��`?
					(
							ra, 
							"�Ӥ�󬰪�"
							,Toast.LENGTH_SHORT 
					).show();
				}
			}
		}catch (IOException e){
			e.printStackTrace();
		}	
	}
}
