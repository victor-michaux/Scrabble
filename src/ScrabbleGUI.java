import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ScrabbleGUI extends JFrame{
	private Dictionary dico;
	private JTextField letterTextField;
	private JButton searchButton;
	private JTextArea wordListTextArea;
	
	public ScrabbleGUI() {
		super("Scrabble");
		this.setMinimumSize(new Dimension(640, 480));
		
		try {
			dico = new Dictionary("fr_FR_utf8.dico");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		
		letterTextField = new JTextField();
		searchButton = new JButton("Search");
		wordListTextArea = new JTextArea();
		
		JScrollPane scroll = new JScrollPane(wordListTextArea);
		JPanel panelNorth = new JPanel();
		JPanel panelCenter = new JPanel();
		
		panelCenter.setBorder(BorderFactory.createTitledBorder("Matching words"));
		panelNorth.setBorder(BorderFactory.createTitledBorder("Yours letters"));
		
		panelNorth.setLayout(new GridLayout(1, 2));		
		panelCenter.setLayout(new BorderLayout());
		
		this.searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wordListTextArea.setText("");
				String textEntered = letterTextField.getText();
				char lettres[] = textEntered.toCharArray();
				ScrabbleComparator sc = new ScrabbleComparator(lettres);
				String[] tabMots = dico.getWordsThatCanBeComposed(lettres);
				String textToDisplay = "";
				wordListTextArea.append(tabMots.length + " matching words found : \n");
				Arrays.sort(tabMots, sc);
				for (int i=0; i<tabMots.length; i++){
					char[] composition = dico.getComposition(tabMots[i], lettres);
					textToDisplay = "" + tabMots[i] + " ([" ;
					if(composition != null){
						for(int j=0; j<composition.length; j++){
							if(j==composition.length-1){
								textToDisplay += composition[j];
							}
							else
								textToDisplay += composition[j] + ", ";
						}
					}
					textToDisplay += "]) - " + sc.wordValue(tabMots[i]) + " point(s) \n";
					wordListTextArea.append(textToDisplay);
				}
			}
		});
		panelNorth.add(letterTextField);
		panelNorth.add(searchButton);
		panelCenter.add(scroll, BorderLayout.CENTER);
		
		this.setLayout(new BorderLayout());
		this.add(panelNorth, BorderLayout.NORTH);
		this.add(panelCenter, BorderLayout.CENTER);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args){
		new ScrabbleGUI();
	}
}
