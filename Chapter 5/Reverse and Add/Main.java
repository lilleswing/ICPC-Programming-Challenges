/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;
import java.math.*;

class Main implements Runnable{
  public static ArrayList<String> a;
  static String ReadLn(int maxLength){  // utility function to read from stdin,
                                        // Provided by Programming-challenges, edit for style only
      byte line[] = new byte [maxLength];
      int length = 0;
      int input = -1;
      try{
          while (length < maxLength){//Read untill maxlength
              input = System.in.read();
              if ((input < 0) || (input == '\n')) break; //or untill end of line ninput
              line [length++] += input;
          }

          if ((input < 0) && (length == 0)) return null;  // eof
          return new String(line, 0, length);
      }catch (IOException e){
          return null;
      }
  }

  public static void main(String args[])  // entry point from OS
  {
     a = new ArrayList<String>();
     String s;
     while((s=ReadLn(100000)) != (null)) {
       a.add(s);
     }
     Main myWork = new Main();  // Construct the bootloader
     myWork.run();            // execute
  }

  public void run() {
      new myStuff(a).run();
  }
}
class myStuff implements Runnable{
  ArrayList<String> file;
  int line = 0;
  int numTrials;
  public myStuff(ArrayList<String> a) {
    file = a;
  }
  public boolean isPal(BigInteger b) {
    String reverse = new StringBuffer(b.toString()).reverse().toString();
    return b.toString().equals(reverse);
  }
  public String backwords(BigInteger b) {
    String reverse = new StringBuffer(b.toString()).reverse().toString();
    return reverse;
  }

  public BigInteger flipAdd(BigInteger b) {
    BigInteger c = new BigInteger(backwords(b));
    BigInteger d = b.add(c);
    return d;

  }


  public void run(){
    numTrials = Integer.parseInt(file.get(line++));
    for(int trials = 0; trials<numTrials; trials++) {
      BigInteger b = new BigInteger(file.get(line++));
      int iterations = 0;
      while(!isPal(b)) {
        b = flipAdd(b);
        iterations++;
      }
      System.out.println(iterations + " " + b);

    }
  } 
}

