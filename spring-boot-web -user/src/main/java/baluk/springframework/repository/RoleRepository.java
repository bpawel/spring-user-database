package baluk.springframework.repository;

import baluk.springframework.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
