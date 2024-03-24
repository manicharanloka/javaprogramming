package com.project.functionalprogramming;

import java.util.function.Function;
import java.util.function.BiFunction;

public class FirstClassFunctions {
    @FunctionalInterface
    public interface TriFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }
    @FunctionalInterface
    public interface NoArgFunction<R>{
        R apply();
    }
    protected static class MyMath {
        public static int triple(int x){
            return x*3;
        }
        public static int add(int x, int y){
            return x+y;
        }
        public static int subtract(int x, int y){
            return x-y;
        }
        public static int combine2And3(BiFunction<Integer, Integer, Integer> combineFunction){
            return combineFunction.apply(2,3);
        }
        public static Function<Integer, Integer> createMultiplier(int y){
            return (x)->x*y;
        }
    }
    protected static class Person {
        private String name;
        private int age;
        public Person(String name, int age){
            this.name=name;
            this.age=age;
        }
        @Override
        public String toString(){
            return "name "+this.name+" age "+this.age;
        }
    }
    protected static class DataLoader {
        public final NoArgFunction<Person> loadPerson;
        public DataLoader(boolean isDevelopment){
            this.loadPerson = isDevelopment?this::loadPersonFake:this::loadPersonReal;
        }
        private Person loadPersonReal(){
            return new Person("Real Person",40);
        }
        private Person loadPersonFake(){
            return new Person("Fake Person", -1);
        }
    }
    public static void main(String[] args) {
        Function<Integer,Integer> myTriple = MyMath::triple; //Method reference: returns function as an object
        System.out.println("Function from other class " + myTriple.apply(9)); //Every function has an apply method which calls the function with given arguments
        Function<Integer,Integer> myTripleLambda = (x)->x*3; //Creating a function from scratch using lambda
        System.out.println("Function from Lambda "+myTripleLambda.apply(9));
        Function<Integer,Integer> absoluteValue = (x)->x<0?-x:x;
        System.out.println("Absolute value of -100 is " + absoluteValue.apply(-100));
        BiFunction<Integer,Integer,Integer> addFunction = MyMath::add;
        System.out.println("BiFunction from other class " + addFunction.apply(12,13));
        BiFunction<Integer,Integer,Integer> addFunctionLambda = (x,y)->x+y;
        System.out.println("BiFunction from lambda " + addFunctionLambda.apply(12,13));
        TriFunction<Integer,Integer,Integer,Integer> triFunction = (x,y,z)->x+y+z;
        System.out.println("TriFunction " + triFunction.apply(1,2,3));
        NoArgFunction<String> noArgFunction = ()->"Hello";
        System.out.println("NoArgFunction "+noArgFunction.apply());
        System.out.println("In development: "+new DataLoader(true).loadPerson.apply());
        System.out.println("Not in development: "+new DataLoader(false).loadPerson.apply());
        System.out.println("Add numbers "+MyMath.combine2And3(MyMath::add)); //passing a function as an argument
        System.out.println("Subtract numbers "+MyMath.combine2And3(MyMath::subtract));
        System.out.println("Using Lambda expression "+MyMath.combine2And3((x,y)->2*(x+y)));
        NoArgFunction<NoArgFunction<String>> createGreeter = ()->()->"Hello function!"; //function returning another function
        NoArgFunction<String> greeter = createGreeter.apply(); //this returns a no-arg function which returns a string
        System.out.println(greeter.apply());
        Function<Integer, Integer> timesTwo = MyMath.createMultiplier(2);
        System.out.println("Returning function "+timesTwo.apply(3));
        Function<Integer, Integer> timesThree = MyMath.createMultiplier(3);
        System.out.println("Returning function "+timesThree.apply(3));
        NoArgFunction<NoArgFunction<String>> welcomeFunction = ()->{
                final String name = "Mani";
                return ()->"Hello "+name;
        };
        NoArgFunction<String> welcomeGreeter = welcomeFunction.apply();
        System.out.println(welcomeGreeter.apply());
        BiFunction<Float, Float, Float> divide = (x,y)->x/y;
        Function<BiFunction<Float, Float, Float>, BiFunction<Float, Float, Float>> divideByZeroCheck =
                (func)->(x,y)->{
                    if(y==0f){
                        System.out.println("Cannot divide by Zero");
                        return 0f;
                    }
                    return func.apply(x,y);
                };
        BiFunction<Float, Float, Float> safeDivide = divideByZeroCheck.apply(divide);
        System.out.println(safeDivide.apply(10f,0f));
    }
}
