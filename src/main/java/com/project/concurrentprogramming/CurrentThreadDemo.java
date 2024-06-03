package com.project.concurrentprogramming;

public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread current = Thread.currentThread();
        System.out.println("Current Thread: " + current);
        current.setName("My Thread");
        System.out.println("After name change: " + current);
        try {
            for(int n=5; n>0; n--){
                System.out.println(n);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e){
            System.out.println("Main thread interrupted");
        }
    }
}