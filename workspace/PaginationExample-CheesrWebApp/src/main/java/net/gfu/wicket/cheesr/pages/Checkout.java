package net.gfu.wicket.cheesr.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import net.gfu.wicket.cheesr.bo.Address;
import net.gfu.wicket.cheesr.bo.Cart;
import net.gfu.wicket.cheesr.components.ShoppingCartPanel;

public class Checkout extends CheesrPage {
	private static final long serialVersionUID = 1L;

	public Checkout() {
		add(new ShoppingCartPanel("shoppingcart", Model.of(getCart())));

		Form<Address> form = new Form<>("form");
		add(form);
		Address address = getCart().getBillingAddress();
		form.add(new TextField<>("name", new PropertyModel<String>(address, "name")).setRequired(true));
		form.add(new TextField<>("street", new PropertyModel<String>(address, "street")).setRequired(true));
		form.add(new TextField<>("zipcode", new PropertyModel<String>(address, "zipcode")).setRequired(true));
		form.add(new TextField<>("city", new PropertyModel<String>(address, "city")).setRequired(true));

		add(new FeedbackPanel("feedback"));
		
		form.add(new Link<Void>("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Index.class);
			}
		});
		form.add(new Button("order") {
			@Override
			 public void onSubmit() {
				 Cart cart = getCart();
				 cart.getCheeses().clear();
				 setResponsePage(Index.class);
			};
		});
	
	}
}