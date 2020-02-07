package net.gfu.wicket;

import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class JSLib {
	public static final JavaScriptResourceReference application = 
			new JavaScriptResourceReference(WicketApplication.class, "application.js");
	public static final JavaScriptResourceReference application_alert = 
			new JavaScriptResourceReference(WicketApplication.class, "alert.js");


}
