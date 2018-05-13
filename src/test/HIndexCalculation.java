package test;


import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import realAufgabe1.Controller;
import realAufgabe1.PaperCitation;

class HIndexCalculation {

	@Test
	//Returns list of all matched Persons
	void testInputHit() throws FileNotFoundException {
		List<PaperCitation> goal = new LinkedList<>();
		goal.add(new PaperCitation("F. Kortum", "The SE-Supreme - Unpublished Studies Part 1", 3));
		goal.add(new PaperCitation("F. Kortum", "The SE-Supreme - Unpublished Studies Part 2", 4));
		goal.add(new PaperCitation("Fabian Kortum", "The SE-Supreme - Unpublished Studies Part 3", 3));
		goal.add(new PaperCitation("F. Kortum", "The SE-Supreme - Unpublished Studies Part 4", 4));
		goal.add(new PaperCitation("F. Kortum", "The SE-Supreme - Unpublished Studies Part 5", 2));
		goal.add(new PaperCitation("Fabian Kortum", "The SE-Supreme - Unpublished Studies Part 6", 4));
		goal.add(new PaperCitation("F. Kortum", "The SE-Supreme - Unpublished Studies Part 7", 2));
		
		List toTest = (new Controller()).getListPaperCitationFromName("Fabian Kortum");
		
		Assert.assertEquals(goal.size(), toTest.size());
		
		for(int i = 0; i < goal.size(); i++) {
			Assert.assertEquals(goal.get(i), toTest.get(i));
		}
		
	}
	
	@Test
	//Wrong input returns empty list
	void testInputMiss() throws FileNotFoundException {
		Assert.assertEquals(new LinkedList(), (new Controller()).getListPaperCitationFromName("Falsche Eingabe"));
	}

}
