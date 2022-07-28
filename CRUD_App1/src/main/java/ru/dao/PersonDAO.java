package ru.dao;

import org.springframework.stereotype.Component;
import ru.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

//    private List<Person> people;
    private static int PEOPLE_COUNT = 0;
    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1234";

    private static Connection connection;

    static {
        try { // удостоверились что драйвер загружен в опреативную память и им можем пользоваться
            //в новых версиях уже не требуется (проверка автоматически)
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

//
//    {
//        people = new ArrayList<>();
//        people.add(new Person(++PEOPLE_COUNT, "Liza",20,"liza@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Anna",13,"anna@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Mike",40,"mike@gmail.com"));
//        people.add(new Person(++PEOPLE_COUNT, "Den",23,"den@gmail.com"));
//    }

    public List<Person> index() {
        // return people;
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //    return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return person;
    }

    public void save(Person person) {
        try {
//            Statement statement = connection.createStatement();
//            String SQL = "INSERT INTO Person VALUES(" + 1 + ",'" + person.getName() +
//                    "'," + person.getAge() + ",'" + person.getEmail() + "')";
//
//            statement.executeUpdate(SQL);
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO Person VALUES(1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

    }

    public void update(int id, Person updatePerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        Person personToBeUpdate = show(id);
//        personToBeUpdate.setName(updatePerson.getName());
//        personToBeUpdate.setAge(updatePerson.getAge());
//        personToBeUpdate.setEmail(updatePerson.getEmail());
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE from Person WHERE id=?");
       preparedStatement.setInt(1, id);
       preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

//        people.removeIf(p -> p.getId() == id);
    }
}
