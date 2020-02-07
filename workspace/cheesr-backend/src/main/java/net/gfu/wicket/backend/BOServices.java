package net.gfu.wicket.backend;

import java.util.*;

import javax.annotation.ManagedBean;

import net.gfu.wicket.backend.bo.Cart;
import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.backend.bo.CheeseDB;

@ManagedBean
public class BOServices {

	private static final HashMap<Integer, Cheese> cheeses = new CheeseDB().getCheeses();

	public BOServices(){

	}

	
	/**
	 * Ermittet alle verfuegbaren Kaesesorten
	 * @return - Liste aller Sorten
	 */
	public List<Cheese> allCheeses(){
		return Collections.unmodifiableList(new ArrayList<>(cheeses.values()));
	}

	public void updateCheese(Cheese cheese, int baseLockingVersion) {
		Cheese c = load(cheese.getId());
		if(cheese.getLockingVersion() != baseLockingVersion) {
			throw new IllegalStateException(
					String.format("Trying to update old version. Base version is %d, current version is %d",
							baseLockingVersion,cheese.getLockingVersion()));
		}
		c.setName(cheese.getName());
		c.setDescription(cheese.getDescription());
		c.setPrice(cheese.getPrice());
		c.incrementLock();

	}

	public Cheese load(int id) {
		return cheeses.get(id);
	}


	/**
	 * Uebergibt eine Bestellung zur Abwicklung ins Backend-System
	 */
	public void proccessOrder(Cart cart){
		// Nichts passiert - das ist im Beispiel so. - Loggen:
		System.err.println(">>>> Start: Bearbeite Bestellung <<<<<");
		System.err.println(cart.getBillingAddress().toString());
		for(Cheese c : cart.getCheeses()){
			System.err.println(c.toString());
		}
		System.err.println(">>>> Ende: Bearbeite Bestellung <<<<<");
		
	}

	public static BOServices get(){
		return new BOServices();
	}
}