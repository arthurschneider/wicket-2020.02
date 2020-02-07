package net.gfu.wicket.cheesr.webapp;

import java.text.NumberFormat;

import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;

public class PriceModel implements IModel<String> {

	private static final long serialVersionUID = 1L;

	
	private String value;
	
	public PriceModel(Number value){
		NumberFormat fmt = NumberFormat.getCurrencyInstance(Session.get().getLocale());
		this.value = fmt.format(value);
	}
	
	@Override
	public String getObject() {
		return value;
	}

	@Override
	public void detach() {
		
	}

	@Override
	@Deprecated
	public void setObject(String arg0) {
		throw new IllegalAccessError();
	}
}