package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;
import realAufgabe1.Controller;

public class InputValidation {

	@SuppressWarnings("deprecation")
	@Test
	public void test() throws FileNotFoundException {
		Controller c = new Controller();
		Assert.assertEquals(c.validateInput("f0rbi$$en"), true);
		Assert.assertEquals(c.validateInput("Kurt Schneider"), false);
		Assert.assertEquals(c.validateInput("F Kortum"), false);
		Assert.assertEquals(c.validateInput("not a n4ame"), true);
	}

}
