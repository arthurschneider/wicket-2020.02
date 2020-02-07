package net.gfu.wicket.cheesr.webapp.components;

import net.gfu.wicket.backend.bo.CheeseAttribute;
import net.gfu.wicket.backend.bo.CheeseDateAttribute;
import net.gfu.wicket.backend.bo.CheesePercantageAttribute;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

public class CheeseAttributePanel<T extends CheeseAttribute>  extends Panel{

    public CheeseAttributePanel(String id, IModel<CheeseAttribute> model) {
        super(id, model);

        add(new Label("attributeName", (IModel<String>) () -> model.getObject().getAttributeName()));
        add(new Label("value", valueModelForLabel()));
    }


    IModel<String> valueModelForLabel(){
        CheeseAttribute model = (CheeseAttribute) getDefaultModelObject();

        if(model instanceof CheeseDateAttribute) {
            return (IModel<String>) () -> {
                Date value = ((CheeseDateAttribute)getDefaultModelObject()).getAttributeValue();
                return DateFormat.getDateInstance().format(value);
            };
        } else if(model instanceof  CheesePercantageAttribute) {
            return (IModel<String>) () -> {
                BigDecimal value = ((CheesePercantageAttribute)getDefaultModelObject()).getAttributeValue();
                return new DecimalFormat("#.00").format(value) + " %";
            };
        } else {
            return (IModel<String>) () -> getDefaultModelObject().toString();
        }
    }

}
