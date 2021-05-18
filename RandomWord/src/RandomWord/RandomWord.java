package RandomWord;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

	public static void main(String[] args) {
		String champion = "";
		int wordCount = 0;
		
		while (!StdIn.isEmpty()) {
			String input = StdIn.readString();
			wordCount++;
			
			if (StdRandom.bernoulli(1/wordCount)) {
				champion = input;
			}
		}
		
		System.out.println(champion);
	}
}
