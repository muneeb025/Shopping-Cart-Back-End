package com.shoppingCart.login.repository;

import java.util.List;

import com.shoppingCart.login.model.Login;
import com.shoppingCart.login.model.Products;

public interface ILoginRepository {

	public Boolean authenticate(String userName, String password);
	
	public void createUser(Login login);
	
	public List <Products> findAll();
	
	public List <Products> getByCate(String pCate);
}
