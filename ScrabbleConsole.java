import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ScrabbleConsole {
	
	public ScrabbleConsole() {
		System.out.println("Welcome to the Scrable assistant !");
		Dictionary dico = null;
		try {
			dico = new Dictionary("fr_FR_utf8.dico");
			System.out.println(dico.getWordsList().size() + " words loaded. From " + dico.getWordsList().get(0) + " to " + dico.getWordsList().get(dico.getWordsList().size()-1));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		System.out.println("Please enter a word :");
		Scanner scan = new Scanner(System.in);
		/*String word = scan.nextLine();
		System.out.println("Please enter a letter list :");
		char letters[] = scan.nextLine().toCharArray();
		if(dico.mayBeComposed(word, letters)) {
			System.out.println(word + " may be composed with letters : " + Arrays.toString(letters));
		} else {
			System.out.println(word + " may NOT be composed with letters : " + Arrays.toString(letters));
		}*/
		System.out.println("Please enter a letter list:");
		char lettres[] = scan.nextLine().toCharArray();
		ArrayList <String> tabMots = dico.getWordsThatCanBeComposed(lettres);
		System.out.print(tabMots.size() + " matching words found : ");
		System.out.println(Arrays.toString(tabMots.toArray()));
	}
	
	public static void main(String[] args) {
		ScrabbleConsole console = new ScrabbleConsole();
	}
}
