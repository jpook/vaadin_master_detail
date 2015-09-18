package com.kws.simple.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kws.simple.cross.helper.MethodHelper;

public class MethodHelperTest {

	MethodHelper mh;

	@Before
	public void setUp() throws Exception {
		mh = new MethodHelper();
	}

	@Test
	public void testGetPropertyName1() {
		assertEquals(mh.getPropertyName(null), null);
	}

	@Test
	public void testGetPropertyName2() {
		assertEquals(mh.getPropertyName(""), null);
	}

	@Test
	public void testGetPropertyName3() {
		assertEquals(mh.getPropertyName("set"), null);
	}

	@Test
	public void testGetPropertyName4() {
		assertEquals(mh.getPropertyName("setG"), "g");
	}

	@Test
	public void testGetPropertyName5() {
		assertEquals(mh.getPropertyName("setsdz"), "sdz");
	}

}
