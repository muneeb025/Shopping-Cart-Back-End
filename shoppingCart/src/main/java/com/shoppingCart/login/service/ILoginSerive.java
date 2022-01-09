package com.shoppingCart.login.service;

import java.util.List;

import com.shoppingCart.login.model.Email;
import com.shoppingCart.login.model.Login;
import com.shoppingCart.login.model.Products;

public interface ILoginSerive {

	public Boolean authenticateUser(String userName, String password);
	
	public Login create(Login login);
	
	public List<Products> getAllProducts();
	
	public List <Products> getByCate(String pCate);
	
	public void sendEmail(Email email);
	
	
}
