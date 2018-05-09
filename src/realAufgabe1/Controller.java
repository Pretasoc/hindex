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
	
	private static List<PaperCitation> list = new LinkedList<PaperCitation>();

	public Controller() throws FileNotFoundException {
		HIndex.initLinkedList(list);
	}
	
	@FXML
	private void initialize() {
		System.out.println("Initialized SayHelloController");
	}
	
	@FXML
	protected void onEnterPressed() {
		// TODO: No number and special symbol
		String name = input.getText();
		output.setText("" + computeHIndex(getListPaperCitationFromName(name)));
	}
	
	/**
	 * Get all papers and their citations of the given author.
	 * @param name name of the author
	 * @return a list of papers
	 */
	private List<PaperCitation> getListPaperCitationFromName(String name) {
		List<PaperCitation> l = new LinkedList<PaperCitation>();
		// TODO: namen
		for(int i = 0; i < list.size(); i++) {
	        if(list.get(i).getAuthor().equals("K Schneider") || 
	        		list.get(i).getAuthor().equals("K. Schneider") ||
	        		list.get(i).getAuthor().equals("Kurt Schneider")) {
	        	l.add(list.get(i));
	        }
	        	
	    }
		return l;
	}
	
	/**
	 * compute the h-index from the list of papers
	 * @param citations list of citations
	 * @return h-index
	 */
	private int computeHIndex(List<PaperCitation> citations) {
	    Collections.sort(citations, new Comparator<PaperCitation>() {
	    	@Override
	    	public int compare(PaperCitation pc1, PaperCitation pc2) {
	    		int c1 = pc1.getCitations();
	    		int c2 = pc2.getCitations();
	    		return c1 < c2 ? -1 : c1 == c2 ? 0 : 1;
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
