package test;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;
import realAufgabe1.Controller;

@SuppressWarnings("deprecation")
public class InputValidation {

	@Test
	public void test() throws FileNotFoundException {
		Controller c = new Controller("/publishingsList.txt");
		Assert.assertEquals(c.isMalformed("f0rbi$$en"), true);
		Assert.assertEquals(c.isMalformed("Kurt Schneider"), false);
		Assert.assertEquals(c.isMalformed("F Kortum"), false);
		Assert.assertEquals(c.isMalformed("not a n4ame"), true);
	}

}
