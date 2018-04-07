import java.io.*;
import java.security.*;
import javax.xml.bind.*;

public class DigestThread extends Thread {

  private String filename;

  public DigestThread(String filename){
    this.filename = filename;
  }

  @Override
  public void run(){
    try{
      FileInputStream in = new FileInputStream(filename);
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);

      while(din.read() != -1);
      din.close();
      byte[] digest = sha.digest();

      StringBuilder result = new StringBuilder(filename);
      result.append(": ");
      result.append(DatatypeConverter.printHexBinary(digest));
      System.out.println(result.toString());
    }catch(IOException ex){
      System.err.println(ex);
    }catch(NoSuchAlgorithmException ex){
      System.err.println(ex);
    }
  }

  public static void main(String[] args){
    for(String filename : args){
      Thread t = new DigestThread(filename);
      t.setPriority(8);
      t.start();
    }
  }

  // System.out is synchronized
  // but once each thread is performing multiple prints
  // the order of each substring's output is not guaranteed
  public void run_not_synchronized() {
    try {
      FileInputStream in = new FileInputStream(filename);
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1) ; // read entire file
      din.close();
      byte[] digest = sha.digest();
      System.out.print(filename + ": ");
      System.out.print(DatatypeConverter.printHexBinary(digest));
      System.out.println();
    } catch (IOException ex) {
      System.err.println(ex);
    } catch (NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }

  // synchronize a block of statements
  public void run_synchronized() {
    try {
      FileInputStream in = new FileInputStream(filename);
      MessageDigest sha = MessageDigest.getInstance("SHA-256");
      DigestInputStream din = new DigestInputStream(in, sha);
      while (din.read() != -1) ; // read entire file
      din.close();
      byte[] digest = sha.digest();
      synchronized (System.out) {
        System.out.print(filename + ": ");
        System.out.print(DatatypeConverter.printHexBinary(digest));
        System.out.println();
      }
    } catch (IOException ex) {
      System.err.println(ex);
    } catch (NoSuchAlgorithmException ex) {
      System.err.println(ex);
    }
  }
}
