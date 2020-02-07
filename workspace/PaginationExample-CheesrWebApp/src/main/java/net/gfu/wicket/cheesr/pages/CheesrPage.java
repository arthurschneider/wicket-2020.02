package net.gfu.wicket.cheesr.pages;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;

import net.gfu.wicket.cheesr.CheesrApplication;
import net.gfu.wicket.cheesr.CheesrSession;
import net.gfu.wicket.cheesr.bo.Cart;
import net.gfu.wicket.cheesr.bo.Cheese;

public class CheesrPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public CheesrSession getCheesrSession(){
		return (CheesrSession) super.getSession();
	}
	
	public Cart getCart(){
		return getCheesrSession().getCart();
	}

	public List<Cheese> getCheeses(){
		return CheesrApplication.get().getCheeses();
	}
}