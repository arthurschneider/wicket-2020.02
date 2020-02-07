package net.gfu.wicket;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see net.gfu.wicket.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}


	@Override
	public void init()
	{
		super.init();

		getResourceBundles().addJavaScriptBundle(WicketApplication.class, "bundle.js",
                JSLib.application,
                JSLib.application_alert);
	}
}