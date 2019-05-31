package com.example.mq.testAdapter;

import java.math.BigDecimal;

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


    public static void main(String[] args) {
        BigDecimal b = new BigDecimal("124.5");
        b.setScale(0,BigDecimal.ROUND_HALF_EVEN);
        System.out.println(b);
        System.out.println(b.setScale(0,BigDecimal.ROUND_HALF_UP));
        System.out.println(b.setScale(0,BigDecimal.ROUND_HALF_EVEN));
    }
}
