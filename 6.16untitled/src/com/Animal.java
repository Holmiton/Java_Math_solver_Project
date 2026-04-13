package com;

public class Animal {
    String name;
    int age;

    public Animal() {

    }


    public Animal(String name, int age){
        this.name = name;
        this.age = age;
    }

    public void show(){
        System.out.println(this.name+"\t"+this.age);

    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
