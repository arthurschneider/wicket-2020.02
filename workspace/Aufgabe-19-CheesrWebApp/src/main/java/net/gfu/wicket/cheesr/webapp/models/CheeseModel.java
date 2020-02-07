package net.gfu.wicket.cheesr.webapp.models;

import net.gfu.wicket.backend.BOServices;
import net.gfu.wicket.backend.bo.Cheese;

public class CheeseModel extends  LockableLDModel<Cheese> {

    private final int id;

    public CheeseModel(int id) {
        this.id = id;
    }

    @Override
    Cheese loadCurrent() {
        return BOServices.get().load(this.id);
    }
}
