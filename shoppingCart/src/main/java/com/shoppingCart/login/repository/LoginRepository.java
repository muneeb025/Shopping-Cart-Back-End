package com.shoppingCart.login.repository;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.shoppingCart.login.model.Login;
import com.shoppingCart.login.model.Products;



@Repository
public class LoginRepository implements ILoginRepository{

	private Object[] sqlArgs;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public Boolean authenticate(String userName, String password) {
		
		String sql = "select exists( select * from credentials "
				 + "where userid = ? and password = ? " 
				 + "and status = 'active')";
		
		Object[] sqlArgs = new Object[] {userName, password};
		
		@SuppressWarnings("deprecation")
		Integer accountValid = jdbcTemplate.queryForObject(sql, sqlArgs, Integer.class);
		
		return (accountValid == 1) ? true: false;

	}

	@Override
	public void createUser(Login login) {
		String sql = "insert into credentials(userid, password, status)" + "values(?, ?, ?)";
		
		this.sqlArgs = new Object [] {login.getUserName(),
									  login.getPassword(),
									  "active"};
		
		jdbcTemplate.update(sql, this.sqlArgs);
		
	}

	
	@Override
	public List<Products> findAll() {
		String SQL = "select * from products";

		@SuppressWarnings("unchecked")
		List<Products> products = jdbcTemplateObject.query(SQL, 
									BeanPropertyRowMapper.newInstance(Products.class));

		return products;
	}

	@Override
	public List <Products> getByCate(String pCate) {
		
		String SQL = String.format("SELECT * FROM products WHERE MATCH (pcate) AGAINST ('%s')", pCate);

		@SuppressWarnings("unchecked")
		
		List <Products> product = jdbcTemplateObject.query(SQL, BeanPropertyRowMapper.newInstance(Products.class));
		
		return product;
	}
	
}
