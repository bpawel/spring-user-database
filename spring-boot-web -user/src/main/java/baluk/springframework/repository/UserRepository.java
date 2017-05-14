package baluk.springframework.repository;

import baluk.springframework.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paweł Baluk on 07/05/2017.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
}
