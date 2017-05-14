package baluk.springframework.services;

import baluk.springframework.model.Holder;
import baluk.springframework.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import baluk.springframework.services.security.EncryptionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class HolderServiceImpl implements HolderService {

    private HolderRepository holderRepository;

    @Autowired
    public void setUserRepository(HolderRepository holderRepository) {
        this.holderRepository = holderRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<?> listAll() {
        List<Holder> holders = new ArrayList<>();
        holderRepository.findAll().forEach(holders::add); //fun with Java 8
        return holders;
    }

    @Override
    public Holder getById(Integer id) {
        return holderRepository.findOne(id);
    }

    @Override
    public Holder saveOrUpdate(Holder modelObject) {
        if(modelObject.getPassword() != null){
            modelObject.setEncryptedPassword(encryptionService.encryptString(modelObject.getPassword()));
        }
        return holderRepository.save(modelObject);
    }
    @Override
    @Transactional
    public void delete(Integer id) {
        holderRepository.delete(id);
    }

    @Override
    public Holder findByUsername(String username) {
        return holderRepository.findByUsername(username);
    }
}
