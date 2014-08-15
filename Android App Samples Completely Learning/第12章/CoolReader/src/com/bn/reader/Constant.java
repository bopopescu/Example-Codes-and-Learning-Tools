package com.bn.reader;

public class Constant
{
	//directions�]�m
	public static String DIRECTIONSNAME="directions.txt"; //APK���۱a�������W�r
	//�U���Ψ�URL
	public static final String ADD_PRE="http://192.168.1.100:8080/txt/";//�s��奻���URL
	//½���Ψ쪺�`�q��
	public static int CURRENT_LEFT_START;//�O����eŪ��BLeftStart����
	public static int CURRENT_PAGE;//�O����eŪ��B��page����
	public static int nextPageStart;//�U�@�����_�l�r�Ŧb�r�Ŧꤤ����m
	public static int nextPageNo;//�U�@�������X
	public static int CONTENTCOUNT;//�奻���`�r�ż�
	//�s�i���Ψ쪺�`�q��
	public static int AD_WIDTH=120;//�s�i����ڪ���
	public static int NUM;//�s�i�Ϫ��ƶq
	//��l�I���M�r���C��
	public static int BITMAP=R.drawable.bg_sjzx;//��l���I���Ϥ�
	public static int COLOR=0xffffff00;//��l���r���C��
	//�̹����j�p
	public static int SCREEN_WIDTH;//�̹����e��
	public static int SCREEN_HEIGHT;//�̹�������
	//���k�ⰼ�Ϥ�����m
	public static int LEFT_OR_RIGHT_X=0;//�����Ϥ������I��x����
	public static int RIGHT_OR_LEFT_x;//�k���Ϥ������I��x����
	//��r���]�m
	public static int PAGE_LENGTH;//�C��Ū����r�Ӽ�
	public static int TEXT_SPACE_BETWEEN_EN=8;//�奻���Z-�^��
	public static int TEXT_SPACE_BETWEEN_CN=16;//�奻���Z-����
	public static int TEXT_SIZE=16;//�奻�j�p
	public static int TITLE_SIZE=25;//���Y�奻���r��j�p
	//���������]�m
	public static int ROWS;//�����������
	public static int PAGE_WIDTH;//���������e��
	public static int PAGE_HEIGHT;//������������
	//�s��奻�����|
	public static String FILE_PATH; //�s��奻�����|
	public static String TEXTNAME;//��e�\Ū����󪺦W�r
	//�W��d��
	public static int BLANK=30;//�W��d��
	//�������νu
	public static int CENTER_WIDTH=4;//�������α��e��
	public static int CENTER_LEFT_Y=BLANK;//�������νu���W��y����
	public static int CENTER_LEFT_X;//�������νu���W��x����	
	public static int CENTER_RIGHT_X;//�������νu �k�U��x����
	public static int CENTER_RIGHT_Y;//�������νu �k�U��y����
	//�I�����֪�����
	public static int I;//�I�����֪�R��
	
	//�ھګ̹�����v�ӧ��`�q��
	public static void changeRatio()
	{
		//���νu�۾A��
		CENTER_LEFT_X=(SCREEN_WIDTH-CENTER_WIDTH)/2;//���������νu���W��x����
		CENTER_RIGHT_X=CENTER_LEFT_X+CENTER_WIDTH-1;//���������νu �k�U��x����
		CENTER_RIGHT_Y=SCREEN_HEIGHT;//���������νu �k�U��y����
		//�k���Ϥ����w�IX���Ц۾A��
		RIGHT_OR_LEFT_x=CENTER_RIGHT_X+1;//���k���Ϥ����w�I��x����
		//���������e�۾A��
		PAGE_WIDTH=(SCREEN_WIDTH-CENTER_WIDTH)/2;//���������e��
		PAGE_HEIGHT=SCREEN_HEIGHT-BLANK;//������������
		//�������奻��Ʀ۾A��
		ROWS=PAGE_HEIGHT/TEXT_SIZE;//�C�ӵ������W�奻���
		//�C��Ū���奻����r���Ӽ�
		PAGE_LENGTH=(PAGE_WIDTH/TEXT_SPACE_BETWEEN_EN+1)*(PAGE_HEIGHT/TEXT_SIZE+1);//�C��Ū����r�Ӽƽ��
	}
}
