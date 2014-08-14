package mahout.fansy.utils.fpg;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.util.ReflectionUtils;
import org.apache.mahout.common.HadoopUtil;

public class ReadAndWritePatterns {

	/*
	 * ˽�л� ���캯����ֻ��ʹ�÷�������
	 */
	private ReadAndWritePatterns(){}
	
	/**
	 * ��ȡ<key,value>�������ݣ�д��HDFS�ļ�ϵͳ
	 * @param input ���������ļ�
	 * @param output ���HDFS�ļ���txt��ʽ��
	 * @param jobtracker ʹ�õ�jobtracker��ַ
	 * @param regex  ʹ�õĽ����࣬���ڽ���key��value
	 * @return �����Ƿ�ɹ�
	 * @throws IOException
	 */
	public static boolean readAndWritePatterns(String input,String output,String jobtracker,AKVRegex regex) throws IOException{
		boolean flag=true;
		
		Configuration conf=new Configuration();
		conf.set("mapred.job.tracker", jobtracker);
		
		FileSystem fsIn = FileSystem.get(URI.create(input), conf);
		FileSystem fsOut = FileSystem.get(URI.create(output), conf);
		HadoopUtil.delete(conf, new Path(output));
	    Path pathIn = new Path(input);
	    Path pathOut = new Path(output);
	    
	    SequenceFile.Reader reader = null;
	    FSDataOutputStream out = fsOut.create(pathOut);
	    try {
	      reader = new SequenceFile.Reader(fsIn, pathIn, conf);
	      Writable key = (Writable)
	        ReflectionUtils.newInstance(reader.getKeyClass(), conf);
	      Writable value = (Writable)
	        ReflectionUtils.newInstance(reader.getValueClass(), conf);
	      while (reader.next(key, value)) {
	    	  // read
	    	  String k=regex.keyRegex(key); 
	    	  String v=regex.valueRegex(value);
	          // write
	    	  if("".equals(v)||v==null){
	    		  continue;
	    	  }
	    	  out.writeChars(k+"\t"+v+"\n");
	      }
	    } catch(IOException e){
	    	flag=false;
	    }finally {
	      IOUtils.closeStream(reader);
	    }
		return flag;
	}
}
