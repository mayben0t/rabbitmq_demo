package com.example.mq.testAdapter;

public class CdCard {
    private String cdName;

    public CdCard(String cdName) {
        this.cdName = cdName;
    }

    public void writeCdCard(){
        System.out.println("cd卡 ["+cdName+"] 写入");
    }
    public void readCdCard(){
        System.out.println("cd卡["+cdName+"]读取");
    }

    public String getCdName() {
        return cdName;
    }

    public void setCdName(String cdName) {
        this.cdName = cdName;
    }
}
