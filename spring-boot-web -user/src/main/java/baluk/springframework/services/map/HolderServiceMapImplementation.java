package baluk.springframework.services.map;

import baluk.springframework.model.Holder;
import baluk.springframework.model.ModelObject;
import baluk.springframework.services.security.EncryptionService;
import baluk.springframework.services.HolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
@Service
@Profile("map")
public class HolderServiceMapImplementation extends   AbstractMapServices implements HolderService {

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<ModelObject> listAll() {
        return super.listAll();
    }

    @Override
    public Holder getById(Integer id) {
        return (Holder) super.getById(id);
    }

    @Override
    public Holder saveOrUpdate(Holder modelObject) {

        if(modelObject.getPassword() != null){
            modelObject.setEncryptedPassword(encryptionService.encryptString(modelObject.getPassword()));
        }

        return (Holder) super.saveOrUpdate(modelObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

    @Override
    public Holder findByUsername(String userName) {

        Optional returnUser =  modelMap.values().stream().filter(new Predicate<ModelObject>() {
            @Override
            public boolean test(ModelObject modelObject) {
                Holder holder = (Holder) modelObject;
                return holder.getUsername().equalsIgnoreCase(userName);
            }
        }).findFirst();

        return (Holder) returnUser.get();
    }
}
