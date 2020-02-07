package net.gfu.wicket.cheesr.webapp;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.ExceptionSettings;

import net.gfu.wicket.cheesr.webapp.pages.ErrorPage;
import net.gfu.wicket.cheesr.webapp.pages.Index;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see net.gfu.wicket.cheesr.webapp.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{

	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return Index.class;
	}


	@Override
	public void init(){
		super.init();
		getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		getApplicationSettings().setInternalErrorPage(ErrorPage.class);
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new CheesrSession(request);
	}
}