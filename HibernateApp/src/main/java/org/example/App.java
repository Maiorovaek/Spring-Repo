package org.example;


import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //по умолчанию читает файл hibernate.properties
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();
            //работает с java-сущностью Person
            //  List<Person> people = session.createQuery("FROM Person where name like 'T%'").getResultList();
            //  session.createQuery("update Person set name='Test' where age < 30").executeUpdate();
            session.createQuery("delete from Person where age< 20").executeUpdate();
            //   Person person1 = new Person("Test id name", 13);
            //   session.save(person1);
            //    System.out.println(person1.getId());

            //    Person person = session.get(Person.class, 2);
            //person.setName("new person name");  // изменить данные

            //           session.delete(person); // удаление

//            Person person1 = new Person("Anna", 20); //добавление нового
//            Person person2 = new Person("Tom", 34);
//            Person person3 = new Person("Den", 24);
//            Person person4 = new Person("Den", 15);
//
//            session.save(person1);
//            session.save(person2);
//            session.save(person3);
//            session.save(person4);

            //  Person person = session.get(Person.class, 1); // получить по индексу
            //  System.out.println(person.getName() + " " + person.getAge());
            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }


    }
}
