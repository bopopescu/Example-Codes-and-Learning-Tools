import java.io.*;
public class J6_5_1 {
  public static void main(String[] args) throws IOException {
    BufferedReader keyin = new BufferedReader(
                           new InputStreamReader(System.in));
    System.out.print("�п�J CD �� DVD �H ");
    String st = keyin.readLine();
    System.out.println();      
        
    CCD p1 = new CCD();
    p1.Play(st);
       
    CDVD p2 = new CDVD();
    p2.Play(st);
  }
}

// �w�qIPlayer����
interface IPlayer {
  String d1 = "CD";
  String d2 = "DVD";
  void Play(String disk);
}

// �w�qCCD���O��@IPlayer����
class CCD implements IPlayer {
  public void Play(String disk) {
    if (disk.equals(d1)) 
      System.out.println ("�{�b���񪺬O�y���֡z\n");
  }
}

// �w�qCDVD���O��@IPlayer����
class CDVD implements IPlayer {
  public void Play(String disk) {
    if (disk.equals(d2)) 
      System.out.println ("�{�b���񪺬O�y�q�v�z\n");
  }
}
