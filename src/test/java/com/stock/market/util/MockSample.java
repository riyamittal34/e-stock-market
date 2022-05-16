package com.stock.market.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.stock.market.dto.CompanyDto;
import com.stock.market.dto.RoleDto;
import com.stock.market.dto.UserDto;
import com.stock.market.entity.CompanyDao;
import com.stock.market.entity.RoleDao;
import com.stock.market.entity.UserDao;

/**
 * The Class MockSample.
 */
public class MockSample {

	/**
	 * Gets the company object.
	 *
	 * @return the company object
	 */
	public static CompanyDto getCompanyObject() {
		CompanyDto company = new CompanyDto();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		company.setCompanyCeo("Riya");
		company.setCompanyTurnover("1000000000");
		company.setCompanyWebsite("http://www.google.com");
		company.setStockExchange("NSE");
		return company;
	}
	
	/**
	 * Gets the company dao object.
	 *
	 * @return the company dao object
	 */
	public static CompanyDao getCompanyDaoObject() {
		CompanyDao company = new CompanyDao();
		company.setCompanyCode("abc");
		company.setCompanyId(UUID.randomUUID().toString());
		company.setCompanyName("ABC Company");
		company.setCompanyCeo("Riya");
		company.setCompanyTurnover("1000000000");
		company.setCompanyWebsite("http://www.google.com");
		company.setStockExchange("NSE");
		return company;
	}
	
	/**
	 * Gets the user dto.
	 *
	 * @return the user dto
	 */
	public static UserDto getUserDto() {
		UserDto user = new UserDto();
		user.setName("riya");
		user.setPassword("riya");
		user.setUsername("riya");
		return user;
	}
	
	/**
	 * Gets the user dao.
	 *
	 * @return the user dao
	 */
	public static UserDao getUserDao() {
		UserDao user = new UserDao();
		user.setName("riya");
		user.setPassword("riya");
		user.setUsername("riya");
		user.setUserId(UUID.randomUUID().toString());
		RoleDao role = new RoleDao();
		role.setRoleId(UUID.randomUUID().toString());
		role.setRoleName("ROLE_ADMIN");
		List<RoleDao> roles = new ArrayList<>();
		roles.add(role);
		user.setRoles(roles);
		return user;
	}
	
	/**
	 * Gets the role dto.
	 *
	 * @return the role dto
	 */
	public static RoleDto getRoleDto() {
		RoleDto role = new RoleDto();
		role.setRoleName("ROLE_ADMIN");
		return role;
	}
	
	/**
	 * Gets the role dao.
	 *
	 * @return the role dao
	 */
	public static RoleDao getRoleDao() {
		RoleDao role = new RoleDao();
		role.setRoleName("ROLE_ADMIN");
		role.setRoleId(UUID.randomUUID().toString());
		return role;
	}

}
