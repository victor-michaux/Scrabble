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
		scan.close();
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
	
	public String[] getWordsThatCanBeComposed(char[] letters){
		ArrayList<String> tabMot = new ArrayList<String>();
		for (String mot : wordsList){
			if(mot.length() <= letters.length) {
				if (mayBeComposed(mot, letters)){
					tabMot.add(mot);
				}
			}
			
		}
		String[] tabMots = new String[tabMot.size()];
		for (int i =0; i<tabMot.size(); i++){
			tabMots[i] = tabMot.get(i);
		}
		return tabMots;
	}
	
	public static char[] getComposition(String word, char[] letters) {
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
		char[] composition = new char[word.length()];
		do {
			boolean okLetter = false;
			int iLetters = 0;
			int size = tabLetters.size();
			while(!okLetter && iLetters < size) {
				if(tabChar[iWord] == tabLetters.get(iLetters)) {
					okLetter = true;
					composition[iWord] = tabLetters.get(iLetters);
					tabLetters.remove(iLetters);
				}
				iLetters++;
				size = tabLetters.size();
			}
			if(!okLetter && nbJoker > 0){
				okWord = true;
				composition[iWord] = '*';
				nbJoker--;
			}
			else if (!okLetter){
				okWord = false;
			}
			iWord++;
		} while(okWord && iWord < word.length());
		if(!okWord) return null;
		return composition;
	}
}
