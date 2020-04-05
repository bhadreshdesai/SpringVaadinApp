package bdd.demo.repository;

import bdd.demo.model.Person;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.List;

//@DataJpaTest
@Ignore
public class PersonRepositoryStepDef {

    final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonRepository personRepository;

    private Person foundPerson;

    @When("you search for a person using the name {word} and surname {word}")
    public void youSearchForAPersonUsingTheNameAndSurname(String firstName, String lastName) {
        foundPerson = personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Then("the person is found")
    public void thePersonIsFound() {
        Assertions.assertThat(foundPerson).isNotNull();
    }

    @And("the name is {word}, the surname is {word} and the dob is {word}")
    public void theNameIsTheSurnameIsAndTheDobIs(String firstName, String lastName, String dob) {
        Person person = Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dob(Date.valueOf(dob))
                .build();
        //Assertions.assertThat(foundPerson).isEqualTo(person); // need to ignore id
        Assertions.assertThat(foundPerson).isEqualToIgnoringGivenFields(person, "id");
    }

    @Then("the person is not found")
    public void thePersonIsNotFound() {
        Assertions.assertThat(foundPerson).isNull();
    }

    @Given("the following people are in the system")
    public void theFollowingPeopleAreInTheSystem(List<Person> personList) {
        LOG.debug("Executing background");
        LOG.debug(personList.toString());
        for (Person person : personList) {
            personRepository.save(person);
        }
        personRepository.flush();
    }
}
