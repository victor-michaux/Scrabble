import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dictionary {
	private ArrayList<String> wordsList;
	
	public Dictionary() {
		this.wordsList = new ArrayList<String>();
		String[] tabMots = {"abricot", "châtaigne", "groseille", "pomme", "tomate"};
		for (String mot : tabMots) {
			this.wordsList.add(mot);
		}
	}
	
	public Dictionary(String fileName) throws FileNotFoundException {
		Scanner scan = new Scanner(new File(fileName));
		this.wordsList = new ArrayList<String>();
		// On enregistre pas le nombre de mots
		scan.next();
		while(scan.hasNext()) {
			this.wordsList.add(scan.next());
		}
	}
	
	public ArrayList<String> getWordsList() {
		return this.wordsList;
	}
	
	public boolean isValidWord(String word) {
		return this.wordsList.contains(word);
	}
	
	public boolean mayBeComposed(String word, char[] letters) {
		word = replaceFrenchCharacter(word);
		System.out.println(word);
		boolean okWord = true;
		int iWord = 0;
		char[] tabChar = word.toCharArray();
		
		ArrayList<Character> tabLetters = new ArrayList<Character>();
		for(int i = 0; i < letters.length; i++) {
			tabLetters.add(letters[i]);
		}
		
		do {
			boolean okLetter = false;
			int iLetters = 0;
			int size = tabLetters.size();
			do {
				if(tabChar[iWord] == tabLetters.get(iLetters)) {
					okLetter = true;
					tabLetters.remove(iLetters);
				}
				iLetters++;
				size = tabLetters.size();
			} while(!okLetter && iLetters < size);
			if(!okLetter) okWord = false;
			iWord++;
		} while(okWord && iWord < word.length());
		
		return okWord;
	}
	
	public static String replaceFrenchCharacter(String s) {
		s = s.replace('à', 'a');
		s = s.replace('â', 'a');
		s = s.replace('ä', 'a');
		s = s.replace('ç', 'c');
		s = s.replace('é', 'e');
		s = s.replace('è', 'e');
		s = s.replace('ê', 'e');
		s = s.replace('ë', 'e');
		s = s.replace('î', 'i');
		s = s.replace('ï', 'i');
		s = s.replace('ô', 'o');
		s = s.replace('ö', 'o');
		s = s.replace('ù', 'u');
		s = s.replace('û', 'u');
		s = s.replace("\u0153", "oe");
		s = s.replace("\u00E6", "ae");
		return s;
	}
}
