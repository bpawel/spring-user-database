package baluk.springframework.repository;

import baluk.springframework.config.RepositoryConfiguration;
import baluk.springframework.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class UserRepositoryTest {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Test
    public void testSaveProduct(){
        //setup user
        User user = new User();
        user.setName("asddsa");
        user.setSurname("Spring Framework");
        user.setAge(25);


        //save user, verify has ID value after save
        assertNull(user.getId()); //null before save
        userRepository.save(user);
        assertNotNull(user.getId()); //not null after save

        //fetch from DB
        User fetchedUser = userRepository.findOne(user.getId());

        //should not be null
        assertNotNull(fetchedUser);

        //should equal
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getSurname(), fetchedUser.getSurname());

        //update description and save
        fetchedUser.setSurname("New Surname");
        userRepository.save(fetchedUser);

        //get from DB, should be updated
        User fetchedUpdatedUser = userRepository.findOne(fetchedUser.getId());
        assertEquals(fetchedUser.getSurname(), fetchedUpdatedUser.getSurname());

        //verify count of products in DB
        long productCount = userRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<User> products = userRepository.findAll();

        int count = 0;

        for(User p : products){
            count++;
        }

        assertEquals(count, 1);
    }
}
