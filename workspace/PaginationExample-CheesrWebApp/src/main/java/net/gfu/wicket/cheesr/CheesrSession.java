package net.gfu.wicket.cheesr;

import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import net.gfu.wicket.cheesr.bo.Cart;

public class CheesrSession extends WebSession {

	private static final long serialVersionUID = 1L;
	private Cart cart = new Cart();
	
	public CheesrSession(Request request) {
		super(request);
	}

	public Cart getCart() {
		return cart;
	}
}