package callablefunction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
class threading implements Callable{
String scannedline;
private ArrayList<String> listed = new ArrayList<String>();
	public threading(String line) throws Exception {
	scannedline = line;	
	}

	@Override
	public ArrayList<String> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}


public class SimpleCallableClass {

	public static void main(String[] args) throws Exception {
		FileInputStream fileInput = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		Scanner scan = new Scanner(fileInput);
		ExecutorService ex = Executors.newCachedThreadPool();
		Future<ArrayList> future = ex.submit(new Callable<ArrayList>(){

			@Override
			public ArrayList call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
			
			
		});
		ex.shutdown();
		future.get();
		while(scan.hasNextLine()){
			String line = scan.nextLine();
			new threading(line);
		}
	
	}

}
