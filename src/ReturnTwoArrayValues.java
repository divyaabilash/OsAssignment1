import java.util.ArrayList;

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

public class ReturnTwoArrayValues {

	public static void main(String[] args){
		MyResult result = f1("A");
		for(int i=0;i<result.getFirst().size();i++){
			System.out.println(result.getFirst().get(i));
			System.out.println(result.getSecond().get(i));
		}
	}

 public static MyResult f1(String a){
	 ArrayList<String> words = new ArrayList<String>();
	 ArrayList<Integer> count = new ArrayList<Integer>();
	  words.add("hello");
	  words.add("c");
	  count.add(1);
	  count.add(1);
	  
	  
	return new MyResult(words,count);
	 
 }
}
