package realAufgabe1;

import java.util.List;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {

	static List<PaperCitation> list = new LinkedList<PaperCitation>();

	public static void main(String[] args) throws FileNotFoundException {
		
		
		HIndex.initLinkedList(list);
		List<PaperCitation> ksch = new LinkedList<PaperCitation>();
		for(int i = 0; i < list.size(); i++){
	        if(list.get(i).getAuthor().equals("F Kortum") ||
	        		list.get(i).getAuthor().equals("F. Kortum") ||
	        		list.get(i).getAuthor().equals("Fabian Kortum")) {
	        	ksch.add(list.get(i));
	        }
	        	
	    }
		System.out.println(computeHIndex(ksch));

	}
	
	public static int computeHIndex(List<PaperCitation> citations) {
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
