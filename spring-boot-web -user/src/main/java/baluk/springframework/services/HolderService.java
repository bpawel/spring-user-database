package baluk.springframework.services;

import baluk.springframework.model.Holder;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
public interface HolderService extends CRUDService<Holder> {

    Holder findByUsername(String username);
}
