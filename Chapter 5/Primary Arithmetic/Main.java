/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;

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
  public myStuff(ArrayList<String> a) {
    file = a;
  }


  public int countCaries(long a,long b) {
    int count = 0;
    //System.out.println(a + " " + b);
    int carry = 0;
    while(a != 0 || b!=0) {
      if(a%10 + b %10 + carry >= 10) {
        count++;
        carry = 1;
      } else {
        carry = 0;
      }
      a /= 10;
      b /= 10;
    }
    return count;
  }

  void output(int i) {
    if(i == 0) {
      System.out.println("No carry operation.");
    } else if(i == 1) {
      System.out.println("1 carry operation.");
    
    } else {
      System.out.println(i + " carry operations.");

    }
  }
  public void run(){
    String s;
    long a, b;
    int caries=0;
    while(!(s=file.get(line++)).equals("0 0")) {
      String[] broken = s.split(" ");
      a = Long.parseLong(broken[0]);
      b = Long.parseLong(broken[1]);
      caries = countCaries(a,b);
      output(caries);
    }
  } 
}


