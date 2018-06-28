package realAufgabe1;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {
	
	@FXML
	protected TextField input;
	@FXML
	protected Text output;
	@FXML
	private ResourceBundle resources;
	
	private boolean found;
	
	private int publicationCount;
	
	private List<PaperCitation> list = new LinkedList<PaperCitation>();

	public Controller(String path) throws FileNotFoundException {
		HIndex.initLinkedList(list, path);	
	}
	
	@FXML
	private void initialize() {}
	
	/**
	 * Checks whether the input has forbidden characters in it.
	 * @param name
	 * @return true if forbidden characters present, else false
	 */
	public boolean isMalformed(String name) {
		if (name.length() == 0) return true;
		if (name.startsWith(" ")) return true;
		return name.codePoints().any(c => {
			char character = (char c);
			return !(Character.isLetter(character) || character == ' ' || character == '.')
		});
	}
	
	/**
	 * Handles input.
	 * A Button is not needed when listening on enter.
	 * This keeps the design simpler and makes it less error prone.
	 * This makes [R08] obsolete.
	 */
	@FXML
	protected void onEnterPressed() {

		String name = input.getText();

		if(isMalformed(name)) {
			output.setText("Error: " + name + " contains forbidden characters!");
			return;
		}

		// to define/use other metrics write a corresponding method and call it instead of computeHIndex()
		int result = computeHIndex(getListPaperCitationFromName(name));

		output.setText(
				"" + 
				(found
				? "HIndex: " + result + " | Publication Count: " + publicationCount
				: "Error: Author not found!")
		);
	}
	
	/**
	 * Get all PaperCitation and their citations of the given author.
	 * @param name name of the author
	 * @return a list of PaperCitation
	 */
	public List<PaperCitation> getListPaperCitationFromName(String name) {
		List<PaperCitation> matchedNames = new LinkedList<PaperCitation>();
		
		name = name.toLowerCase();
		String[] searchNames = name.split(" ");
		String searchLastName = searchNames[searchNames.length - 1];
		
		for(PaperCitation iterator : list) {
			
			// Match for last name and first character of first name
			String[] names = iterator.getAuthor().toLowerCase().split(" ");
			String lastName = names[names.length - 1];
	        if(searchLastName.equals(lastName) &&
	        		names[0].startsWith("" + searchNames[0].charAt(0))) {
	        	matchedNames.add(iterator);
	        }
	        	
	    }
		
		// If list is empty, the author was not found
		found = !(matchedNames.size() == 0);
		publicationCount = matchedNames.size();
		
		return matchedNames;
	}
	
	// To define/use another metric than hIndex simply write a method similar to this correspondingly.
	/**
	 * compute the h-index from the list of papers
	 * @param citations list of citations
	 * @return h-index
	 */
	public int computeHIndex(List<PaperCitation> citations) {
	    Collections.sort(citations, new Comparator<PaperCitation>() {
	    	@Override
	    	public int compare(PaperCitation paperCitation1, PaperCitation paperCitation2) {
	    		int citationCount1 = paperCitation1.getCitations();
	    		int citationCount2 = paperCitation2.getCitations();
	    		return citationCount1 < citationCount2 ? -1 : citationCount1 == citationCount2 ? 0 : 1;
	    	}
	    });
	 
	    int result = 0;    
	    for(int i = 0; i < citations.size(); i++){
	        int smaller = Math.min(citations.get(i).getCitations(), citations.size() - i);
	        result = Math.max(result, smaller);
	    }
	 
	    return result;
	}
}
