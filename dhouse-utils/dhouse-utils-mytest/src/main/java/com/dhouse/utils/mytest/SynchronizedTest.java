package com.dhouse.utils.mytest;

/**
 * Synchronized的重入机制
 * 以下为测试内容对应字节码（去除打印信息）
 public class com.dhouse.utils.mytest.SynchronizedTest {
 public com.dhouse.utils.mytest.SynchronizedTest();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: return

 public void synchronizedOuter();
 Code:
 0: aload_0
 1: dup
 2: astore_1
 3: monitorenter
 4: aload_0
 5: dup
 6: astore_2
 7: monitorenter
 8: aload_0
 9: invokespecial #2                  // Method synchronizedInner:()V
 12: aload_2
 13: monitorexit
 14: goto          22
 17: astore_3
 18: aload_2
 19: monitorexit
 20: aload_3
 21: athrow
 22: aload_1
 23: monitorexit
 24: goto          34
 27: astore        4
 29: aload_1
 30: monitorexit
 31: aload         4
 33: athrow
 34: return
 Exception table:
 from    to  target type
 8    14    17   any
 17    20    17   any
 4    24    27   any
 27    31    27   any

 private synchronized void synchronizedInner();
 Code:
 0: aload_0
 1: dup
 2: astore_1
 3: monitorenter
 4: aload_0
 5: invokevirtual #3                  // Method java/lang/Object.notifyAll:()V
 8: aload_1
 9: monitorexit
 10: goto          18
 13: astore_2
 14: aload_1
 15: monitorexit
 16: aload_2
 17: athrow
 18: return
 Exception table:
 from    to  target type
 4    10    13   any
 13    16    13   any

 public void synchronizedWaitOuter();
 Code:
 0: aload_0
 1: dup
 2: astore_1
 3: monitorenter
 4: aload_0
 5: invokespecial #4                  // Method synchronizedWaitInner:()V
 8: aload_1
 9: monitorexit
 10: goto          18
 13: astore_2
 14: aload_1
 15: monitorexit
 16: aload_2
 17: athrow
 18: return
 Exception table:
 from    to  target type
 4    10    13   any
 13    16    13   any

 private synchronized void synchronizedWaitInner();
 Code:
 0: aload_0
 1: invokevirtual #5                  // Method java/lang/Object.wait:()V
 4: goto          12
 7: astore_1
 8: aload_1
 9: invokevirtual #7                  // Method java/lang/InterruptedException.printStackTrace:()V
 12: return
 Exception table:
 from    to  target type
 0     4     7   Class java/lang/InterruptedException

 public static void main(java.lang.String[]) throws java.lang.InterruptedException;
 Code:
 0: new           #8                  // class com/dhouse/utils/mytest/SynchronizedTest
 3: dup
 4: invokespecial #9                  // Method "<init>":()V
 7: astore_1
 8: new           #10                 // class com/dhouse/utils/mytest/SynchronizedTest$1
 11: dup
 12: aload_1
 13: invokespecial #11                 // Method com/dhouse/utils/mytest/SynchronizedTest$1."<init>":(Lcom/dhouse/utils/mytest/SynchronizedTest;)V
 16: astore_2
 17: new           #12                 // class com/dhouse/utils/mytest/SynchronizedTest$2
 20: dup
 21: aload_1
 22: invokespecial #13                 // Method com/dhouse/utils/mytest/SynchronizedTest$2."<init>":(Lcom/dhouse/utils/mytest/SynchronizedTest;)V
 25: astore_3
 26: aload_2
 27: invokevirtual #14                 // Method java/lang/Thread.start:()V
 30: ldc2_w        #15                 // long 100l
 33: invokestatic  #17                 // Method java/lang/Thread.sleep:(J)V
 36: aload_3
 37: invokevirtual #14                 // Method java/lang/Thread.start:()V
 40: return
 }
 */
public class SynchronizedTest {
    public void synchronizedOuter(){
        //System.out.println("synchronizedOuter start");
        synchronized (this){
            //System.out.println("synchronizedOuter lock1");
            synchronized (this){
                //System.out.println("synchronizedOuter lock2");
                synchronizedInner();
            }
        }
        //System.out.println("synchronizedOuter unlock");
        //System.out.println("synchronizedOuter end");
    }

    private synchronized void synchronizedInner() {
        //System.out.println("synchronizedInner start");
        synchronized (this){
            //System.out.println("synchronizedInner lock");
            //System.out.println("synchronizedInner notifyAll");
            notifyAll();
        }
        //System.out.println("synchronizedInner unlock");
        //System.out.println("synchronizedInner end");
    }

    public void synchronizedWaitOuter(){
        //System.out.println("synchronizedWaitOuter start");
        synchronized (this){
            //System.out.println("synchronizedWaitOuter lock");
            synchronizedWaitInner();
        }
        //System.out.println("synchronizedWaitOuter unlock");
        //System.out.println("synchronizedWaitOuter end");
    }

    private synchronized void synchronizedWaitInner() {
        //System.out.println("synchronizedWaitInner start");
        try {
            //System.out.println("synchronizedWaitInner wait");
            wait();
            //System.out.println("synchronizedWaitInner wait finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("synchronizedWaitInner end");
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest test = new SynchronizedTest();
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                test.synchronizedWaitOuter();
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                test.synchronizedOuter();

            }
        };
        thread1.start();
        Thread.sleep(100);
        thread2.start();

    }

}
