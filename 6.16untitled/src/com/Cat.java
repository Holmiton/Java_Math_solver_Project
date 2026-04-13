package com;

public class Cat extends Animal{
    String name;
    int age;

    public Cat(String name, int age) {
        super(name, age);
    }


    public void shut(){
        System.out.println("moew");
    }
}
