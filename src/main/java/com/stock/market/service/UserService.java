package com.stock.market.service;

import java.util.List;

import com.stock.market.dto.RoleDto;
import com.stock.market.dto.UserDto;
import com.stock.market.entity.RoleDao;
import com.stock.market.entity.UserDao;

public interface UserService {

	public UserDao addUser(UserDto user);
	
	public RoleDao addRole(RoleDto role);
	
	public void addRoleToUser(String username, String roleName);
	
	public UserDao getUser(String username);
	
	public List<UserDao> getAllUsers();
}
