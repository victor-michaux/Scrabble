import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ScrabbleConsole {
	
	public ScrabbleConsole() {
		System.out.println("Welcome to the Scrable assistant !");
		Dictionary dico = null;
		Scanner scan = new Scanner(System.in);
		try {
			dico = new Dictionary("fr_FR_utf8.dico");
			System.out.println(dico.getWordsList().size() + " words loaded. From " + dico.getWordsList().get(0) + " to " + dico.getWordsList().get(dico.getWordsList().size()-1));
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			System.exit(-1);
		}
		
		System.out.println("Please enter a letter list:");
		
		char lettres[] = scan.nextLine().toCharArray();
		ScrabbleComparator sc = new ScrabbleComparator(lettres);
		String[] tabMots = dico.getWordsThatCanBeComposed(lettres);
		
		Arrays.sort(tabMots, sc);
		
		System.out.print(tabMots.length + " matching words found : ");
		
		for (int i=0; i<tabMots.length; i++){
			char[] composition = dico.getComposition(tabMots[i], lettres);
			System.out.print(tabMots[i] + " (");
			if(composition != null){
				for(int j=0; j<composition.length; j++){
					System.out.print(composition[j] + " ");
				}
			}
			System.out.println(") : " + sc.wordValue(tabMots[i]) + " points");
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		new ScrabbleConsole();
	}
}
