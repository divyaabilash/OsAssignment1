
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class NewThread1 implements Runnable{

String linetocountwords;
Thread t;

NewThread1(String linetocount){
	linetocountwords = linetocount;
	t = new Thread(this);
	t.start();
}
	
	public void run() {
	System.out.println(linetocountwords);
	Scanner sc = new Scanner(linetocountwords);
	while(sc.hasNext()){
		String words=sc.next();
		System.out.println(words);
	}
	}
	
}

public class PrintingEachWordofLine {

	public static void main(String[] args) throws IOException {
		
//		Creating the File and Scanner for the reading the File.
		FileInputStream fileInput = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		Scanner scan = new Scanner(fileInput);
		
		while(scan.hasNextLine()){
			String ScannedLine = scan.nextLine();
//			if(ScannedLine.length() == 0 || ScannedLine.isEmpty() || ScannedLine == ""){
//				System.out.println("The file is Empty File");		
//			}
//			System.out.println(ScannedLine);
			new NewThread1(ScannedLine);
		}
		
		System.out.println("Closing the Scanner");
		scan.close();
		System.out.println("Closing the File");
		fileInput.close();
	}

}
