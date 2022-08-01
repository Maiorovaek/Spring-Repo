package org.example;

import org.example.model.Actor;
import org.example.model.Movie;
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
        Configuration configuration = new Configuration().addAnnotatedClass(Movie.class).addAnnotatedClass(Actor.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (sessionFactory) {
            Session session = sessionFactory.getCurrentSession();
            session.beginTransaction();


//            Movie movie = new Movie("Pulp fiction", 1994);
//            Actor actor1 = new Actor("harvey Keitel", 81);
//            Actor actor2 = new Actor("Samuel L. Jackson", 72);
//
//            movie.setActors(new ArrayList<>(List.of(actor1, actor2)));//неизменяемый список
//
//
//            actor1.setMovies(new ArrayList<>(Collections.singletonList(movie))); //создает список из одного элемента
//            actor2.setMovies(new ArrayList<>(Collections.singletonList(movie))); //создает список из одного элемента
//
//            session.save(movie);
//            session.save(actor1);
//            session.save(actor2);


//            Movie movie = session.get(Movie.class, 1);
//            System.out.println(movie.getActors());
//
//            Actor actor = session.get(Actor.class, 2);
//            System.out.println(actor.getMovies());

//            Movie movie = new Movie("Reservoir dogs", 1992);
//            Actor actor = session.get(Actor.class, 1);
//            movie.setActors(new ArrayList<>(Collections.singletonList(actor)));
//            actor.getMovies().add(movie);
//            session.save(movie);


            Actor actor = session.get(Actor.class, 2);
            System.out.println(actor.getMovies());

            Movie movieToRemove = actor.getMovies().get(0);

            actor.getMovies().remove(0);
            // нужно переопределить equals и hashcode в классах для метода remove
            movieToRemove.getActors().remove(actor);

            session.getTransaction().commit();
        }
    }
}
