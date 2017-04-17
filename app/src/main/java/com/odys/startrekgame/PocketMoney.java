package com.odys.startrekgame;

public class PocketMoney {

    private static int money = 0;


    public static int getMoney() {
        return money;
    }

    public static void addMoney(int m) {
        money += m;
    }

    public static void spendMoney(int m) {
        money -= m;
    }

    public static void setMoney(int m) {
        money = m;
    }
}
