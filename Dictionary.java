import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Dictionary {
	private ArrayList<String> wordsList;
	
	public Dictionary() {
		this.wordsList = new ArrayList<String>();
		String[] tabMots = {"abricot", "ch�taigne", "groseille", "pomme", "tomate"};
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
		boolean okWord = true;
		int iWord = 0;
		char[] tabChar = word.toCharArray();
		
		ArrayList<Character> tabLetters = new ArrayList<Character>();
		
		for(int i = 0; i < letters.length; i++) {
			tabLetters.add(letters[i]);
		}
		
		ArrayList<Character> tabTemp = tabLetters;
		
		int nbJoker = 0;
		
		for(int i=0 ; i < tabLetters.size(); i++) {
			if(tabLetters.get(i) == '*') {
				nbJoker++;
				tabTemp.remove(tabLetters.get(i));
			}
		}
		tabLetters = tabTemp;
		do {
			boolean okLetter = false;
			int iLetters = 0;
			int size = tabLetters.size();
			System.out.println(size);
			while(!okLetter && iLetters < size) {
				if(tabChar[iWord] == tabLetters.get(iLetters)) {
					okLetter = true;
					tabLetters.remove(iLetters);
				}
				iLetters++;
				size = tabLetters.size();
			}
			if(!okLetter && nbJoker > 0){
				okWord = true;
				nbJoker--;
			}
			else if (!okLetter){
				okWord = false;
			}
			iWord++;
		} while(okWord && iWord < word.length());
		
		return okWord;
	}
	
	public static String replaceFrenchCharacter(String s) {
		s = s.replace('�', 'a');
		s = s.replace('�', 'a');
		s = s.replace('�', 'a');
		s = s.replace('�', 'c');
		s = s.replace('�', 'e');
		s = s.replace('�', 'e');
		s = s.replace('�', 'e');
		s = s.replace('�', 'e');
		s = s.replace('�', 'i');
		s = s.replace('�', 'i');
		s = s.replace('�', 'o');
		s = s.replace('�', 'o');
		s = s.replace('�', 'u');
		s = s.replace('�', 'u');
		s = s.replace("\u0153", "oe");
		s = s.replace("\u00E6", "ae");
		return s;
	}
	
	public ArrayList <String> getWordsThatCanBeComposed(char[] letters){
		ArrayList <String> tabMots = new ArrayList<String>();
		for (String mot : wordsList){
			if(mot.length() <= letters.length) {
				if (mayBeComposed(mot, letters)){
					tabMots.add(mot);
				}
			}
			
		}
		return tabMots;
	}
}
