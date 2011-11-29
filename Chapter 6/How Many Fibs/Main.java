import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

class Main implements Runnable{
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
        Main myWork = new Main();  // Construct the bootloader
        try {
			System.setIn(new FileInputStream("in.in"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        myWork.run();            // execute
    }

    public void run() {
        new myStuff().run();
    }
}
class myStuff implements Runnable{
	ArrayList<BigInteger> dp = new ArrayList<BigInteger>();
	BigInteger maxVal;
	private void fillDP() {
		dp.add(BigInteger.ONE);
		dp.add(BigInteger.ONE);
		String maxString = "1";
		for(int i = 0; i < 10; i++) {
			maxString += "0000000000";
		}
		maxVal = new BigInteger(maxString);
		int i = 2;
		while(dp.get(i-1).compareTo(maxVal) <= 0) {
			dp.add(dp.get(i-2).add(dp.get(i-1)));
			i++;
		}
		dp.set(0, new BigInteger("-1"));
		//System.out.println(i);
	}
    public void run(){
    		String s;
    		fillDP();
    		while(!(s=Main.ReadLn(10000).trim()).equals("0 0")) {
    			//s = s.trim();
    			//System.out.println(s.trim());
    			String[] vars = s.split(" ");
    			BigInteger a = new BigInteger(vars[0]);
    			BigInteger b = new BigInteger(vars[1]);
    			//System.out.println(a +":"+ b);
    			int counter = 0;
    			for(BigInteger check : dp) {
    				if(a.compareTo(check) <= 0 && b.compareTo(check) >= 0) {
    					counter++;
    				}
    			}
    			System.out.println(counter);
    		}
        	
        }
    
    // You can insert more classes here if you want.
}

