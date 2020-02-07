package net.gfu.wicket.cheesr.webapp;

import org.apache.wicket.DefaultPageManagerProvider;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.authroles.authentication.pages.SignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.pageStore.IDataStore;
import org.apache.wicket.pageStore.memory.HttpSessionDataStore;
import org.apache.wicket.pageStore.memory.PageNumberEvictionStrategy;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.settings.ExceptionSettings;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.gfu.wicket.cheesr.webapp.pages.Details;
import net.gfu.wicket.cheesr.webapp.pages.ErrorPage;
import net.gfu.wicket.cheesr.webapp.pages.Index;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see net.gfu.wicket.cheesr.webapp.Start#main(String[])
 */
public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends WebPage> getHomePage() {
		return Index.class;
	}


	@Override
	public void init() {
		super.init();
		getExceptionSettings().setUnexpectedExceptionDisplay(ExceptionSettings.SHOW_INTERNAL_ERROR_PAGE);
		getApplicationSettings().setInternalErrorPage(ErrorPage.class);
		mountPage("/cheese/${name}", Details.class);
		setPageManagerProvider(new DefaultPageManagerProvider(this) {
			@Override
			protected IDataStore newDataStore() {
				return new HttpSessionDataStore(getPageManagerContext(), new PageNumberEvictionStrategy(2));
			}
		});

		getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy.AllowAllAuthorizationStrategy() {
			@Override
			public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass) {
				if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
					if (((CheesrSession) Session.get()).isSignedIn()) {
						return true;
					}
					throw new RestartResponseAtInterceptPageException(SignInPage.class);
				}
				return true;
			}
		});
		
	    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
	    //Scan package for annotated beans
	    ctx.scan("net.gfu.wicket.backend");
	    ctx.refresh();

	    getComponentInstantiationListeners().add(new SpringComponentInjector(this, ctx));
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new CheesrSession(request);
	}
}