package com.example.mq.testAdapter;

public class TestThinkPad implements Computer{
    ReadCardQi readCardQi;

    @Override
    public void readCard() {
        readCardQi.readCard();
    }

    @Override
    public void writeCard() {
        readCardQi.writeCard();
    }

    public static void main(String[] args) {
        TestThinkPad testThinkPad = new TestThinkPad();
        ReadCardQi readCardQi = new ReadCardQi();
        CdCard cdCard = new CdCard("闪电cd卡");
        readCardQi.cdCard = cdCard;
        testThinkPad.readCardQi = readCardQi;
        testThinkPad.readCardQi.writeCard();
        testThinkPad.readCardQi.readCard();
    }
}
