package reggensc.poschtiapp.domain;

import java.util.Date;
import java.util.Set;

public class ShoppingList {
	
	private String name;
	private Date creationDate;
	private Date purchaseDate;
	
	private Set<User> shoppers;
	private State state;
	
	private Set<ShoppingItem> items;
	

}
