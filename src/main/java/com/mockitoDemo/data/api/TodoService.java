package com.mockitoDemo.data.api;

import java.util.List;
//Create TodoServiceStub
//Test the TodoBuisness impl using TodoServiceStub
public interface TodoService {

	
	public List<String> retrieveTodos(String user);
	
	public void deleteTodo(String todo);
}
