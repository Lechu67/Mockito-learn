package com.mockitoDemo;

import static org.hamcrest.Matchers.anything;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpyTest {

	@Test
	public void test() {
		
		List arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Michal");
		assertEquals(1, arrayListSpy.size());
		when(arrayListSpy.size()).thenReturn(5);
		assertEquals(5, arrayListSpy.size());		
		verify(arrayListSpy).add("Michal");
		verify(arrayListSpy, never()).remove(anything());
	}

}
