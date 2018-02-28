package com.bookStore;

import java.io.IOException;

public interface BookList {

	 public Book [] list (String searchBook) throws IOException;

	  public boolean add (Book book, int quantity);

	  public int [] buy (Book ... books) throws IOException;
	  
}
