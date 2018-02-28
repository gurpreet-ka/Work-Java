package com.bookStore;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OnlineBookStore extends ShoppingCart implements BookList { 
	
	ShoppingCart shoppingCart = new ShoppingCart();
	static BigDecimal totalPrice= new BigDecimal(0.00);
	
	public OnlineBookStore() throws IOException {
		 Scanner s = new Scanner(System.in);
		 System.out.println("Enter the choice from below options: ");
		 System.out.println("1. List the Books Available ");
		 System.out.println("2. Search A Book");
		 System.out.println("3. Buy A Book");
		 System.out.println("4. Exit");
		 
		 int user_choice= s.nextInt();
		 
		 start(user_choice);
	}
	
	public static void main(String[] args) throws IOException {
	System.out.println(":::Welcome to My Book Store:::");
	OnlineBookStore obs =  new OnlineBookStore();
	}

	private void menu() throws IOException {
		 Scanner s2 = new Scanner(System.in);
		 System.out.println("Do You want to continue shopping? Enter appropriate option from below: ");
		 System.out.println("1. Yes "+"\n" +"2. No");
			 
		 int user_choice= s2.nextInt();
		 if(user_choice==1) {
			 OnlineBookStore obs =  new OnlineBookStore();
		 }else if(user_choice==2) {
			 System.out.println("Thank You For Shopping With US !!");
		 }
	}
	private void start(int userChoice) throws IOException {
		String titleOfBook="";
		switch(userChoice) {
		
		 case 1:
			 System.out.println("Available Books are: ");
			// getting all books from BookStore Data
			List<String> data= getBookStoreData();
			String [] books;
			if (null != data && data.size() > 0) {
				for (int index = 0; index < data.size(); index++) {
					books = data.get(index).split(";");
					if (null != books && books.length > 0) {
						int count = 0;
						System.out.println("Title : " + books[count] + "\n" + "Author : " + books[++count] + "\n"
								+ "Price : " + books[++count] + "\n" + "Quantity : " + books[++count] + "\n");
					}

				}
			}
			menu();
			 break;
		 case 2:
			 System.out.println("Enter Name/Author of book you want to search");
			 Scanner ss = new Scanner(System.in);
			  titleOfBook = ss.nextLine();  
			 Book[] searchedBooks = list(titleOfBook);
				if(searchedBooks.length>0) {
				for (Book book : searchedBooks) {
					int[] statusOfBook = buy(book);
					if (null != statusOfBook && statusOfBook.length > 0 && null != book) {
						System.out.println("Author:: " + book.getAuthor() + "\n" + "Title:: " + book.getTitle() + "\n"
								+ "Price:: " + book.getPrice());
						for (int status : statusOfBook) {

							if (status == 0) {
								System.out.println("Availability: Yes");
							} else if (status == 1) {
								System.out.println("Availability: No");
							} else {
								System.out.println("Availability: Book does not exist");
							}

						}
					}

				}
				}else {
					System.out.println("No Match Found");
				}
			
				menu();
			 break;
		 case 3:
			 System.out.println("Enter Name/Author of book you want to Buy");
			 Scanner s3= new Scanner(System.in);
			 titleOfBook = s3.nextLine();  
			 Book[] requiredBooks = list(titleOfBook);
			
			 int counterForOption=1;
			if (requiredBooks.length > 0) {
				System.out.println("Select an option from below list to choose");
				for (Book book : requiredBooks) {
					int[] statusOfBooks = buy(book);
					System.out.println(counterForOption + ". " + "Author:: " + book.getAuthor() + "\n" + "Title:: "
							+ book.getTitle() + "\n" + "Price:: " + book.getPrice());
					if (null != statusOfBooks && statusOfBooks.length > 0) {
						for (int status : statusOfBooks) {
							if (status == 0) {
								System.out.println("Book is  Available");
							} else if (status == 1) {
								System.out.println("Book is Not Available");
								menu();
							} else {
								System.out.println("Book does not exist");
							}
						}
					}
					counterForOption++;
				}
			}else {
				System.out.println("No Match Found");
				menu();
			}
			 Scanner s4 = new Scanner(System.in);
			 int choice= s4.nextInt();
			 Book 	book= new Book();
			 int [] statusOfBooks;
			 switch(choice) {
			 case 1:
				 System.out.println("You added: "+ choice);
				 if(null != requiredBooks && !(requiredBooks.length==0) && null !=requiredBooks[0]) {
				  	book=requiredBooks[0];
				  	statusOfBooks = buy(book);
					if(statusOfBooks[0]==0) {
					// buy the book
					System.out.println("Enter the quantity");
					int qty= s4.nextInt();
					boolean addToCart= add(book,qty);
					if(addToCart) {
						System.out.println("Total Price: "+shoppingCart.getTotalprice());
						System.out.println("Do you want to add any other book?"+"\n"+"1.YES"+"\n"+"2. NO");
						int wantMoreBooks= s4.nextInt();
						if(wantMoreBooks==1) {
							OnlineBookStore obs1 =  new OnlineBookStore();
						}else {
							System.out.println("Thank You for Purchasing !!");
							break;
						}
						}else {
							System.out.println("book cannot be added to cart , please try again.");
							menu();
						}
					System.out.println("Total Price: "+shoppingCart.getTotalprice());
					System.out.println("Do you want to continue to payment? "+"\n"+"1.YES"+"\n"+"2.NO");
					int approve= s4.nextInt();
					if(approve==1) {
						System.out.println("Thank You for Purchasing !!");
						break;
					}else {
						menu();
					}
					}else if(statusOfBooks[0]==1) {
						System.out.println("Book is Not Available");
						menu();
					}else {
						System.out.println("Invalid Input, please try again");
						menu();
					}
				 }else {
					 System.out.println("Invalid option");
					 menu();
				 }
				 
			 break;
			 case 2:
				 System.out.println("Your choice: "+ choice);
				 System.out.println("enter the quantity");
				 int qty= s4.nextInt();
				 if(null != requiredBooks && requiredBooks.length>1 && null !=requiredBooks[1]) {
				  book =requiredBooks[1];
				  statusOfBooks = buy(book);
					if(statusOfBooks[0]==0) {
						// buy the book
						boolean addToCart= add(book,qty);
						if(addToCart) {
						System.out.println("Total Price: "+shoppingCart.getTotalprice());
						System.out.println("Do you want to add any other book?"+"\n"+"1.YES"+"\n"+"2. NO");
						int wantMoreBooks= s4.nextInt();
						if(wantMoreBooks==1) {
							OnlineBookStore obs1 =  new OnlineBookStore();
						}else {
							System.out.println("Thank You for Purchasing !!");
							break;
						}
						}else {
							System.out.println("Book cannot be added to cart , Please try again.");
							menu();
						}
						}else if(statusOfBooks[0]==1) {
							System.out.println("Book is Not Available");
							menu();
						}else {
							System.out.println("Invalid Input, Please try again");
							menu();
						}
				 }else {
					 System.out.println("Invalid Choice");
					 menu();
				 }
				 break;
				 
			 }
		
			 break;
		 case 4:
			 System.out.println("Thank You");
			 break;
		default:
				 System.out.println("Bad input, Please try again");
				 menu();
		 }
		
		 
	}
	private List<String> getBookStoreData() throws IOException {
		
        URL url = new URL("https://raw.githubusercontent.com/contribe/contribe/dev/bookstoredata/bookstoredata.txt");

        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        List<String> line = new ArrayList<String>();
        String read;
        while((read=br.readLine()) != null) {
        	line.add(read);
        }
       
		return line;
	}

	@Override
	public Book[] list(String searchBook) throws IOException{
		List<String> bookData = getBookStoreData();
		List<Book> books = new ArrayList<Book>();
		
		System.out.println("You searched for: "+searchBook);
		if(null !=bookData && bookData.size()>0) {
			for(int index=0;index<bookData.size();index++) {
				Book book = new Book();
				String [] splitBookData= bookData.get(index).split(";");
				if(null !=splitBookData && splitBookData.length>0) {
					if(null !=splitBookData[0] && null != splitBookData[1] 
							&& (splitBookData[0].equalsIgnoreCase(searchBook) || splitBookData[1].equalsIgnoreCase(searchBook))) {
						
						book.setAuthor(splitBookData[1]);
						book.setTitle(splitBookData[0]);
						if(null != splitBookData[2] && splitBookData[2].contains(",")) {
							splitBookData[2]= splitBookData[2].replace(",", "");
						}
						book.setPrice(new BigDecimal(splitBookData[2]));
						books.add(book);	
						
						
					}
				}
				
			}
		}
		Book [] bb = new Book[books.size()];
		return books.toArray(bb);
	}

	@Override
	public boolean add(Book book, int quantity) {
		if (null != book && !(quantity == 0)) {
			BigDecimal price = book.getPrice();
			totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
			//creating shopping cart
			shoppingCart.setTotalprice(totalPrice);
			shoppingCart.getItems().add(book);
			if (null != shoppingCart && null != shoppingCart.getItems() && shoppingCart.getItems().size() > 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int[] buy(Book... books) throws IOException {
		
		int [] statusOfBooks= new int[books.length];
		List<String> bookData = getBookStoreData();
		int counter=0;
		for(Book book : books) {
		
			for(int index=0;index<bookData.size();index++) {
				String [] splitBookData= bookData.get(index).split(";");
				if(null != splitBookData && splitBookData.length>0) {
					
					if(null != splitBookData[2] && splitBookData[2].contains(",")) {
						splitBookData[2]= splitBookData[2].replace(",", "");
					}
					
					if(null != book && book.getTitle().equalsIgnoreCase(splitBookData[0]) && book.getAuthor().equalsIgnoreCase(splitBookData[1])
							&& book.getPrice().toString().equalsIgnoreCase(splitBookData[2])) {
						
						if(Integer.parseInt(splitBookData[3])>0) {
							statusOfBooks[counter]=Status.OK.getValue();
						}else if(Integer.parseInt(splitBookData[3])==0) {
							statusOfBooks[counter]=Status.NOT_IN_STOCK.getValue();
						}else {
							statusOfBooks[counter]=Status.Does_not_exist.getValue();
						}
						counter++;
					}
				}
			}
		}
		
		return statusOfBooks;
	}

}
