package net.gfu.wicket.cheesr.webapp;

import org.apache.wicket.authorization.IAuthorizationStrategy;

public class TestWicketApplication extends WicketApplication{

    @Override
    public void init() {
        super.init();
        getSecuritySettings().setAuthorizationStrategy(IAuthorizationStrategy.ALLOW_ALL);
    }
}