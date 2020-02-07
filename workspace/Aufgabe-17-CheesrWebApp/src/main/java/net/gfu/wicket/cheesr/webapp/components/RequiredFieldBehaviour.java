package net.gfu.wicket.cheesr.webapp.components;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.feedback.FeedbackMessages;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

public class RequiredFieldBehaviour extends Behavior {
	private static final long serialVersionUID = 1L;

		@Override
	public void onComponentTag(Component component, ComponentTag tag) {
		super.onComponentTag(component, tag);
		
		if(component instanceof FormComponent<?>){
			FormComponent<?> f = (FormComponent<?>)component;
			FeedbackMessages feedbackMessages = f.getFeedbackMessages();
			if(feedbackMessages != null && feedbackMessages.size() > 0){
				String attribute = tag.getAttribute("class") ;
				attribute = (attribute == null)? "" : attribute;
				tag.put("class", String.format("%s has-error", attribute));
			}
}}
	@Override
	public void onConfigure(Component component) {
		super.onConfigure(component);
		if(component instanceof FormComponent<?>){
			FormComponent<?> f = (FormComponent<?>)component;
			f.setRequired(true);
		}
}}
