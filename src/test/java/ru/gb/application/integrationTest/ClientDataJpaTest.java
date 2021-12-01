package ru.gb.application.integrationTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import ru.gb.entity.User;
import ru.gb.entity.repository.UserRepository;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientDataJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

   @Test
   public void findByEmailTest() {
       entityManager.persist(new User(1, "test@test.com", "12345", "test 1 ", "secondName"));
       User user = repository.findByEmail("test@test.com").get();
       Assertions.assertThat(user.getEmail().equals("test@test.com"));
   }

    @Test
    @Rollback(false)
    public void testSaveNewUser(){

       User saveUser = repository.save(new User(2, "test2@test2.com", "54321", "test 2", "secondName2"));
       Assertions.assertThat(saveUser.getId()).isGreaterThan(2);
    }

}
