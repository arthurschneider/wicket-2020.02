package net.gfu.wicket.cheesr.webapp.pages;

import net.gfu.wicket.backend.BOServices;
import net.gfu.wicket.backend.bo.Address;
import net.gfu.wicket.backend.bo.Cart;
import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.cheesr.webapp.components.ShoppingCartPanel;
import net.gfu.wicket.cheesr.webapp.models.CheeseModel;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.util.GenericBaseModel;

public class EditPage extends CheesrPage {

	private static final long serialVersionUID = 1L;

	CheeseModel cheese;

	public EditPage(CheeseModel cheese) {
		this.cheese = cheese;
		add(new FeedbackPanel("feedback"));
		Form<Address> form = new Form<>("form");
		add(form);


		form.add(new TextField<>("name", new PropertyModel<String>(cheese, "name")).setRequired(true));
		form.add(new TextField<>("description", new PropertyModel<String>(cheese, "description")).setRequired(true));
		form.add(new TextField<>("price", new PropertyModel<String>(cheese, "price")).setRequired(true));

		form.add(new Button("update") {
			@Override
			public void onSubmit() {
				System.out.println("Updating Cheese");
				BOServices.get().updateCheese(cheese.getObject(),cheese.getLockVersion());
				cheese.refresh();
				setResponsePage(Index.class);

			}
		});
	}
}