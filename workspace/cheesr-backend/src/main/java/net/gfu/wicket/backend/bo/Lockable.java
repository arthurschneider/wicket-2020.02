package net.gfu.wicket.backend.bo;

public abstract class Lockable {

    private int lockingVersion;

    public int getLockingVersion(){
        return lockingVersion;
    }
    public void setLockingVersion(int lockingVersion) {
        this.lockingVersion = lockingVersion;
    }
    public void incrementLock(){
        this.lockingVersion++;
    }

}
