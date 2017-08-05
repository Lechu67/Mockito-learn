package com.mockitoDemo.buisness.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


import java.util.List;

import org.junit.Test;

public class ListTest {

	@Test
	public void MockSizeMethod() {
		List<Object> myList = mock(List.class);
		when(myList.size()).thenReturn(2);
		assertEquals(2, myList.size());
	}
	@Test
	public void MockSizeMethod_returnMultipleValues() {
		List<Object> myList = mock(List.class);
		when(myList.size()).thenReturn(2).thenReturn(3);
		assertEquals(2, myList.size());
		assertEquals(3, myList.size());
	}
	@Test
	public void MockSizeMethod_getMethod() {
		List<Object> myList = mock(List.class);
		when(myList.get(anyInt())).thenReturn("HELLO");
		assertEquals("HELLO", myList.get(0));
		assertEquals("HELLO", myList.get(1));
	}
	@Test(expected=RuntimeException.class)
	public void MockSizeMethod_exception() {
		List<Object> myList = mock(List.class);
		when(myList.get(anyInt())).thenThrow(new RuntimeException("Smth"));
		myList.get(0);
	}
	@Test(expected=RuntimeException.class)
	public void MockSizeMethod_mixingMatchers() {
		List<Object> myList = mock(List.class);
		when(myList.subList(anyInt(),5)).thenThrow(new RuntimeException("Smth"));
		myList.get(0);
	}
	@Test
	public void MockSizeMethod_getMethodUsingBDD() {
		
		//given 
		List<String> myList = mock(List.class);
		given(myList.get(anyInt())).willReturn("HELLO");
		//when
		String firstElement = myList.get(0);
		//then
		assertThat(firstElement, is("HELLO"));
		
	}
}
