import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.midi.*;    // ���J�M�� 

class MidiFrame extends JFrame implements ActionListener {
  JButton btnPlay, btnStop;
  Sequencer player;           // �ŧiSequencer�榡���񾹪���
   
  MidiFrame() {
    btnPlay = new JButton("����");
    btnPlay.addActionListener(this);
    add(btnPlay);
    btnStop = new JButton("�Ȱ�");
    btnStop.addActionListener(this);
    add(btnStop);
        
    setTitle("����MIDI����");
    setLayout(new FlowLayout());
    setBounds(100, 100, 200, 80);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
     
    try {
      player = MidiSystem.getSequencer();             // �إ߼���
      File file = new File("song.midi");              // ���o������
      Sequence sound = MidiSystem.getSequence(file);  // ���o��J��y
      player.setSequence(sound);                      // �]�w�����J��y
      player.open();	                              // �Ұʼ���\��
    } 
    catch(Exception exce) {
      System.out.println(exce);
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnPlay) player.start();     // ����
    if (e.getSource() == btnStop) player.stop();      // �Ȱ�
  }
}   

public class J13_1_1 {
  public static void main(String[] args) {
    MidiFrame frame = new MidiFrame();
  }
}