package org.vaadin.diego;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Person {
    private String name;
    private int age;

    public void setAge(int age) {
        this.age = age;
    }


    public int getAge() {
        return age;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {

    }

    public static Set<Person> getPeople(){
        Set<Person> people = new HashSet<>();
        Person p;
        for ( int i = 0; i < 25; i++){
            p = new Person();
            p.setAge(i);
            p.setName("Name " + i);
            people.add(p);
        }
        return people;
    }
}
