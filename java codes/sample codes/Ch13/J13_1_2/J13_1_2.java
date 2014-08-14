import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.midi.*;     // ���J�M��

class MidiFrame extends JFrame implements ActionListener {
  JButton btnPlay, btnLoop, btnStop;
  Sequencer player;           // �ŧiSequencer�榡���񾹪���
  
  MidiFrame() throws Exception {           // ���w���O��k��X�ҥ~
    btnPlay = new JButton("����");
    btnPlay.addActionListener(this);
    add(btnPlay);
    btnStop = new JButton("����");
    btnStop.addActionListener(this);
    add(btnStop);
          
    setTitle("����MIDI����");
    setLayout(new FlowLayout());
    setBounds(100, 100, 200, 80);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    
    player = MidiSystem.getSequencer();         // �إ߼���
    Sequence sound = MidiSystem.getSequence(new File("song.midi"));
    player.setSequence(sound);                  // �]�w�����J��y
    player.open();                              // �Ұʼ���\��     
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btnPlay) player.start();     // ����
    if (e.getSource() == btnStop) player.stop();      // �Ȱ�
  }
}   

public class J13_1_2 {
  public static void main(String[] args) {
    try {
      MidiFrame frame = new MidiFrame();
    }
    catch(Exception exce) {
      System.out.println(exce);
    }
  }
  // public static void main(String[] args) throws Exception {
  //  MidiFrame frame = new MidiFrame();
  // }
}