package com.project.functionalprogramming;

import java.util.function.BiFunction;
import java.util.function.Function;

public class AdvancedFunctions {
    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
    public static void countDown(int x){
        if(x<0){
            System.out.println("Done!");
            return;
        }
        System.out.println(x);
        countDown(x-1);
    }
    public static void countUp(int x, int y){
        if(x>y){
            System.out.println("Done!");
            return;
        }
        System.out.println(x);
        countUp(x+1,y);
    }
    public static int fibonacci(int x){
        if(x==1 || x==2) return 1;
        return fibonacci(x-1)+fibonacci(x-2);
    }

    public static void main(String[] args) {
        // Partial Functions
        TriFunction<Integer, Integer, Integer, Integer> add = (x,y,z)->x+y+z;
        Function<Integer, BiFunction<Integer, Integer, Integer>> addPartial = x->(y,z)-> add.apply(x,y,z);
        BiFunction<Integer, Integer, Integer> add5 = addPartial.apply(5);
        System.out.println(add5.apply(6,7));
        BiFunction<Integer, Integer, Function<Integer, Integer>> addTwoPartial = (x,y)->(z)->add.apply(x,y,z);
        Function<Integer, Integer> add5And6 = addTwoPartial.apply(5,6);
        System.out.println(add5And6.apply(7));
        Function<Integer, Function<Integer,Function<Integer,Integer>>> addOnePartial= (x)->(y)->(z)->add.apply(x,y,z); //This specific case where we pass one argument at a time is called currying
        Function<Integer, Function<Integer, Integer>> addFive = addOnePartial.apply(5);
        Function<Integer, Integer> addFiveAndSix = addFive.apply(6);
        System.out.println(addFiveAndSix.apply(7));
        countDown(10);
        countUp(0,10);
        Function<Integer, Integer> timesTwo = x->x*2;
        Function<Integer, Integer> minusOne = x->x-1;
        Function<Integer, Integer> timesTwoMinusOne = minusOne.compose(timesTwo); //does it in reverse order
        System.out.println(timesTwoMinusOne.apply(5));
        Function<Integer, Integer> minusOneTimesTwo = timesTwo.compose(minusOne);
        System.out.println(minusOneTimesTwo.apply(5));
        Function<Integer, Integer> combine = timesTwo.andThen(minusOne); //works sequentially unlike compose
        System.out.println(combine.apply(5));
        System.out.println("The 5th fibonacci number is: "+fibonacci(5));
        System.out.println("The 10th fibonacci number is: "+fibonacci(10));
        System.out.println("The 17th fibonacci number is: "+fibonacci(17));
    }
}
