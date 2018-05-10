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
	
	private static List<PaperCitation> list = new LinkedList<PaperCitation>();

	public Controller() throws FileNotFoundException {
		HIndex.initLinkedList(list);
	}
	
	@FXML
	private void initialize() {
	}
	
	@FXML
	protected void onEnterPressed() {

		String name = input.getText();

		// Test for forbidden characters
		if(!name.replaceAll("[a-zA-Z. ]","").equals("")) {
			output.setText("Error: " + name + " contains forbidden characters!");
			return;
		}

		int result = computeHIndex(getListPaperCitationFromName(name));

		output.setText(
				"" + 
				(found
				? "HIndex: " + result + " | Publication Count: " + publicationCount
				: "Error: Author not found!")
		);
	}
	
	/**
	 * Get all papers and their citations of the given author.
	 * @param name name of the author
	 * @return a list of papers
	 */
	private List<PaperCitation> getListPaperCitationFromName(String name) {
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
	
	/**
	 * compute the h-index from the list of papers
	 * @param citations list of citations
	 * @return h-index
	 */
	private int computeHIndex(List<PaperCitation> citations) {
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
