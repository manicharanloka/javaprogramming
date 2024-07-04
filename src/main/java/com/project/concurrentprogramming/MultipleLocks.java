package com.project.concurrentprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Worker {
    Random random = new Random();
    Object lock1 = new Object();
    Object lock2 = new Object();
    public List<Integer> list1 = new ArrayList<>();
    public List<Integer> list2 = new ArrayList<>();
    public void one() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list1.add(random.nextInt(100));
        }
    }
    public void two() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list2.add(random.nextInt(100));
        }
    }
    public void process(){
        for(int i=0; i<500; i++) {
            one();
            two();
        }
    }
}
public class MultipleLocks {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(()-> worker.process());
        Thread t2 = new Thread(()->worker.process());
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();
        }catch (InterruptedException e) {
            System.out.println("Exception "+e);
        }
        long end = System.currentTimeMillis();
        System.out.println("list1: "+worker.list1.size()+" list2: "+worker.list2.size());
        System.out.println("Time taken: "+(end-start));
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(()-> System.out.println("In here"));
    }
}
