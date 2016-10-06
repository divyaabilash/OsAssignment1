import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Newthread12 implements Runnable{

String linetocountwords;
Thread t;
ArrayList<String> arrayofwords = new ArrayList<String>();


Newthread12(String linetocount,ArrayList<String> array1){
	linetocountwords = linetocount;
	array1=arrayofwords;
	t = new Thread(this);
	t.start();
	}
	
	public void run() {
//	System.out.println(linetocountwords);
	Scanner sc = new Scanner(linetocountwords);
	while(sc.hasNext()){
		String words=sc.next();
//		adding each word of the line to the array list
//		System.out.println(words);
		arrayofwords.add(words);
		
	}
	
//	for(int i=0;i<arrayofwords.size();i++){
//		System.out.println("from words block"+arrayofwords.get(i));
//	} 
	}

//	public ArrayList<String> getwords(){
//		ArrayList<String> newwords = arrayofwords;
//		return newwords;
//		
//	}
}

public class returningValueFromThread {

	public static void main(String[] args) throws IOException {
		
//		Creating the File and Scanner for the reading the File.
		FileInputStream fileInput = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		Scanner scan = new Scanner(fileInput);
		ArrayList<String> splitedvalues = new ArrayList<String>();
		while(scan.hasNextLine()){
			String ScannedLine = scan.nextLine();
			Newthread12 t = new Newthread12(ScannedLine,splitedvalues);
		}
		for(int i=0;i<splitedvalues.size();i++){
			System.out.println(splitedvalues.get(i));
		}
		System.out.println("Closing the Scanner");
		scan.close();
		System.out.println("Closing the File");
		fileInput.close();
	}

}

