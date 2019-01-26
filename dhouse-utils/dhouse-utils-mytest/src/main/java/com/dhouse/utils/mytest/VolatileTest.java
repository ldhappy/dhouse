package com.dhouse.utils.mytest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 想测试一下valotile是怎么通知其他线程改动的，但是内存刷新其实很快，看不见
 */
public class VolatileTest {
    public Integer modCount = 0;
    public volatile Integer vModCount = 0;

    public static void main(String[] args) {
        VolatileTest test = new VolatileTest();
        String sp = "======";
        CyclicBarrier barrierRead = new CyclicBarrier(2);
        CyclicBarrier barrierWrite = new CyclicBarrier(2);
        int count = 1000000;
        Thread threadRead = new Thread("read") {
            @Override
            public void run() {
                try {
                    //System.out.println("读等1开始");
                    barrierRead.await();
                    //System.out.println("读等1结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < count; i++) {
                    try {
                        //System.out.println("读线程---写等---第"+i+"次");
                        barrierWrite.await();
                        //System.out.println("读线程---写等完成---开始读---第"+i+"次");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    int m = test.modCount;
                    test.modCount++;
                    int vm = test.vModCount;
                    test.vModCount++;
                    if(m != vm){
                        System.out.println(Thread.currentThread().getName() +sp+"i:"+i+ sp + "m:" + m + sp + "vm" + vm);
                        System.out.println(Thread.currentThread().getName() +sp+"i:"+i+ sp + "modCount:" + test.modCount + sp + "vModCount" + test.vModCount);
                    }
                    if(i+1 != count){
                        try {
                            //System.out.println("读线程---读等开始---第"+i+"次");
                            barrierRead.await();
                            //System.out.println("读线程---读等结束---第"+i+"次");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };

        Thread threadWrite = new Thread("write") {
            @Override
            public void run() {

                for (int i = 0; i < count; i++) {
                    try {
                        //System.out.println("写线程---读等---第"+i+"次");
                        barrierRead.await();
                        //System.out.println("写线程---读等完成---开始写---第"+i+"次");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    test.modCount++;
                    test.vModCount++;
                    try {
                        //System.out.println("写线程---写等开始---第"+i+"次");
                        barrierWrite.await();
                        //System.out.println("写线程---写等结束---第"+i+"次");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        threadRead.start();
        threadWrite.start();
        try {
            threadRead.join();
            threadWrite.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(test.modCount);
        System.out.println(test.vModCount);

    }

    private void read() {
        System.out.println("read-begin>>>param:" + "");
        String sp = "======";
        System.out.println(Thread.currentThread().getName() + sp + "modCount:" + modCount + sp + "vModCount" + vModCount);
        //System.out.println(Thread.currentThread().getName() + sp + "modCount:" + modCount );
        System.out.println("read-end<<<return:");
    }

    private void write() {
        System.out.println("write-begin>>>param:" + "");
        String sp = "======";
        modCount++;
        vModCount++;
        System.out.println(Thread.currentThread().getName() + sp + "modCount:" + modCount + sp + "vModCount" + vModCount);
        //System.out.println(Thread.currentThread().getName() + sp + "modCount:" + modCount );
        System.out.println("write-end<<<return:");
    }
}
