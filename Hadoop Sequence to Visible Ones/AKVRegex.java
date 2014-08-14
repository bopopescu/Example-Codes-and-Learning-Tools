package mahout.fansy.utils.fpg;

import org.apache.hadoop.io.Writable;

public  class AKVRegex implements IKVRegex{
	
	/**
	 * key �Ľ�������
	 * @return key�Ľ����ַ���
	 */
	public  String keyRegex(Writable key){
		return key.toString();
	};
	
	/**
	 * value �Ľ�������
	 * @return value�Ľ����ַ���
	 */
	public String valueRegex(Writable value) {
		return value.toString();
	}
}
