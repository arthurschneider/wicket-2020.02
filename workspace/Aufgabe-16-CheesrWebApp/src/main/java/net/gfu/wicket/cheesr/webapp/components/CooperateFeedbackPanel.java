package net.gfu.wicket.cheesr.webapp.components;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class CooperateFeedbackPanel extends FeedbackPanel {

	private static final long serialVersionUID = 1L;

	public CooperateFeedbackPanel(String id) {
		super(id);
	}

	public CooperateFeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}
}