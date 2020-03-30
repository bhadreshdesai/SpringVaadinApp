package bdd.demo.repository;

import bdd.demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

// https://github.com/hellokoding/hellokoding-courses/blob/master/springboot-examples/springboot-restapi-testing-all-layers/src/test/java/com/hellokoding/springboot/restful/product/ProductRepositoryTest.java
// @DataJpaTest already has @ExtendWith(SpringExtension.class) defined in it so we don't have to use it
@DataJpaTest
class UserRepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail() {
        // when
        User actualUser = userRepository.findByEmail("test@email.com");
        User expectedUser = User.builder()
                .email("test@email.com")
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .build();
        LOGGER.debug(actualUser.toString());

        // then
       assertThat(actualUser).isEqualTo(expectedUser);
       // and
       // Using testEntityManager.flush() and testEntityManager.clear() returns null getPasswordConfirm
       // if you don't flush and clear the testEntityManager we get the previous instance of the user
       // which return passwordConfirm for getPasswordConfirm()
       assertThat(actualUser.getPasswordConfirm()).isNull();
    }

    @BeforeEach
    public void setUp() {
        //give
        User user = User.builder()
                .email("test@email.com")
                .firstName("firstName")
                .lastName("lastName")
                .password("password")
                .passwordConfirm("passwordConfirm")
                .build();
        testEntityManager.persist(user);
        testEntityManager.flush();
        testEntityManager.clear();
    }
}