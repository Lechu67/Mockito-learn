package com.mockitoDemo.buisness.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.mockitoDemo.data.api.TodoService;

public class TodoBuisnessImplMockTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_UsingAMock() {
		
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);
		
		
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoServiceMock);
		
		
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, todoList.size());
	}
	@Test
	public void testRetrieveTodosRelatedToSpring_WithEmptyList() {
		
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todo = Arrays.asList();
		when(todoServiceMock.retrieveTodos("Dummy")).thenReturn(todo);
		
		
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoServiceMock);
		
		
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, todoList.size());
	}
	
	@Test
	public void testRetrieveTodosRelatedToSpring_UsingBDD() {
		
		
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoServiceMock);
		//When
		
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		//Then
		assertThat(todoList.size(), is(2));
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_UsingBDD() {

		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoServiceMock);
		//When
		
		todoBuisnessImpl.deleteTodosNotRelatedToSpring("Dummy");
		//Then
		verify(todoServiceMock, times(1)).deleteTodo("Learn to dance");
		verify(todoServiceMock, never()).deleteTodo("Learn Spring");
		then(todoServiceMock).should(never()).deleteTodo("Learn Srping MVC");
	}
	@Test
	public void testDeleteTodosNotRelatedToSpring_UsingBDD_captureArgument() {
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		//Given
		TodoService todoServiceMock = mock(TodoService.class);
		List<String> todo = Arrays.asList("Learn Spring MVC","Learn Spring","Learn to dance");
		given(todoServiceMock.retrieveTodos("Dummy")).willReturn(todo);
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoServiceMock);
		//When
		
		todoBuisnessImpl.deleteTodosNotRelatedToSpring("Dummy");
		//Then

		then(todoServiceMock).should(times(1)).deleteTodo(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue(), is("Learn to dance"));
	}




}
