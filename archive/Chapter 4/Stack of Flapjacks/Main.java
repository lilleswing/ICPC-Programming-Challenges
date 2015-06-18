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

  public void reverse(ArrayList<Integer> a, int spot) {
    for(int i = 0; i <= spot/2; i+=1) {
      int temp = a.get(i);
      a.set(i,a.get(spot-i));
      a.set(spot-i,temp);
    }
  }

  public void move(ArrayList<Integer> a, int num, int spot) {
    int currSpot = a.indexOf(num);
    if(currSpot == 0) {
      System.out.print(a.size() - spot + " ");
      reverse(a,spot);
    } else {
      System.out.print((a.size() - currSpot) + " " + (a.size() - spot) + " ");
      reverse(a,currSpot);
      reverse(a,spot);
    }
  }

  public void run(){
    //ArrayList myArrayList = new ArrayList(Arrays.asList(file.get(line++).split(" ")));
    //ArrayList myArrayList = java.util.Arrays.asList(file.get(line++).split(" "));
    while(line < file.size()) {
      String[] s = file.get(line++).split(" ");
      ArrayList<Integer> unsorted = new ArrayList<Integer>(s.length);
      for(int x = 0; x < s.length; x++) {
        unsorted.add(Integer.parseInt(s[x]));
	if(x != s.length-1) {
  	  System.out.print(unsorted.get(x) + " ");
	}  else {
	  System.out.print(unsorted.get(x) + "\n");
	}
      }
      ArrayList<Integer> sorted = new ArrayList<Integer>(unsorted);
      Collections.sort(sorted);
      for(int point = sorted.size()-1; point >= 0; point--) {
        //System.out.print("(" + unsorted.get(x) + "," + sorted.get(x) + "),");
	//System.out.println(unsorted);
	if(unsorted.get(point) != sorted.get(point)) {
	  move(unsorted, sorted.get(point), point);
	}
      }

      System.out.print("0\n");
    }

  }
    
 // You can insert more classes here if you want.
}


