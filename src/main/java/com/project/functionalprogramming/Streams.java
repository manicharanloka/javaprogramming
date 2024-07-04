package com.project.functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;
import java.util.function.*;

public class Streams {
    public static void main(String[] args) {
        // Map
        int[] intArray = {1,2,3,4,5,6,7,8,9,10};
        // Get min/max using streams
        System.out.println(Arrays.stream(intArray).min().getAsInt());
        // Convert int[] array to List<Integer>
        List<Integer> integerList = Arrays.stream(intArray).boxed().toList();
        List<Integer> timesTwoResult = integerList.stream().map(i->i*2).collect(Collectors.toList()); //creating a function on the fly
        System.out.println(timesTwoResult);
        Function<Integer, Integer> timesThree = x->x*3;
        List<Integer> timesThreeResult = integerList.stream().map(timesThree).collect(Collectors.toList()); //referring a function
        System.out.println(timesThreeResult);
        // Filter
        List<Integer> odds = timesThreeResult.stream().filter(x->x%2!=0).collect(Collectors.toList());
        System.out.println(odds);
        Predicate<Integer> isEven = x->x%2==0;
        List<Integer> evens = timesThreeResult.stream().filter(isEven).collect(Collectors.toList());
        System.out.println(evens);
        String[] words = {"functional", "programming", "is", "really", "really", "cool"};
        Function<Integer,Predicate<String>> isLongerThanLength = (len)->(str)->str.length()>len;
        Predicate<String> isLongerThan3 = isLongerThanLength.apply(3);
        List<String> longerWords = Arrays.stream(words).filter(isLongerThan3).collect(Collectors.toList());
        System.out.println(longerWords);
        // Reduce
        BinaryOperator<Integer> reducer = (a,c)->a+c;
        int sum = integerList.stream().reduce(0,reducer);
        System.out.println("Sum from reducer "+sum);
        System.out.println("Sum from reducer lambda "+integerList.stream().reduce(0,(a,c)->a+c));
        //Collect
        List<String> wordList = new ArrayList<>(Arrays.asList(words));
        Set<String> stringSet = wordList.stream().collect(Collectors.toSet());
        System.out.println(stringSet);
        String sentence = wordList.stream().collect(Collectors.joining(", "));
        System.out.println(sentence);
        long count = wordList.stream().filter(isLongerThanLength.apply(3)).collect(Collectors.counting());
        System.out.println(count);
        Map<Integer, List<String>> mapByLength = wordList.stream().collect(Collectors.groupingBy((word)->word.length()));
        System.out.println(mapByLength);
        Map<Boolean, List<String>> partitionMap = wordList.stream().collect(Collectors.partitioningBy((word)->word.length()>5));
        System.out.println(partitionMap);
        //Parallel Streams
        List<String> processedWords = wordList
                .parallelStream()
                .map((word)->{
                    System.out.println("uppercasing word: "+word);
                    return word.toUpperCase();
                })
                .map((word)->{
                    System.out.println("adding ! for word: "+word);
                    return word+"!";
                })
                .collect(Collectors.toList());
        System.out.println(processedWords);
    }
}
