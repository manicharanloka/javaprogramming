package com.project.shallowclone;

class Address {
    String city;
}
class Employee implements Cloneable{
    int id;
    String name;
    Address address;
    public Employee (int id, String name) {
        System.out.println("Constructor called");
        this.id=id;
        this.name=name;
    }
    @Override
    public Employee clone() throws CloneNotSupportedException{
            return (Employee) super.clone();
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
public class ShallowCloning {
    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee(1,"John");
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
