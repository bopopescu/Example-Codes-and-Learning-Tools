package mahout.fansy.utils.fpg;

import org.apache.hadoop.io.Writable;

/**
 * <key,value>��������
 * @author Administrator
 *
 */
public interface IKVRegex {
	
	/**
	 * key �Ľ�������
	 * @return key�Ľ����ַ���
	 */
	public   String keyRegex(Writable key);
	
	/**
	 * value �Ľ�������
	 * @return value�Ľ����ַ���
	 */
	public String valueRegex(Writable value);
}
