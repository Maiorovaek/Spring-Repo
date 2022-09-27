package com.example.restApi.repositories;

import com.example.restApi.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  PeopleRepository extends JpaRepository<Person, Integer> {


}
