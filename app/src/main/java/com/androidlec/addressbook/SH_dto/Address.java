package com.androidlec.addressbook.SH_dto;

public class Address {

    private int aseqno;
    private String aname, aphone, aemail, atag, aimage, amemo;

    public Address(int aseqno, String aname, String aimage, String aphone, String aemail, String amemo, String atag) {
        this.aseqno = aseqno;
        this.aname = aname;
        this.aphone = aphone;
        this.aemail = aemail;
        this.atag = atag;
        this.amemo = amemo;
        this.aimage = aimage;
    }

    public int getAseqno() {
        return aseqno;
    }

    public void setAseqno(int aseqno) {
        this.aseqno = aseqno;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAphone() {
        return aphone;
    }

    public void setAphone(String aphone) {
        this.aphone = aphone;
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail;
    }

    public String getAtag() {
        return atag;
    }

    public void setAtag(String atag) {
        this.atag = atag;
    }

    public String getAimage() {
        return aimage;
    }

    public void setAimage(String aimage) {
        this.aimage = aimage;
    }

    public String getAmemo() {
        return amemo;
    }

    public void setAmemo(String amemo) {
        this.amemo = amemo;
    }
}
