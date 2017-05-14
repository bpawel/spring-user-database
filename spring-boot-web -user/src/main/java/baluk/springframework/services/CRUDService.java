package baluk.springframework.services;

import java.util.List;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
public interface CRUDService<T> {
    List<?> listAll();

    T getById(Integer id);
    T saveOrUpdate(T modelObject);

    void delete(Integer id);

}
