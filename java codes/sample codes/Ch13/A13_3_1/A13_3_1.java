import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//import java.io.*;
import java.applet.Applet;             // ���JApplet�M�� 
import java.applet.AudioClip;          // ���JAudioClip����

public class A13_3_1 extends JApplet implements ActionListener {
  JButton btnPlay, btnLoop, btnStop;
  AudioClip sound;          // �ŧi AudioClip �������A����sound

  public void init() {
    setLayout(new FlowLayout());   
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
    
    // ���oAudioClip��J��y
    sound = getAudioClip(getCodeBase(),"happy.wav");
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