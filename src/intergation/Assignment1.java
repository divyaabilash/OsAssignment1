package intergation;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

		/*creating my own set of data type which contains the array list of string and 
		array list of integer
		its used to return the list of words and its count.*/
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

		//class which has implemented callable function.
		class findwords implements Callable<MyResult>{
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		
			String line;
		//	constructor 
			public findwords(String line) {
				this.line = line;
			}

		//call method with the return type as MyResult
			public MyResult call() throws Exception {
		//		scanner for the line which is passed from the main thread
				Scanner f = new Scanner(line);
				
		// word counter function.
				while(f.hasNext()){
					String nextword = f.next().toLowerCase();
		/*			if the word is already existing in the array list then get the index 
					and increase the count of the word in the second array list.
					its done by getting the index of the word in the words list, and for the same 
					index get the value of count and increase it.*/
					
					if(words.contains(nextword)){
						int index = words.indexOf(nextword);
						count.set(index, count.get(index)+1);
					}
					
		//			If the word doesn't exist, then push the new word to the array list and set the count
		//			count to 1 by adding to count array list.
					else{
						words.add(nextword);
						count.add(1);
					}
				}
		//		closing the scanner
				f.close();
		//		returning the array list which contains the words and its corresponding count.
				return new MyResult(words,count);
			}
			
		}


		public class Assignment1 {
		
			public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
		
				//	file reader 
				FileInputStream fileInput = new FileInputStream("/Users/divya/Desktop/osAssignment1.txt");
		
				// scanner for reading the file 
				Scanner scan = new Scanner(fileInput);
				
				//	creating local variables 
				ArrayList<String> words1 = new ArrayList<String>();
				ArrayList<String> finallistwords = new ArrayList<String>();
				ArrayList<Integer> count1 = new ArrayList<Integer>();
				ArrayList<Integer> finalcount = new ArrayList<Integer>();
		
				//scanning for each line of the file
				while(scan.hasNextLine()){
					String line = scan.nextLine();
		
					//creating the callable interface for each and every line of the file.
					ExecutorService ex = Executors.newCachedThreadPool();
	
					//calling the class findwords
					Future<MyResult> future = ex.submit(new findwords(line));
					
		
				   //getting the return value form the get method of the findwords class 
					MyResult res = future.get();
					
					for(int i=0;i<res.getFirst().size();i++){
						//creating two array list for storing the values returned from each thread.
						words1.add(res.getFirst().get(i));
						count1.add(res.getSecond().get(i));
					}
					
					ex.shutdown();	
				}
/*
 * Condition to check whether the returns size of word array list is zero. If its zero, then its an empty file 
 * else it has content to count.
 */
				if(words1.size() ==0){
					System.out.println("Its an Empty file");
				}else{
				System.out.println("The thread outputs are:");
				
				for(int i=0;i<words1.size();i++){
					//Printing values after the threads returned the value
					
					System.out.println(words1.get(i)+" = "+count1.get(i));		
					String w = words1.get(i);
					int c=count1.get(i);
					
					//creating the consolidating list from the list thats collected the output from threads.
					/* 
					 * if the final list is having the words then the count is increased.
					 * if not then the words is added to the final list array.
					 */
					if(finallistwords.contains(w)){
						int index = finallistwords.indexOf(w);
						finalcount.set(index,finalcount.get(index)+c);
					}else{
						finallistwords.add(w);
						finalcount.add(c);
					}
				}
				}
				for(int i=0;i<100;i++){
					System.out.print("-");
				}
				System.out.println();

				/*
				 * Condition to check whether the returns size of final list words array list is zero. If its zero, then its an empty file 
				 * else it has content to count.
				 */
				if(finallistwords.size() == 0){
					System.out.println("Its an Empty file");
				}else{
					System.out.println("The output of concatenated group:");
					System.out.println();
					//printing out the values by looping the values
					for(int i=0;i<finallistwords.size();i++){
					System.out.println(finallistwords.get(i)+" = "+finalcount.get(i)+" times ");
				}
				}
				
				
				for(int i=0;i<100;i++){
					System.out.print("-");
				}
				System.out.println();
				System.out.println("Closing the Scanner");
				scan.close();
				System.out.println("Closing the File");
				fileInput.close();
			}
		
		}
