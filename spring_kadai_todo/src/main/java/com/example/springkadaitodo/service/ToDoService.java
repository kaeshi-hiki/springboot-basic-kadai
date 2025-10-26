package com.example.springkadaitodo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.springkadaitodo.entity.ToDo;
import com.example.springkadaitodo.repository.ToDoRepository;

@Service
public class ToDoService {
	
	private final ToDoRepository toDoRepository;
	public ToDoService(ToDoRepository toDoRepository) {
		this.toDoRepository = toDoRepository;
	}
	
	public void createToDo(String title, String priority, String status) {
		if(title == null || title.isEmpty()) {
			throw new IllegalArgumentException("タスク名を入力して下さい。");
		}
		if(!toDoRepository.findByTitle(title).isEmpty()) {
			throw new IllegalArgumentException("そのタスクはすでに登録されています。");
		}
		ToDo toDo = new ToDo();
		toDo.setTitle(title);
		toDo.setPriority(priority);
		toDo.setStatus(status);
		toDoRepository.save(toDo);
	}
	public List<ToDo> getAllToDos(){
		return toDoRepository.findAll();
	}

}
