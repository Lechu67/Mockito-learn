package com.mockitoDemo.buisness.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.mockitoDemo.data.api.TodoService;
import com.mockitoDemo.data.api.TodoServiceStub;

public class TodoBuisnessImplStubTest {

	@Test
	public void testRetrieveTodosRelatedToSpring_UsingAStub() {
		TodoService todoService = new TodoServiceStub();
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoService);
		
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(2, todoList.size());
	}
	@Test
	public void testRetrieveTodosRelatedToSpring_UsingAStub2() {
		TodoService todoService = new TodoServiceStub();
		TodoBuisnessImpl todoBuisnessImpl = new TodoBuisnessImpl(todoService);
		
		List<String>todoList = todoBuisnessImpl.retrieveTodosRelatedToSpring("Dummy");
		assertEquals(0, todoList.size());
	}


}
