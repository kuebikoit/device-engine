package com.kuebikoit.deviceengine;

import com.kuebikoit.deviceengine.service.DeviceService;

public class TstCal {

    private int num;

    public TstCal() {
    }

    public void test() {
        System.out.println("Test");
    }

    public TstCal(int num1) {
        this.num = num1;
    }

    public static void main(String[] args) {
        Tst tst = new Tst();

        tst.jpt();
        tst.print("ghh");
    }
}
