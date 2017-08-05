package com.mockitoDemo.buisness.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;

import com.mockitoDemo.data.api.TodoService;

//@RunWith(MockitoJUnitRunner.class)
public class TodoBuisnessImplMockitoInjectionMockTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Mock
	TodoService todoServiceMock;
	
	@InjectMocks
	TodoBuisnessImpl todoBuisnessImpl;
	
	@Captor
	ArgumentCaptor<String> argumentCaptor;
	
	
	@Test
	public void testRetrieveTodosRelatedToSpring_UsingAMock() {
		
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, todoList.size());
	}
	@Test
	public void testRetrieveTodosRelatedToSpring_WithEmptyList() {

		List<String> todo = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, todoList.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_UsingBDD() {
			
		//Given
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		//Then
		assertThat(todoList.size(), is(2));
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_UsingBDD() {

		//Given
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		todoBuisnessImpl.deleteTodosNotRelatedToSpring("Dummy");
		//Then
		verify(todoServiceMock, times(1)).deleteTodo("Learn to dance");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodo("Learn Srping MVC");
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_UsingBDD_captureArgument() {
		
		//Given
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		todoBuisnessImpl.deleteTodosNotRelatedToSpring("Dummy");
		//Then
		then(todoServiceMock).should(atLeast(1)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn to dance"));
	}
}
