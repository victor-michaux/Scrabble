import java.io.FileNotFoundException;
import java.util.Arrays;

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
		//System.out.println(Arrays.toString(dico.getWordsList().toArray()));
	}
	
	public static void main(String[] args) {
		ScrabbleConsole console = new ScrabbleConsole();
	}
}
