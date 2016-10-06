import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CountWords {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		FileInputStream fin = new FileInputStream("/Users/divya/Desktop/osAssignment1.rtf");
		Scanner sc = new Scanner(fin);
		
//		creating the array list for words and its count
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Integer> count= new ArrayList<Integer>();
		
//		Reading through the file
		while(sc.hasNext()){
			String nextword = sc.next();
//			Determine the word is in array list
			if(words.contains(nextword)){
				int index = words.indexOf(nextword);
				count.set(index, count.get(index)+1);
				
			}else{
				words.add(nextword);
				count.add(1);
			}
		}
		
		sc.close();
		fin.close();
		
//		print the results
		for(int i=0;i<words.size();i++){
			System.out.println(words.get(i)+" occurred "+count.get(i));
		}

	}

}
