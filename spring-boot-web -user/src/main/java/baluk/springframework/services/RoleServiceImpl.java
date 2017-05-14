package baluk.springframework.services;

import baluk.springframework.model.Role;
import baluk.springframework.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paweł Baluk on 09/05/2017.
 */
@Service
@Profile("springdatajpa")
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<?> listAll() {
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }

    @Override
    public Role getById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public Role saveOrUpdate(Role modelObject) {
        return roleRepository.save(modelObject);
    }

    @Override
    public void delete(Integer id) {
        roleRepository.delete(id);
    }
}
