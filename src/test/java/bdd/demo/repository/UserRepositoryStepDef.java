package bdd.demo.repository;

import bdd.demo.model.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

//@DataJpaTest
@Ignore
public class UserRepositoryStepDef {

    @Autowired
    private UserRepository userRepository;

    private User foundUser;

    @Given("user {string} exists in the database")
    public void userExistsInTheDatabase(String email) {
        User user = User.builder()
                .email(email)
                .firstName("FirstName")
                .lastName("LastName")
                .password("Password")
                .passwordConfirm("PasswordConfirm")
                .build();
        userRepository.saveAndFlush(user);
    }

    @When("you search for user {string}")
    public void youSearchForUser(String email) {
        foundUser = userRepository.findByEmail(email);
    }

    @Then("the user is found")
    public void theUserIsFound() {
        Assertions.assertThat(foundUser).isNotNull();
    }

    @And("the user name is {string}")
    public void theUserNameIs(String email) {
        Assertions.assertThat(foundUser.getEmail()).isEqualTo(email);
    }

    @Then("the user is not found")
    public void theUserIsNotFound() {
        Assertions.assertThat(foundUser).isNull();
    }

    @Given("user {string} does not exist in the database")
    public void userDoesNotExistInTheDatabase(String email) {
        if (userRepository.existsById(email)) {
            userRepository.deleteById(email);
            userRepository.flush();
        }
    }
}
