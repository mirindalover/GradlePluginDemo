package com.mlc.plugin.simple.builder

public class Person {

    String name

    int age

    @Override
    String toString() {
        return "i am $name, $age years old"
    }
}