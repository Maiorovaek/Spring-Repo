package org.example.model;


import javax.persistence.*;

@Entity
@Table(name = "Person")
public class Person {

    @Id //как первичный ключ
    @Column(name = "id")
    // говорим чтобы не использовал hibernate, автоматически генерируется postgres\ом
    @GeneratedValue(strategy = GenerationType.IDENTITY)
// @GeneratedValue(strategy = GenerationType.SEQUENCE) - не для постгрес
// @GeneratedValue(strategy = GenerationType.TABLE) - отдельная таблица дял генерации id

    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person( String name, int age) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
