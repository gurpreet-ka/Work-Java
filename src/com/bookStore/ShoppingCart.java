package com.bookStore;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

	private BigDecimal totalprice;
	private List<Book> items = new ArrayList<Book>();
	
	
	public BigDecimal getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}
	public List<Book> getItems() {
		return items;
	}
	
}
