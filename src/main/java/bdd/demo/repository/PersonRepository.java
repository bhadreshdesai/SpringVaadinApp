package bdd.demo.repository;

import bdd.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
