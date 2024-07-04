package com.project.deepclone;

class Address {
    String city;
}
class Employee implements Cloneable{
    int id;
    String name;
    Address address;
    @Override
    public Employee clone() throws CloneNotSupportedException{
            Employee clone = (Employee) super.clone();
            clone.address=new Address();
            clone.address.city=address.city;
            return clone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address.city +
                '}';
    }
}
public class DeepCloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee();
        employee.id=1;
        employee.name="John";
        Address address = new Address();
        address.city="Boston";
        employee.address=address;
        System.out.println("Employee: " + employee);
        Employee cloneEmployee = employee.clone();
        System.out.println("CloneEmployee: " + cloneEmployee);
        cloneEmployee.address.city="San Jose";
        System.out.println("After modifying Clone Employee's reference variable");
        System.out.println("Employee: " + employee);
        System.out.println("CloneEmployee: " + cloneEmployee);
    }
}
