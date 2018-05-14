package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;
import realAufgabe1.Controller;

// Test for own list of publications to fulfill task
public class ListTest {

	@Test
	public void testPublishingList() throws FileNotFoundException {
		Controller c = new Controller("/publishingListTest.txt");
		Assert.assertEquals(4, c.computeHIndex(c.getListPaperCitationFromName("F Chudigiewitsch")));
		Assert.assertEquals(4, c.computeHIndex(c.getListPaperCitationFromName("F. Chudigiewitsch")));
		Assert.assertEquals(4, c.computeHIndex(c.getListPaperCitationFromName("Florian Chudigiewitsch")));
		Assert.assertEquals(0, c.computeHIndex(c.getListPaperCitationFromName("F Chudigiewitschhh")));
		
		Assert.assertEquals(3, c.computeHIndex(c.getListPaperCitationFromName("Q Le")));
		Assert.assertEquals(3, c.computeHIndex(c.getListPaperCitationFromName("Q. Le")));
		Assert.assertEquals(3, c.computeHIndex(c.getListPaperCitationFromName("Quang Le")));
		Assert.assertEquals(0, c.computeHIndex(c.getListPaperCitationFromName("Quang Li")));
		
		Assert.assertEquals(5, c.computeHIndex(c.getListPaperCitationFromName("J Brune")));
		Assert.assertEquals(5, c.computeHIndex(c.getListPaperCitationFromName("J. Brune")));
		Assert.assertEquals(5, c.computeHIndex(c.getListPaperCitationFromName("Jonas Brune")));
		Assert.assertEquals(0, c.computeHIndex(c.getListPaperCitationFromName("Jonas Bruhne")));
	}

}
