package callablefunction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

final class MyResult {
    private final ArrayList<String> first;
    private final ArrayList<Integer> second;

    public MyResult(ArrayList<String> words, ArrayList<Integer> count) {
        this.first = words;
        this.second = count;
    }

    public ArrayList<String> getFirst() {
        return first;
    }

    public ArrayList<Integer> getSecond() {
        return second;
    }
}


class findwords implements Callable<MyResult>{
ArrayList<String> words = new ArrayList<String>();
ArrayList<Integer> count = new ArrayList<Integer>();

	String line;
	public findwords(String line) {
		this.line = line;
	}

	@Override
	public MyResult call() throws Exception {
		Scanner f = new Scanner(line);
		while(f.hasNext()){
			String nextword = f.next();
			if(words.contains(nextword)){
				int index = words.indexOf(nextword);
				count.set(index, count.get(index)+1);
			}else{
				words.add(nextword);
				count.add(1);
			}
		}
		f.close();
		return new MyResult(words,count);
	}
	
}


public class TryingSimpleCallablefunction {

	public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		FileInputStream fileInput = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		Scanner scan = new Scanner(fileInput);
		ArrayList<String> words1 = new ArrayList<String>();
		ArrayList<String> finallistwords = new ArrayList<String>();
		ArrayList<Integer> count1 = new ArrayList<Integer>();
		ArrayList<Integer> finalcount = new ArrayList<Integer>();

		while(scan.hasNextLine()){
			String line = scan.nextLine();
			ExecutorService ex = Executors.newCachedThreadPool();
			Future<MyResult> future = ex.submit(new findwords(line));
			MyResult res = future.get();
			for(int i=0;i<res.getFirst().size();i++){
				words1.add(res.getFirst().get(i));
				count1.add(res.getSecond().get(i));
			}
			
			
		}
		for(int i=0;i<words1.size();i++){
			System.out.println(words1.get(i)+" its count is "+count1.get(i));		
	
		if(finallistwords.contains(words1.get(i))){
			int index = finallistwords.indexOf(words1.get(i));
			finalcount.set(index, finalcount.get(index)+1);
		}else{
			finallistwords.add(words1.get(i));
			finalcount.add(1);
		}
		}
		
		for(int i=0;i<finallistwords.size();i++){
			System.out.println(finallistwords.get(i)+" occured "+finalcount.get(i));
		}
		
		System.out.println("Closing the Scanner");
		scan.close();
		System.out.println("Closing the File");
		fileInput.close();
	}

}
