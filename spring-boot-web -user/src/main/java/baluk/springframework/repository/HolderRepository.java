package baluk.springframework.repository;

import baluk.springframework.model.Holder;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
public interface HolderRepository extends CrudRepository<Holder, Integer> {
    Holder findByUsername(String username);
}
