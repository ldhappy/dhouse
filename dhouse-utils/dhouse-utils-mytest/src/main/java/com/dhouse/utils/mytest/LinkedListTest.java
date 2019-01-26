package com.dhouse.utils.mytest;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class LinkedListTest extends Thread{
    private Spliterator<String> list;

    /**
     * Allocates a new {@code Thread} object. This constructor has the same
     * effect as
     * {@code (null, null, name)}.
     *
     * @param name the name of the new thread
     */
    public LinkedListTest(String name, Spliterator<String> list) {
        super(name);
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"start");
        list.forEachRemaining(new Consumer<String>() {
            @Override
                public void accept(String t) {
                    System.out.println(Thread.currentThread().getName()+" === "+t);
                }
        });
        System.out.println(Thread.currentThread().getName()+"end");
    }

    public static void main(String[] args) {
        LinkedList<String> linkedListS = new LinkedList<String>();
        for(int i=1;i<21;i++){
            linkedListS.offer(i+"");
        }
        //java8新特性试验====Spliterator====分割迭代器
        Spliterator<String> list1 = linkedListS.spliterator().trySplit();
        Spliterator<String> list2 = list1.trySplit();
        Spliterator<String> list3 = list1.trySplit();
        Spliterator<String> list4 = list2.trySplit();
        LinkedListTest test1 = new LinkedListTest("test1",list1);
        LinkedListTest test2 = new LinkedListTest("test2",list2);
        LinkedListTest test3 = new LinkedListTest("test3",list3);
        LinkedListTest test4 = new LinkedListTest("test4",list4);
        test1.start();
        test2.start();
        test3.start();
        test4.start();
        //序列化测试
//        try {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
//            outputStream.writeObject(linkedListS);
//            ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
//            LinkedList linkedListR = (LinkedList) inputStream.readObject();
//            showList(linkedListR);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    private static void showList(List list) {
        for (Object o:list) {
            System.out.println(o);
        }
    }
}
