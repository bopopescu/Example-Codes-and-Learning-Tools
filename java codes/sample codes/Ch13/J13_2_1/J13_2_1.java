import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;        // ���JJava Sound API�M��

class SoundFrame extends JFrame implements ActionListener {
  JButton btnPlay, btnLoop, btnStop;
  File file = new File("happy.wav");
  // ���o�����ɪ���J��y
  AudioInputStream sound = AudioSystem.getAudioInputStream(file);
  // �N���o����J��y���J�O����Clip
  DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
  // ���o���w��Clip����
  Clip player = (Clip)AudioSystem.getLine(info);
  
  SoundFrame() throws Exception {
    btnPlay = new JButton("����");
    btnPlay.addActionListener(this);
    add(btnPlay);
    btnLoop = new JButton("���Ƽ���");
    btnLoop.addActionListener(this);
    add(btnLoop);
    btnStop = new JButton("�Ȱ�");
    btnStop.addActionListener(this);
    btnStop.setEnabled(false);
    add(btnStop);
     
    player.open(sound);              // �Ұ�player�\��
    
    setTitle("��Clip���󼽩񭵼�");
    setLayout(new FlowLayout());
    setBounds(100, 100, 300, 80);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnPlay) {      
      player.start();                 // ����@�M
      //player.loop(1);                   
      btnPlay.setEnabled(false);
      btnStop.setEnabled(true);
    } 
    if (e.getSource() == btnLoop) {     
      player.loop(-1);                  // �`������   
      btnLoop.setEnabled(false);
      btnPlay.setEnabled(false);
      btnStop.setEnabled(true);
    }
    if (e.getSource() == btnStop) {     
      player.stop();                   // �Ȱ�����
      btnLoop.setEnabled(true);
      btnPlay.setEnabled(true);
      btnStop.setEnabled(false);
    }
  }
}   

public class J13_2_1 {
  public static void main(String[] args) throws Exception {
    SoundFrame frame = new SoundFrame();
  }
}