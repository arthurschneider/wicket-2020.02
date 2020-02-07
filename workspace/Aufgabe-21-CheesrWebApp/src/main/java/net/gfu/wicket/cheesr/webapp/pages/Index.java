package net.gfu.wicket.cheesr.webapp.pages;

import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.cheesr.webapp.components.AddToCartPanel;
import net.gfu.wicket.cheesr.webapp.resources.CSVExportResource;
import net.gfu.wicket.cheesr.webapp.tables.CheeseTableDefinition;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.table.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.gfu.wicket.cheesr.webapp.components.ShoppingCartPanel;

import java.util.ArrayList;


public class Index extends CheesrPage {
	private static final long serialVersionUID = 1L;
	
	public Index(final PageParameters parameters) {
		super();
		//CheeseListModel m = new CheeseListModel();

		// Definition fuer CSV und Wicket
		final CheeseTableDefinition cheeseTableDef = new CheeseTableDefinition();
		ArrayList<IColumn> cols = new ArrayList<>(cheeseTableDef.columns());

		cols.add(createAddToCartColum());

		DefaultDataTable table = new DefaultDataTable("cheeseTable", cols , cheeseTableDef.provider(), 10);

		add(table);

		add(new ShoppingCartPanel("cart",getCartModel()));

		add(new ResourceLink("export", new CSVExportResource()));

		add(new Link<String>("checkout"){
			private static final long serialVersionUID = 1L;
			@Override

			public void onClick() {
				setResponsePage(new Checkout());

			}
			@Override
			public boolean isVisible() {
				return !getCart().getCheeses().isEmpty();
			}
		});
	}

	private HeaderlessColumn createAddToCartColum(){
		return new HeaderlessColumn<Cheese,String>() {
			@Override
			public void populateItem(Item<ICellPopulator<Cheese>> item, String s, final IModel<Cheese> cheese) {
				item.add(new AddToCartPanel(s,cheese) {
					@Override
					public void onAddToCart(IModel<Cheese> cheese) {
						getCart().getCheeses().add(cheese.getObject());
					}
				});
			}
		};
	}
}