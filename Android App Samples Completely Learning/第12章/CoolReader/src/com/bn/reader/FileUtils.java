package com.bn.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import android.os.Environment;
import android.widget.Toast;

public class FileUtils{
	private String SDPATH;//�Ω�sSD�d����󪺸��|
	private ReaderActivity ra;
	//������|
	public FileUtils(ReaderActivity ra) {
		this.ra=ra;
		SDPATH=Environment.getExternalStorageDirectory()+"/";//��o��e�~���s�x�]�ƪ��ؿ�(�ھڪ������P���|���P)
	}
	//�Ыؤ��
	public File createSDFile(String fileName) throws IOException{
		File file=new File(SDPATH+fileName);
		file.createNewFile();
		return file;
	}
	//�Ыؤ��ؿ�
	public File createSDDir(String dirName){
		File dir=new File(SDPATH+dirName);
		dir.mkdir();
		return dir;
	}
	//�P�_���O�_�s�b
	public boolean isFileExist(String fileName){
		File file=new File(SDPATH+fileName);
		return file.exists();
	}
	// �NinputStream�����g�JSD�d 
	//path �l�ؿ��W  fileName �O�s���W inputStream �q�LURL�������J�y
	public File writeToSDFromInput(String path,String fileName,InputStream inputStream){
		File file=null;
		OutputStream output=null;
		try {
			file=createSDFile(path+fileName);//�Ыإؿ��M���
			output=new FileOutputStream(file);//�Ыؿ�X�y 
			byte buffer[]=new byte[4*1024];//�@��Ū����󪺪���
			if((inputStream.read(buffer))!=-1){
				output.write(buffer);
			}else{
				Toast.makeText
				(
						ra, 
						"�w�g�s�b�Ӥ��L�ݤU��"
						,Toast.LENGTH_SHORT 
				).show();
			}
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				output.close();//������X�y
				Toast.makeText
				(
						ra, 
						"�U������"
						,Toast.LENGTH_SHORT 
				).show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
  
		return file;
	}
	public InputStream getInputStreamFromUrl(String urlStr) throws IOException{//�����X�y
		 URL url;
		 url=new URL(urlStr);
		 URLConnection urlCon=url.openConnection();
		 InputStream inputStream=urlCon.getInputStream();
		 return inputStream;
  }

}

