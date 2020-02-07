package net.gfu.wicket.cheesr.webapp.components;

import net.gfu.wicket.backend.bo.Cheese;
import net.gfu.wicket.backend.bo.CheeseAttribute;
import net.gfu.wicket.cheesr.webapp.helper.CheeseAttributeComparator;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;

import java.util.*;

public class CheeseAttributeList extends ListView<CheeseAttribute> {

    // In der Regel kann Konfiguration gut mit Spring injected werden
    // Da Spring jedoch in einer anderen Aufgabe erarbeitet wird, hier haendich

    public CheeseAttributeList(String id, IModel<Cheese> cheese) {
        super(id, new IModel<List<CheeseAttribute>>() {
            @Override
            public List<CheeseAttribute> getObject() {
                List<CheeseAttribute> attrs = new ArrayList<>(cheese.getObject().getAdditionalAttributes().values());
                Collections.sort(attrs, new CheeseAttributeComparator());
                return attrs;
            };
        });
    }

    @Override
    protected void populateItem(ListItem<CheeseAttribute> item) {
        item.add(new CheeseAttributePanel("attr", item.getModel()));
    }




}
