package net.gfu.wicket.cheesr.webapp.models;

import net.gfu.wicket.backend.bo.Lockable;
import org.apache.wicket.model.LoadableDetachableModel;

public abstract class LockableLDModel<T extends Lockable> extends LoadableDetachableModel<T> {

    private Integer lockVersion; // The lock version has to be kept seperated

    // Reload from database - update files
    public void refresh() {
        lockVersion = null;
        load();
    }


    // Note: Our database does not story any historic data
    // Thus it is futile to load an old version of this entry

    // This method is different, if the database supports loading historic data
    @Override
    protected T load() {
        T t = loadCurrent();
        if(lockVersion == null) {
            lockVersion = t.getLockingVersion();
        }
        return t;
    }

    // Template method for loading data

    abstract T loadCurrent();

    public Integer getLockVersion() {
        return lockVersion;
    }
}
