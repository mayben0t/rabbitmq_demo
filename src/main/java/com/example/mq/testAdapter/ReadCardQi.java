package com.example.mq.testAdapter;

public class ReadCardQi implements Computer {
    CdCard cdCard;

    @Override
    public void readCard() {
        System.out.println("--------适配读卡器 read----------");
        cdCard.readCdCard();
        System.out.println("--------适配读卡器 read----------");
    }

    @Override
    public void writeCard() {
        System.out.println("--------适配读卡器 write----------");
        cdCard.writeCdCard();
        System.out.println("--------适配读卡器 write----------");
    }
}
