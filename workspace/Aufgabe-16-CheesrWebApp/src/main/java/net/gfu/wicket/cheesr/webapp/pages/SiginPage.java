package net.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import net.gfu.wicket.cheesr.webapp.CheesrSession;

public class SiginPage extends CheesrPage {
    private static final long serialVersionUID = 1L;

    public SiginPage() {
        // Create feedback panel and add to page
        add(new FeedbackPanel("feedback"));

        // Add sign-in form to page
        add(new SignInForm("signInForm"));
    }

    public final class SignInForm extends Form<Void> {
        private static final long serialVersionUID = 1L;

        private static final String USERNAME = "username";
        private static final String PASSWORD = "password";

        // El-cheapo model for form
        private final ValueMap properties = new ValueMap();

        public SignInForm(final String id) {
            super(id);

            // Attach textfield components that edit properties map model
            add(new TextField<>(USERNAME, new PropertyModel<>(properties, USERNAME)));
            add(new PasswordTextField(PASSWORD, new PropertyModel<>(properties, PASSWORD)));
        }

        @Override
        public final void onSubmit() {
            CheesrSession session = (CheesrSession) getSession();

            if (session.signIn(getUsername(), getPassword())) {
                continueToOriginalDestination();
                setResponsePage(getApplication().getHomePage());
            } else {
                String errmsg = getString("loginError", null, "Unable to sign you in");
                error(errmsg);
            }
        }

        private String getPassword() {
            return properties.getString(PASSWORD);
        }

        private String getUsername() {
            return properties.getString(USERNAME);
        }
    }
}
