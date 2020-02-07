package net.gfu.wicket.cheesr.webapp.components;

import net.gfu.wicket.backend.bo.Cheese;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class AddToCartPanel extends Panel{

    public AddToCartPanel(String id, IModel<Cheese> cheese) {
        super(id);
        add(new Link<String>("addToCart") {
            @Override
            public void onClick() {
                onAddToCart(cheese);
            }
        });


    }

    public abstract void onAddToCart(IModel<Cheese> cheese);

}

