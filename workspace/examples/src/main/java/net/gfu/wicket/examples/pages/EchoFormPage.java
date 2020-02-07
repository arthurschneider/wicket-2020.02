package net.gfu.wicket.examples.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class EchoFormPage extends WebPage {
    private static final long serialVersionUID = 1L;

    private Form<String> form;
    private Label message;

    public EchoFormPage(){
        Model<String> inputModel = new Model<>();
        TextField<String> textField = new TextField<>("echo-input", inputModel);

        form = new Form<>("form");
        form.add(textField);

        message = new Label("echo-message", inputModel);

        add(form);
        add(message);
    }
}