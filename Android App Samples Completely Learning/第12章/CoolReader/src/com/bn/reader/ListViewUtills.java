package com.bn.reader;

import java.io.File;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListViewUtills
{
	String currentPath;//��e���|
	ReaderActivity reader;//Activity���ޥ�
	
	Boolean judgeTimes=false;//�PŪ�O�_�O�Ĥ@�����}�n��
	
	public ListViewUtills(ReaderActivity reader)
	{
		this.reader=reader;//����ޥ�
	}
	//�Ψ�������|�U�����Ʋ�
	public File[] getFiles(String filePath)
	{
		File[] files=new File(filePath).listFiles();//�����e�ؿ��U�����    	
		return files;
	}
	//�N�d�� ���C��K�[��ListView��
	public void intoListView(final File[] files,final ListView lv)
	{
		if(files!=null)//����C�����Ů�
		{
			if(files.length==0)//��e�ؿ�����
			{				
				File cf=new File(currentPath);//�����e���C�����|���������
				cf=cf.getParentFile();//������ؿ����
				currentPath=cf.getPath();//�O����e���C����|
				Toast.makeText
				(
						reader, 
						"�Ӥ�󧨬��šI�I", 
						Toast.LENGTH_SHORT
				).show();  
			}
			else
			{
				BaseAdapter ba=new BaseAdapter()//�ЫؾA�t��
				{
					@Override
					public int getCount() {
						return files.length;
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
						LinearLayout ll=new LinearLayout(reader);
						ll.setOrientation(LinearLayout.HORIZONTAL);//�]�m�¦V	
						ll.setPadding(5,5,5,5);//�]�m�|�P�d��
	
						//��l��ImageView
						ImageView  ii=new ImageView(reader);
						String s=files[arg0].getPath();
						File f=new File(s);//��o���ﹳ
						char c[]=s.toCharArray();
						int i=s.length();
						if(f.isDirectory())//�s�b����
						{
							ii.setImageDrawable(reader.getResources().getDrawable(R.drawable.sl_file));//�]�m�Ϥ�
						}else if(c[i-1]=='t'&&c[i-2]=='x'&&c[i-3]=='t')
						{
							ii.setImageDrawable(reader.getResources().getDrawable(R.drawable.sl_txt));
						}
						else
						{
							ii.setImageDrawable(reader.getResources().getDrawable(R.drawable.sl_else));
						}
						ii.setScaleType(ImageView.ScaleType.FIT_XY);//���ӭ�Ϥ��
						ii.setLayoutParams(new Gallery.LayoutParams(60,60));//�Ϥ����j�p�]�m
						ll.addView(ii);//�K�[��LinearLayout��
								
						//��l��TextView
						TextView tv=new TextView(reader);
						tv.setText(files[arg0].getName());//�K�[���W��
						tv.setTextSize(18);//�]�m�r��j�p
						tv.setTextColor(reader.getResources().getColor(R.color.white));//�]�m�r���C��
						tv.setPadding(5,5,5,5);//�]�m�|�P�d��
						tv.setGravity(Gravity.RIGHT);
						ll.addView(tv);//�K�[��LinearLayout��				
						return ll;
					}        	
				};
				lv.setAdapter(ba);//�]�m�A�t��
		            	
				lv.setOnItemClickListener//�]�m�襤��檺��ť��
				(
					new OnItemClickListener()
					{
						@Override
						public void onItemClick(AdapterView<?> arg0, View arg1,
								int arg2, long arg3) {
					
							File f=new File(files[arg2].getPath());//��o��e�I�������ﹳ
							if(f.isDirectory())//�s�b����
							{
								currentPath=files[arg2].getPath();//������|
								File[] fs=getFiles(currentPath);//�����e���|�U�Ҧ��l���
								intoListView(fs,lv);//�N�l���C���JListView��
							}
							else 
							{
								reader.saveCurrentData();//���Ѥ��e�]�Y�GConstant.FILE_PATH�ܤƤ��e�^�A�N��e�Ѫ������H���s�J�ƾڮw
								
								String s=files[arg2].getPath();//������|
								char c[]=s.toCharArray();
		        				int i=s.length();
		        				if(c[i-1]=='t'&&c[i-2]=='x'&&c[i-3]=='t')
		        				{
		        					
		        					Constant.FILE_PATH=files[arg2].getPath();//������|
									Constant.TEXTNAME=files[arg2].getName();//�����󪺦W�r
		        					Constant.CONTENTCOUNT=TextLoadUtil.getCharacterCount(Constant.FILE_PATH);//�ե�getCharacterCount��k   					
		        					//���ܥt�@���ѮɡA�ƾڮw���d��A�O�_�w�gŪ�L�o���Ѫ��O��
		        					try
		        					{
		        						judgeTimes=SQLDBUtil.judgeIsWhichTime(Constant.FILE_PATH);
		        						System.out.println("judgeTimes="+judgeTimes+"####");
		        					}catch(Exception e)
		        					{
		        						e.printStackTrace();
		        					}
		        					
		        					if(judgeTimes)//�p�G�O�Ĥ@�����}�o���ѡA
		        					{
		        						reader.saveCurrentData();//���Ѥ���A��e�H���s�J�ƾڮwBookRecord�A�_�h�L�k�s����(�S���Z�šA���̨Ӫ��ǥ�)
		        						Constant.CURRENT_LEFT_START=0;
			        					Constant.CURRENT_PAGE=0;
			        					reader.goToReaderView();
		        					}else//�_�h�A�b�ƾڮw�����X�ƾ�
		        					{
		        						try
		        						{
		        							//�b�ƾڮw�����XhashMap
		        							byte[] data=SQLDBUtil.selectRecordData(Constant.FILE_PATH);
		        							//��readerView����hashMap
		        							reader.readerView.currBook=SQLDBUtil.fromBytesToListRowNodeList(data);//�Nbyte���ƾ���Ƭ�hashMap�����ƾ�
		        							int page=SQLDBUtil.getLastTimePage(Constant.FILE_PATH);//�o��o���ѤW�@��Ū�쪺��m
		        							int fontsize=SQLDBUtil.getLastTimeFontSize(Constant.FILE_PATH);//�o��W�@�����r��j�p
		        							if(fontsize!=Constant.TEXT_SIZE)//�p�G�������}�P�W�@�����}�ɡA�r��j�p���P
		        							{
		        								reader.updataBookMarkAndHashMap();//��s���ҩΪ̬OhashMap
		        								
		        							}

		        							reader.readerView.currRR=reader.readerView.currBook.get(page);//�ھ�hashMap�����ި��XReadRecord����H�]�O����e�ѭ������W�I���ޡ^
		        			        		Constant.CURRENT_LEFT_START=reader.readerView.currRR.leftStart;//����e�ѭ����W���޽��
		        			        		Constant.CURRENT_PAGE=reader.readerView.currRR.pageNo;//����e�ѭ���page���      		
		        						}catch(Exception e)
		        						{
		        							e.printStackTrace();
		        						}
		        						
			        					reader.goToReaderView();
		        					}
		        				}else
		        				{
		        					Toast.makeText
		        					(
		        						reader, 
		        						"�Х��}.txt���", 
		        						Toast.LENGTH_SHORT
		        					).show();
		        				}
		        			}        						
						}
					}
				);
			}    
		}
		else
		{
			File cf=new File(currentPath);//�����e���C�����|���������
			cf=cf.getParentFile();//������ؿ����
			currentPath=cf.getPath();//�O����e���C����|
			Toast.makeText
			(
				reader, 
				"�w�g��ڥؿ��F", 
				Toast.LENGTH_SHORT
			).show();
		}
	}
}

