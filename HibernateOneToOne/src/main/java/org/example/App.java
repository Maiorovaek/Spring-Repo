package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class).addAnnotatedClass(Passport.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        try {
            session.beginTransaction();


//            Person person = new Person("Test person", 50);
//            Passport passport = new Passport( 124435353);
//            person.setPassport(passport);
//            session.save(person);

//            Person person = session.get(Person.class, 1);
//            System.out.println(person.getPassport());

//            Passport passport = session.get(Passport.class, 1);
//            System.out.println(passport.getPerson().getName());

//            Person person = session.get(Person.class, 1);
//            person.getPassport().setPassportNumber(4445);
//            System.out.println(person.getPassport().getPassportNumber());

//            Person person = session.get(Person.class, 1);
//            session.remove(person);

            session.getTransaction().commit();

        } finally {
            sessionFactory.close();
        }

    }
}
