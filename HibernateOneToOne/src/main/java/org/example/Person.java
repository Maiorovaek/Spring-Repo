package org.example;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "person")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Passport passport;

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

    public Passport getPassport() {
        return passport;
    }
// с двух сторон назначается связь
    public void setPassport(Passport passport) {
        this.passport = passport;

        passport.setPerson(this);
    }

    public String toString() {
        return id + " " + name + " " + age;
    }
}
