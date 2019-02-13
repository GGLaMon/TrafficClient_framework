package com.mad.trafficclient.bean;

public class F12 {
    private String carnumber;
    private String pcode;
    private String pdatetime;
    private String paddr;


    public F12() {
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getPcode() {
        return pcode;
    }

    public void setPcode(String pcode) {
        this.pcode = pcode;
    }

    public String getPdatetime() {
        return pdatetime;
    }

    public void setPdatetime(String pdatetime) {
        this.pdatetime = pdatetime;
    }

    public String getPaddr() {
        return paddr;
    }

    public void setPaddr(String paddr) {
        this.paddr = paddr;
    }

    public F12(String carnumber, String pcode, String pdatetime, String paddr) {

        this.carnumber = carnumber;
        this.pcode = pcode;
        this.pdatetime = pdatetime;
        this.paddr = paddr;
    }
}
