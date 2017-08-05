package com.mockitoDemo.buisness.impl;

import java.util.ArrayList;
import java.util.List;

import com.mockitoDemo.data.api.TodoService;
//ToDoBuisnessImpl - SUT - System Under Test
//ToDoService dependencie
public class TodoBuisnessImpl {

	private TodoService todoService;
	
	public TodoBuisnessImpl (TodoService todoService){
		this.todoService = todoService;
	}
	
	public List<String> retrieveTodosRelatedToSpring(String user){
		List<String> filterTodos = new ArrayList<String>();
		List<String> todos = todoService.retrieveTodos(user);
		
		for (String todo: todos){
			if(todo.contains("Spring")){
				filterTodos.add(todo);
			}
		}
		return filterTodos;
	}
	public void deleteTodosNotRelatedToSpring(String user){

		List<String> todos = todoService.retrieveTodos(user);
		
		for (String todo: todos){
			if(!todo.contains("Spring")){
				todoService.deleteTodo(todo);
			}
		}
		
	}
}
