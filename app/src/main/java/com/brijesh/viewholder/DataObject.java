package com.brijesh.viewholder;
//this is our model class
public class DataObject {
    private String CODE;
    //private String end_date;
    private String END_TIME;
    //private String key;
    private String URL;
    private String NAME;
    //private String site;
   // private String start_date;
    private String START_TIME;

    public DataObject(){
        //firebase
    }

    public DataObject(String CODE, String END_TIME, String NAME, String START_TIME,String URL) {
        this.CODE = CODE;
        //this.END = end_date;
        this.END_TIME = END_TIME;
        //this.key = key;
        this.URL = URL;
        this.NAME = NAME;
        //this.site = site;
        //this.start_date = start_date;
        this.START_TIME = START_TIME;
    }

    public String getCODE() {
        return CODE;
    }

    public String getEND_TIME() {
        return END_TIME;
    }

    public String getURL() {
        return URL;
    }

    public String getNAME() {
        return NAME;
    }

    public String getSTART_TIME() {
        return START_TIME;
    }
}
