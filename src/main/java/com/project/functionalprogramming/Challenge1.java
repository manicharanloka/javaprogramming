package com.project.functionalprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Challenge1 {
    static class Person {
        private final String name;
        private final Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }

    static class Car {
        private final String make;
        private final String color;
        private final Float price;

        public Car(String make, String color, Float price) {
            this.make = make;
            this.color = color;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "make='" + make + '\'' +
                    ", color='" + color + '\'' +
                    ", price=" + price +
                    '}';
        }

        public String getMake() {
            return make;
        }

        public String getColor() {
            return color;
        }

        public Float getPrice() {
            return price;
        }
    }

    static class Employee {
        private final String name;
        private final Integer age;
        private final String jobTitle;
        private final Float salary;

        public Employee(String name, Integer age, String jobTitle, Float salary) {
            this.name = name;
            this.age = age;
            this.jobTitle = jobTitle;
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", jobTitle='" + jobTitle + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public Float getSalary() {
            return salary;
        }
    }

    public static void main(String[] args) {
        Person[] peopleArr = {
                new Person("Brandon", 23),
                new Person("Hank", 43),
                new Person("Jenna", 33),
                new Person("Veronica", 56),
                new Person("Jack", 27),
        };
        List<Person> people = new ArrayList<>(Arrays.asList(peopleArr));
        List<String> names = people.stream().map((p)->p.getName()).collect(Collectors.toList());
        System.out.println(names);
        Car[] carsArr = {
                new Car("Chevy", "red", 45000f),
                new Car("Ford", "blue", 23000f),
                new Car("Toyota", "grey", 14000f),
                new Car("Lamborghini", "blue", 150000f),
                new Car("Renault", "blue", 150000f),
        };
        List<Car> cars = new ArrayList<>(Arrays.asList(carsArr));
        List<Car> blueCars = cars.stream().filter((c)->c.getColor().equals("blue")).collect(Collectors.toList());
        System.out.println(blueCars);
        Employee[] employeesArr = {
                new Employee("John", 34, "developer", 80000f),
                new Employee("Hannah", 24, "developer", 95000f),
                new Employee("Bart", 50, "sales executive", 100000f),
                new Employee("Sophie", 49, "construction worker", 40000f),
                new Employee("Darren", 38, "writer", 50000f),
                new Employee("Nancy", 29, "developer", 75000f),
        };
        List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));
        float sum = employees
                .stream()
                .map((e)->e.getSalary())
                .reduce(0f,(a,c)->a+c);
        System.out.println(sum);
        // Combining Functions
        float developerSalaries = employees
                .stream()
                .filter(e->e.getJobTitle().equals("developer"))
                .map(e->e.getSalary())
                .reduce(0f,(a,c)->a+c);
        long numOfDevelopers = employees
                .stream()
                .filter(e->e.getJobTitle().equals("developer"))
                .collect(Collectors.counting());
        float avgSalaryOfDevelopers = developerSalaries/numOfDevelopers;
        System.out.println(avgSalaryOfDevelopers);
        //Composition
        Function<Employee, String> getName = emp->emp.getName();
        Function<String, String> reverse = x->new StringBuilder(x).reverse().toString();
        Function<String, String> upperCase = x->x.toUpperCase();
        Function<Employee, String> reverseAndUpperCase = getName.andThen(reverse).andThen(upperCase);
        List<String> employeeNames = employees.stream()
                .map(reverseAndUpperCase)
                .collect(Collectors.toList());
        System.out.println(employeeNames);
    }
}
