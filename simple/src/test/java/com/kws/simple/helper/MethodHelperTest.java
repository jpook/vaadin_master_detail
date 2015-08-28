package com.kws.simple.helper;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MethodHelperTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetPropertyName1() {
		assertEquals(MethodHelper.getPropertyName(null),null);
	}
	
	@Test
	public void testGetPropertyName2() {
		assertEquals(MethodHelper.getPropertyName(""),null);
	}
	
	@Test
	public void testGetPropertyName3() {
		assertEquals(MethodHelper.getPropertyName("set"),null);
	}
	
	@Test
	public void testGetPropertyName4() {
		assertEquals(MethodHelper.getPropertyName("setG"),"g");
	}
	
	@Test
	public void testGetPropertyName5() {
		assertEquals(MethodHelper.getPropertyName("setsdz"),"sdz");
	}
	
}
