import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
//import java.applet.*;

public class A13_6_1 extends Applet implements ActionListener {
  int total_Photo = 5;          // �@5�i�A��0~4�i
  ImageIcon[] img = new ImageIcon[total_Photo]; // �Ϥ��}�C
  int page = 0;                 // �ثe���b�i�ܪ��Ϥ����X 
  JLabel lblPhoto = new JLabel();         // ��Ϥ������Ҫ��� 
  JButton btnPgUp = new JButton("�W��");  // �V�e�����Ϥ����s
  JButton btnPgDn = new JButton("�U��");  // �V������Ϥ����s
  JButton btnMusic;                       // �����n�}���������s            
  AudioClip music;               // �ŧiAudioClip����music
  boolean music_on;              // ���O�ثe���֬O�}����
  
  public void init() {
    for( int i = 0; i < total_Photo; i++ ) {
      img[i] = new ImageIcon(getImage(getCodeBase(), "fig/scene_" + i + ".jpg"));
    }
    lblPhoto.setBounds(10, 10, 600, 450);
    lblPhoto.setIcon(img[page]);
    add(lblPhoto);
      
    btnPgUp.setBounds(485, 465, 60, 30);
    btnPgUp.addActionListener(this);    // �ǲΪ���ť�覡(��7��)
    add(btnPgUp);      
    
    btnPgDn.setBounds(550, 465, 60, 30);
    btnPgDn.addActionListener(ListbtnPgDn);  // ��10����Ҫ���ť�覡
    add(btnPgDn);
    
    // ���oAudioClip��J��y�A������music����
    music = getAudioClip(getCodeBase(), "fig/bg_music.wav");
    music.loop();                      // ���ִ`������
    music_on = true;                   // ���ֳB�b�}�����A
    btnMusic = new JButton("������");
    btnMusic.setBounds(10, 465, 80, 30);
    add(btnMusic);
    btnMusic.addActionListener(new ActionListener() { // �s����ť�覡
      // btnMusic����ť�ƥ�
      public void actionPerformed(ActionEvent e) {
   	    if (music_on) {
          music.stop();                // �����ܩ�
   	      music_on = false;            // ���ֳB�b�������A
   	      btnMusic.setText("�}����");
   	    } else {
          music.loop();                // ���ִ`������
   	      music_on = true;             // ���ֳB�b�}�����A
   	      btnMusic.setText("������");
        }
      }
    } );
           
    setLayout(null);
    setBackground(Color.yellow);
    setVisible(true);
  }
  
  //  btnPgUp����ť�ƥ�
  public void actionPerformed(ActionEvent e) {
    page --;
   	if (page < 0) page = total_Photo-1;
    lblPhoto.setIcon(img[page]);
  }
  
  // btnPgDn����ť�ƥ�
  public ActionListener ListbtnPgDn = new ActionListener() {
    public void actionPerformed(ActionEvent e) {
      page ++;
   	  if ( page > total_Photo ) page = 0;
      lblPhoto.setIcon(img[page]);
    }
  };
}