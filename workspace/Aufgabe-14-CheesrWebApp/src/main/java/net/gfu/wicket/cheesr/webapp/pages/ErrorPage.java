package net.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

public class ErrorPage extends CheesrPage{
	
	private static final long serialVersionUID = 1L;
	
	public ErrorPage() {
		super();
		
		add(new Label("errorMessage", Model.of("Leider ist ein technischer Fehler aufgetreten. Bitte kontaktieren Sie den Support.")));
		
	}
}