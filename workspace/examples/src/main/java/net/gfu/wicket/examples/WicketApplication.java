package net.gfu.wicket.examples;

import net.gfu.wicket.examples.pages.EchoFormPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

import net.gfu.wicket.examples.pages.ClickCounterPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see net.gfu.wicket.examples.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return EchoFormPage.class;
	}

	@Override
	public void init() {
		super.init();

		// add your configuration here
	}
}