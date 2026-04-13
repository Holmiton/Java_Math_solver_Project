package com;

public class Main {
    public static void main(String[] args){
        B b = new B();
        System.out.println(b.count);
        b.method();

        A a = b;
        System.out.println(a == b);
        System.out.println(a.count);
        a.method();

    }
}



class A{
    
}

class B extends A {
    int count = 20;

    @Override
    public void method() {
        System.out.println(this.count);
    }
}