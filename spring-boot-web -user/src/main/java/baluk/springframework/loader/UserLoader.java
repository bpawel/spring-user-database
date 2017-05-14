package baluk.springframework.loader;

import baluk.springframework.model.Holder;
import baluk.springframework.model.User;
import baluk.springframework.model.Role;
import baluk.springframework.services.RoleService;
import baluk.springframework.services.HolderService;
import org.apache.log4j.Logger;
import baluk.springframework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


/**
 * Created by Paweł Baluk on 07/05/2017.
 */
@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;
    private HolderService holderService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(UserLoader.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setHolderService(HolderService holderService) {
        this.holderService = holderService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadUsers();
        loadHolders();
        loadRoles();
        assignHoldersToHolderRole();
        assignHoldersToAdminRole();
    }

    private void loadUsers() {
        User user1 = new User();
        user1.setName("Paweł");
        user1.setSurname("Baluk");
        user1.setAge(22);
        user1.setEmail("asd@gmail.com");

        userRepository.save(user1);

        log.info("Saved user1 - id: " + user1.getId());

        User user2 = new User();
        user2.setName("Marcin");
        user2.setSurname("Kowalski");
        user2.setAge(23);
        user2.setEmail("mkowal@gmail.com");

        userRepository.save(user2);

        log.info("Saved user2 - id: " + user2.getId());
    }

    private void loadHolders() {
        Holder holder1 = new Holder();
        holder1.setUsername("user");
        holder1.setPassword("user");
        holderService.saveOrUpdate(holder1);

        Holder holder2 = new Holder();
        holder2.setUsername("admin");
        holder2.setPassword("admin");
        holderService.saveOrUpdate(holder2);

    }

    private void loadRoles() {
        Role role = new Role();
        role.setRole("USER");
        roleService.saveOrUpdate(role);
        log.info("Saved role" + role.getRole());
        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        roleService.saveOrUpdate(adminRole);
        log.info("Saved role" + adminRole.getRole());
    }
    private void assignHoldersToHolderRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<Holder> holders = (List<Holder>) holderService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                holders.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        holderService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignHoldersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<Holder> holders = (List<Holder>) holderService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                holders.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        holderService.saveOrUpdate(user);
                    }
                });
            }
        });
    }

}
