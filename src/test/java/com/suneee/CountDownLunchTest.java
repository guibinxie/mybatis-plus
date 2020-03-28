package com.suneee;

import java.util.concurrent.CountDownLatch;

public class CountDownLunchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0; i < 9; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " 运行");
                    try {
                        Thread.sleep(3000);
                        System.out.println(Thread.currentThread().getName() + " 执行任务结束");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        latch.countDown();
                    }
                }
            }).start();
        }

        System.out.println("等待子线程运行结束");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程运行结束");

    }
}
