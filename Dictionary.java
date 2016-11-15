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
		while(scan.hasNext()) {
			System.out.println(scan.next());
		}
	}
	
	public ArrayList<String> getWordsList() {
		return this.wordsList;
	}
	
	public boolean isValidWord(String word) {
		return this.wordsList.contains(word);
	}
}
