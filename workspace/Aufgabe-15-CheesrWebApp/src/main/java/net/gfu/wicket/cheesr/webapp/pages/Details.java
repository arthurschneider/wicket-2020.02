package net.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import net.gfu.wicket.backend.BOServices;
import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.cheesr.webapp.components.DetailPanel;

public class Details extends CheesrPage {

    private static final long serialVersionUID = 1L;

    public Details(final PageParameters parameters) {
        super();

        String name = parameters.get("name").toString("");
        Cheese cheese = getCheese(name);

        if (cheese != null) {
            add(new DetailPanel("details", new Model<>(cheese)));
        } else {
            add(new Label("details", "Cheese '" + name + "' not found."));
        }
    }

    public Cheese getCheese(String name) {
        Cheese cheese = null;

        for (Cheese item : BOServices.get().allCheeses()) {
            if (name.equals(item.getName())) {
                cheese = item;
                break;
            }
        }
        return cheese;
    }
}