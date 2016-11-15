import java.io.FileNotFoundException;
import java.util.Arrays;

public class ScrabbleConsole {
	
	public ScrabbleConsole() {
		System.out.println("Welcome to the Scrable assistant !");
		Dictionary dico = null;
		try {
			dico = new Dictionary("fr_FR_utf8.dico");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Arrays.toString(dico.getWordsList().toArray()));
	}
	
	public static void main(String[] args) {
		ScrabbleConsole console = new ScrabbleConsole();
	}
}
