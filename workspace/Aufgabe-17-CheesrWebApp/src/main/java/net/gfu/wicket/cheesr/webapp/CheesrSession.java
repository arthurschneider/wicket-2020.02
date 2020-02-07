package net.gfu.wicket.cheesr.webapp;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import net.gfu.wicket.backend.bo.Cart;

public class CheesrSession extends AuthenticatedWebSession {

	private static final long serialVersionUID = 1L;
	private Cart cart = new Cart();
	
	public CheesrSession(Request request) {
		super(request);
	}

	public Cart getCart() {
		return cart;
	}

	@Override
	protected boolean authenticate(String arg0, String arg1) {
		return "gfu".equals(arg0) && "gfu".equals(arg1);
	}

	@Override
	public Roles getRoles() {
		return null;
	}
}