package com.project.concurrentprogramming;

class Callme {
    synchronized void call(String msg){
        System.out.print("["+msg);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            System.out.println("Interrupted");
        }
        System.out.println("]");
    }
}
class Caller implements Runnable {
    String msg;
    Callme target;
    Thread t;
    public Caller(String message, Callme targ){
        this.msg=message;
        this.target=targ;
        t=new Thread(this);
    }
    @Override
    public void run(){
        target.call(msg);
    }
}
public class Synch {
    public static void main(String[] args) {
        Callme target = new Callme();
        Caller obj1 = new Caller("Hello", target);
        Caller obj2 = new Caller("Synchronized", target);
        Caller obj3 = new Caller("World", target);
        obj1.t.start();
        obj2.t.start();
        obj3.t.start();
        try {
            obj1.t.join();
            obj2.t.join();
            obj3.t.join();
        } catch (InterruptedException e){
            System.out.println("Interrupted");
        }
    }
}
