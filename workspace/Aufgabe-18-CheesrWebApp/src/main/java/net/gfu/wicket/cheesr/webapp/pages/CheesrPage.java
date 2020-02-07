package net.gfu.wicket.cheesr.webapp.pages;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentUpdatingBehavior;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import net.gfu.wicket.backend.bo.Cart;
import net.gfu.wicket.cheesr.webapp.CheesrSession;

public abstract class CheesrPage extends WebPage {
	private static final long serialVersionUID = 1L;


	public CheesrPage() {
		LocaleDropDown localeDropDown = new LocaleDropDown("localeSelect", Arrays.asList(Locale.US, Locale.GERMANY));
		localeDropDown.add(new FormComponentUpdatingBehavior());

		add(localeDropDown);
	}

	public Cart getCart() {
		return ((CheesrSession) getSession()).getCart();
	}
	
	public Model<Cart> getCartModel() {
		return new Model<Cart>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Cart getObject() {
				return getCart();
			}
		};
	}
	
	private static class LocaleDropDown extends DropDownChoice<Locale> {
		private static final long serialVersionUID = 1L;

		private class LocaleRenderer extends ChoiceRenderer<Locale> {
	      @Override
	      public String getDisplayValue(Locale locale) {
	        return ((Locale) locale).getDisplayName(getLocale());
	      }
	    }

	    LocaleDropDown(String id, List<Locale> supportedLocales) {
	      super(id, supportedLocales);
	      setChoiceRenderer(new LocaleRenderer());
	      setDefaultModel(new IModel() {

	        public Object getObject() {
	          return getSession().getLocale();
	        }

	        public void setObject(Object object) {
	          getSession().setLocale((Locale) object);
	        }

	        public void detach() {
	        }
	      });
	    }
	  }
}