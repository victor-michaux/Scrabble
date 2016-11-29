import java.util.Comparator;

public class ScrabbleComparator implements Comparator<String> {
	private char[] letters;
	
	public ScrabbleComparator(char[] letters) {
		this.letters = letters;
	}
	
	public int letterValue(char letter) {
		if(letter == '*') return 0;
		if(letter == 'e' || letter == 'a' || letter == 'i' || letter == 'n' || letter == 'o' || letter == 'r' || letter == 's' || letter == 't' || letter == 'u' || letter == 'l') return 1;
		if(letter == 'd' || letter == 'm' || letter == 'g') return 2;
		if(letter == 'b' || letter == 'c' || letter == 'p') return 3;
		if(letter == 'f' || letter == 'h' || letter == 'v') return 4;
		if(letter == 'j' || letter == 'q') return 8;
		if(letter == 'k' || letter == 'w' || letter == 'x' || letter == 'y' || letter == 'z') return 10;
		return 0;
	}
	public int lettersValue(char[] letters) {
		int total = 0;
		for(Character c : letters) {
			total += letterValue(c);
		}
		return total;
	}
	public int wordValue(String word) {
		char[] composition = Dictionary.getComposition(word, letters);
		if(composition == null) {
			return 0;
		} else {
			return lettersValue(composition);
		}
	}

	@Override
	public int compare(String s1, String s2) {
		if (wordValue(s1)>wordValue(s2))
			return -1;
		else if (wordValue(s1)==wordValue(s2))
			return 0;
		else
			return 1;
	}
}
