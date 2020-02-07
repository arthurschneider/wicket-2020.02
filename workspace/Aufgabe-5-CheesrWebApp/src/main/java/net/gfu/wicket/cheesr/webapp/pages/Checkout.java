package net.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import net.gfu.wicket.backend.BOServices;
import net.gfu.wicket.backend.bo.Address;
import net.gfu.wicket.backend.bo.Cart;

public class Checkout extends CheesrPage {

	private static final long serialVersionUID = 1L;

	public Checkout() {
		Form<Address> form = new Form<>("form");
		add(form);
		Address address = getCart().getBillingAddress();
		form.add(new TextField<>("name", new PropertyModel<String>(address, "name")));
		form.add(new TextField<>("street", new PropertyModel<String>(address, "street")));
		form.add(new TextField<>("zipcode", new PropertyModel<String>(address, "zipcode")));
		form.add(new TextField<>("city", new PropertyModel<String>(address, "city")));

		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Index.class);
			}
		});
		form.add(new Button("order") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onSubmit() {
				BOServices.get().proccessOrder(getCart());
				Cart cart = getCart();
				cart.getCheeses().clear();
				setResponsePage(Index.class);
			}
		});
	}
}