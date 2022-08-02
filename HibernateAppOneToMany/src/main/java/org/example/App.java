package org.example;


import org.example.model.Item;
import org.example.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();

//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//            System.out.println(items);


//            Item item = session.get(Item.class, 5);
//            System.out.println(item);
//            Person person = item.getPerson();
//            System.out.println(person);


//            Person person = session.get(Person.class, 2);
//            Item newItem = new Item("Item from Hibernate", person);
//            person.getItems().add(newItem); // hibernate кеширует и может выдавать старый спписок товаров - задание отношение с двух сторон --- не влияет на sql запросы и базу данных
//            session.save(newItem);

//            Person person = new Person("Test person", 30);
//            Item newItem = new Item("Item from Hibernate 2", person);
//            person.setItems(new ArrayList<Item>(Collections.singletonList(newItem)));
//            session.save(person);
//            session.save(newItem);

//
//            Person person = session.get(Person.class, 3);
//            List<Item> items = person.getItems();
//            //SQL
//            for(Item item : items)
//                session.remove(item);
//            // не пораждает SQL но делает кеш верным
//            person.getItems().clear();


//            Person person = session.get(Person.class, 2);
//            //SQL
//            session.remove(person);
//            //правильное состояние hibernate кэш
//            person.getItems().forEach(i -> i.setPerson(null));


//            Person person = session.get(Person.class, 4);
//            Item item = session.get(Item.class, 1);
//            //правильное состояние hibernate кэш
//            item.getPerson().getItems().remove(item);
//            //SQL
//            item.setPerson(person);
//            //правильное состояние hibernate кэш
//            person.getItems().add(item);


////Cascading
//            Person person = new Person("test cascading 2", 22);
//
//            person.addItem(new Item("Item 1"));
//            person.addItem(new Item("Item 2"));
//            person.addItem(new Item("item 3"));
//         //   person.setItems(new ArrayList<>(Collections.singletonList(item)));
//            session.save(person);

            //session.persist(item) // hibernate сделает за нас из за persistance


//            //LASZY/EAGER loading
            Person person = session.get(Person.class, 1);
            System.out.println("Getting person");
            System.out.println(person);
//            System.out.println(person.getItems());
            //Getting binding entities(Lazy)
//            System.out.println(person.getItems());
            //      Hibernate.initialize(person.getItems());// подгружаем связанные ленивые сущности

            session.getTransaction().commit();

//            Item item = session.get(Item.class,1);
//            System.out.println("Getting item");
//
//            System.out.println(item.getPerson());
            //открываем сессию  и транзакцию ещё раз
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            System.out.println("Внутри Второй транзакции");
        //    person = (Person) session.merge(person);

            // Hibernate.initialize(person.getItems());
           List<Item> items =  session.createQuery("select i from Item i where i.person.id=:personId", Item.class)
                   .setParameter("personId", person.getId()).getResultList();
            System.out.println(items);

            session.getTransaction().commit();
            System.out.println("Not second session");
//            System.out.println(person.getItems());
            //person.getItems(); // здесь работать не будет, т.к. hibernate тут уже не работает
        } finally {
            sessionFactory.close();
        }
    }


}
