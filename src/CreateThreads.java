//Dynamic creation of threads for each line in the file 
//Printing each line from the thread

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class NewThread implements Runnable{

	String tofind;
	Thread t;
	Integer i =1;
	
	NewThread(String a1){
		tofind = a1;
		t= new Thread(this);
		t.start();
		
	}
	
	public void run() {
		System.out.println(tofind);
	}
	
}

public class CreateThreads {
	

	public static void main(String[] args) throws FileNotFoundException {
		
		FileInputStream fin = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		Scanner sc = new Scanner(fin);
		while(sc.hasNextLine()){
		String newLine = sc.nextLine();
		new NewThread(newLine);
//		System.out.println(newLine);
		
		}
		
	}
}
