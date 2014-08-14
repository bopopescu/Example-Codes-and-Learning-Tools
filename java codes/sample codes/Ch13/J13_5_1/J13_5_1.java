import java.awt.*;
import javax.swing.*;
import java.net.*;
import javax.media.*;

class JMFrame extends JFrame implements ControllerListener {
  Player mplayer;               // �ŧiPlayer���񾹪���mplayer   
  Component visual, control;    // �ŧi���W�έ��WComponent����
  int video_w = 0;              // ���W�e��  
  int video_h = 0;              // ���W����
  int inset_w = 8;              // �����~�ؼe�סA���k�U4
  int inset_h = 34;             // �����~�ذ��סA�W30�B�U4
  int control_h = 30;           // �������
    
  public JMFrame() throws Exception {
    setTitle("Java�h�C�鼽��");
    setLocation(100, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    URL url = new URL("file:man.avi");   // ���o��J��y 
    mplayer = Manager.createPlayer(url);   // ���o���񾹪���mplayer
	mplayer.addControllerListener(this);   // mplayer���񾹪���ť�ƥ�
	mplayer.prefetch();                   // �Ұʼ���\��
  }
 
  public void controllerUpdate(ControllerEvent e) {
 	if (e instanceof PrefetchCompleteEvent) {
	  visual = mplayer.getVisualComponent();   // ���o���W����
	  if (visual != null) {
	    Dimension size = visual.getPreferredSize();  //���o���W����ؤo
		video_w = size.width;                 // ���o���W�e��
		video_h = size.height;                // ���o���W����
		add("Center", visual);            // �����e���[�J���W����(����)
	  } else video_w = 300;               // �w�]���W�e��  
	  control = mplayer.getControlPanelComponent();	 // ���o���W���� 	 
	  control_h = control.getPreferredSize().height;  // ���o���W����
	  add("South", control);              // �����e���[�J���W����(����)
	  setSize(video_w + inset_w, video_h + control_h + inset_h); // �]�w�����j�p
      setVisible(true);                        // ��ܵ��� 
	  mplayer.start();                         // �ҥμ���s
    } 
    if (e instanceof EndOfMediaEvent) {
	  mplayer.setMediaTime(new Time(0));       // �`���A��
	  mplayer.start();                         // �ҥμ���s
	}
  }
}

public class J13_5_1 {
  public static void main(String[] args) throws Exception {
    JMFrame frame = new JMFrame();
  }
}