package com.project.functionalprogramming;

import java.util.*;
import java.util.stream.Collectors;

public class Challenge2 {
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
        Employee[] employeesArr = {
                new Employee("John", 34, "developer", 80000f),
                new Employee("Hannah", 24, "developer", 95000f),
                new Employee("Bart", 50, "sales executive", 100000f),
                new Employee("Sophie", 49, "construction worker", 40000f),
                new Employee("Darren", 38, "writer", 50000f),
                new Employee("Nancy", 29, "developer", 75000f),
        };
        List<Employee> employees = new ArrayList<>(Arrays.asList(employeesArr));
        Map<String, Float> answer = employees
                .stream()
                .collect(Collectors.groupingBy(e->e.getJobTitle()))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e->e.getKey(),
                        e-> e.getValue()
                                .stream()
                                .map(emp -> emp.getSalary())
                                .reduce(0f, (a, c) -> a + c) /
                                e.getValue().size()
                ));
        System.out.println("Average salary by Department Declarative way "+answer);
        // Traditional imperative way of doing it
        Map<String, List<Employee>> employeesByDepartment = employees
                .parallelStream()
                .collect(Collectors.groupingBy((e)->e.getJobTitle()));
        System.out.println(employeesByDepartment);
        Map<String, Float> avgSalaryByDepartment = new HashMap<>();
        for(Map.Entry<String, List<Employee>> entry:employeesByDepartment.entrySet()){
            String key = entry.getKey();
            List<Employee> employeeList = entry.getValue();
            float sum = employeeList
                    .stream()
                    .map(e->e.getSalary())
                    .reduce(0f,(a,c)->a+c);
            long count = employeeList.stream().count();
            float avg = sum/count;
            avgSalaryByDepartment.put(key,avg);
        }
        System.out.println("Avg Salary by Department Imperative way "+avgSalaryByDepartment);
    }
}
