package com.dhouse.utils.mytest;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 */
public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        MyThread thread1 = new MyThread("thread1",lock,null);
        MyThread thread2 = new MyThread("thread2",lock,thread1);
        MyThread thread3 = new MyThread("thread3",lock,Thread.currentThread());

        thread1.start();
        thread2.start();
        thread3.start();
//        Thread.yield();
//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            System.out.println("主线程等待"+thread1.getName()+"优先执行");
            thread1.join(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //thread1.interrupt();

        thread2.interrupt();
        thread3.interrupt();
        System.out.println("主线程结束");
    }
}
class MyThread extends Thread{
    private Lock lock;
    private Thread preThread;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as {@linkplain #Thread(ThreadGroup, Runnable, String) Thread}
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public MyThread(String name, Lock lock, Thread preThread) {
        super(name);
        this.lock = lock;
        this.preThread = preThread;
    }

    @Override
    public void run() {
        System.out.println(getName()+"开始");
        System.out.println(getName()+"获取锁前的状态"+this.isInterrupted());
        try {


            lock.lockInterruptibly();
            if(preThread != null){
                System.out.println(getName()+"等待"+preThread.getName()+"优先执行");
                preThread.join(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(getName()+"获得锁");
        try {
            for(int i=0;i<10000;i++){

            }
            System.out.println(getName()+"运行中");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
            System.out.println(getName()+getName()+"解锁");
        }
        System.out.println(getName()+"结束");
    }
}