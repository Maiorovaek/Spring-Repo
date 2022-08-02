package org.example.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "person")

public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;


    @Column(name = "age")
    private int age;



    //cascading
  //  @OneToMany(mappedBy = "person",cascade = CascadeType.PERSIST)
  @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
  //save теперь будет каскадироваться
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE )
    private List<Item> items;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String toString() {
        return id + " " + name + " " + age;
    }

    public void addItem(Item item){
        if(this.items == null)
            this.items = new ArrayList<>();

        this.items.add(item);
        item.setPerson(this);

    }
}
