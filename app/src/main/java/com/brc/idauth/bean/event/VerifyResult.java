package com.brc.idauth.bean.event;

public class VerifyResult {

    private boolean isSamePerson = false;




    public VerifyResult(boolean isSamePerson) {
        this.isSamePerson = isSamePerson;
    }

    public boolean isSamePerson() {
        return isSamePerson;
    }

    public void setSamePerson(boolean samePerson) {
        isSamePerson = samePerson;
    }
}
