package net.gfu.wicket.examples.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;

import java.util.Optional;

public class ClickCounterPage extends WebPage {
	private static final long serialVersionUID = 1L;

	private int linkClicks, ajaxLinkClicks;

	private Label linkLabel;
	private Label ajaxLinkLabel;
	private Link<Void> link;
	private AjaxFallbackLink<Void> ajaxLink;

	public ClickCounterPage(){
		linkClicks = 0;
		ajaxLinkClicks = 0;

		Model<Integer> linkLabelModel = Model.of(linkClicks);
		Model<Integer> ajaxLinkLabelModel = Model.of(ajaxLinkClicks);

		linkLabel = new Label("link-label", linkLabelModel);

		ajaxLinkLabel = new Label("ajax-link-label", ajaxLinkLabelModel);
		ajaxLinkLabel.setOutputMarkupId(true);

		link = new Link<Void>("link-link"){
			@Override
			public void onClick() {
				linkClicks++;
				linkLabelModel.setObject(linkClicks);
			}
		};

		ajaxLink = new AjaxFallbackLink<Void>("ajax-link-link"){
			@Override
			public void onClick(Optional<AjaxRequestTarget> optional) {
				if (optional.isPresent()){
					ajaxLinkClicks++;
					ajaxLinkLabelModel.setObject(ajaxLinkClicks);

					optional.get().add(ajaxLinkLabel);
				}
			}
		};

		add(linkLabel, ajaxLinkLabel);
		add(link, ajaxLink);
	}
}