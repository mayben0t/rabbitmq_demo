package com.example.mq.testAdapter;

public class AnotherReadCardQi extends CdCard implements Computer  {
    @Override
    public void readCard() {

    }

    @Override
    public void writeCard() {

    }

//    public AnotherReadCardQi(String cdName) {
//        super(cdName);
//    }

    public AnotherReadCardQi(){
        super("zz");
    }

    @Override
    public void writeCdCard() {
        super.writeCdCard();
    }

    @Override
    public void readCdCard() {
        super.readCdCard();
    }

    @Override
    public String getCdName() {
        return super.getCdName();
    }

    @Override
    public void setCdName(String cdName) {
        super.setCdName(cdName);
    }
}
