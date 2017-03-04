package com.crossover.spring.models;

import java.util.List;

import com.crossover.hibernate.data.User;

public interface UserDAO {
	
	public void addUser(User u) throws Exception;

	public void updateUser(User u) throws Exception;

	public List<User> listUsers() throws Exception;

	public User getUserById(Long id) throws Exception;

	public void removeUser(int id) throws Exception;
	
	public User getUserByEmail(String email) throws Exception;
}
