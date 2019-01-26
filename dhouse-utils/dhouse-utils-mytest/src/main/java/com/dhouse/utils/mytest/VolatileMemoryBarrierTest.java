package com.dhouse.utils.mytest;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * valotile是怎么实现其禁止重排序和修改内容对所有线程可见的
 * 是通过MemoryBarrier（cpu指令，内存栅栏）实现的
 * 指令的实现不体现在字节码上
 * 以下内容的字节码
 *
 public class com.dhouse.utils.mytest.VolatileMemoryBarrierTest {
 java.lang.Integer modCount;

 volatile java.lang.Integer vModCount;

 public com.dhouse.utils.mytest.VolatileMemoryBarrierTest();
 Code:
 0: aload_0
 1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 4: aload_0
 5: iconst_0
 6: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 9: putfield      #3                  // Field modCount:Ljava/lang/Integer;
 12: aload_0
 13: iconst_0
 14: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 17: putfield      #4                  // Field vModCount:Ljava/lang/Integer;
 20: return

 public java.lang.Integer getModCount();
 Code:
 0: aload_0
 1: getfield      #3                  // Field modCount:Ljava/lang/Integer;
 4: areturn

 public void setModCount(java.lang.Integer);
 Code:
 0: aload_0
 1: aload_1
 2: putfield      #3                  // Field modCount:Ljava/lang/Integer;
 5: return

 public java.lang.Integer getvModCount();
 Code:
 0: aload_0
 1: getfield      #4                  // Field vModCount:Ljava/lang/Integer;
 4: areturn

 public void setvModCount(java.lang.Integer);
 Code:
 0: aload_0
 1: aload_1
 2: putfield      #4                  // Field vModCount:Ljava/lang/Integer;
 5: return

 public static void main(java.lang.String[]);
 Code:
 0: new           #5                  // class com/dhouse/utils/mytest/VolatileMemoryBarrierTest
 3: dup
 4: invokespecial #6                  // Method "<init>":()V
 7: astore_1
 8: aload_1
 9: iconst_1
 10: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 13: invokevirtual #7                  // Method setModCount:(Ljava/lang/Integer;)V
 16: aload_1
 17: iconst_1
 18: invokestatic  #2                  // Method java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
 21: invokevirtual #8                  // Method setvModCount:(Ljava/lang/Integer;)V
 24: return
 }

 *
 */
public class VolatileMemoryBarrierTest {
    Integer modCount = 0;
    volatile Integer vModCount = 0;

    public Integer getModCount() {
        return modCount;
    }

    public void setModCount(Integer modCount) {
        this.modCount = modCount;
    }

    public Integer getvModCount() {
        return vModCount;
    }

    public void setvModCount(Integer vModCount) {
        this.vModCount = vModCount;
    }

    public static void main(String[] args) {
        VolatileMemoryBarrierTest test = new VolatileMemoryBarrierTest();
        test.setModCount(1);
        test.setvModCount(1);
    }
}
