package baluk.springframework.services;

import baluk.springframework.model.User;

/**
 * Created by Paweł Baluk on 07/05/2017.
 */
public interface UserService {
    Iterable<User> listAllUsers();

    User getUserById(Integer id);

    User saveUser(User user);

    void deleteUser(Integer id);

}
