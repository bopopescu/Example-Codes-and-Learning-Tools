import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.applet.Applet;
import java.applet.AudioClip;

class SoundFrame extends JFrame implements ActionListener {
  JButton btnPlay, btnLoop, btnStop;
  File file = new File("music.wav");
  // �Q��Applet��newAudioClip��k���oAudioClip����
  AudioClip sound = Applet.newAudioClip(file.toURL());

  SoundFrame() throws Exception {
    btnPlay = new JButton("����");
    btnPlay.addActionListener(this);
    add(btnPlay);
    btnLoop = new JButton("�`������");
    btnLoop.addActionListener(this);
    add(btnLoop);
    btnStop = new JButton("����");
    btnStop.addActionListener(this);
    btnStop.setEnabled(false);
    add(btnStop);
    
    setTitle("��new AudioClip()���񭵼�");
    setLayout(new FlowLayout());
    setBounds(100, 100, 300, 100);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
  }  
    
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnPlay) {    
      sound.play();                    // �@������@�M
      btnPlay.setEnabled(false);
      btnStop.setEnabled(true);
    } 
    if (e.getSource() == btnLoop) { 
      sound.loop();                   // �`������
      btnLoop.setEnabled(false);
      btnPlay.setEnabled(false);
      btnStop.setEnabled(true);
    }
    if (e.getSource() == btnStop) {
      sound.stop();                   // �����
      btnLoop.setEnabled(true);
      btnPlay.setEnabled(true);
      btnStop.setEnabled(false);
    }
  }
}

public class J13_4_1 {
  public static void main(String[] args) throws Exception {
    SoundFrame frame = new SoundFrame();
  }
}