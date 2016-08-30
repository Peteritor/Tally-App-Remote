package com.example.peter_sumit.tally_data;

public class DataObject {
    private String mHeading;
    private String mOpBalText;
    private String mCpBalText;


    DataObject(String heading, String opBal, String cpBal ){
        this.mHeading=heading;
        this.mOpBalText=opBal;
        this.mCpBalText=cpBal;
    }

    public String getmCpBalText() {
        return mCpBalText;
    }

    public void setmCpBalText(String mCpBalText) {
        this.mCpBalText = mCpBalText;
    }

    public String getmOpBalText() {

        return mOpBalText;
    }

    public void setmOpBalText(String mOpBalText) {
        this.mOpBalText = mOpBalText;
    }

    public String getmHeading() {

        return mHeading;
    }

    public void setmHeading(String mHeading) {
        this.mHeading = mHeading;
    }
}