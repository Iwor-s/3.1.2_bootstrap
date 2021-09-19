package web.config;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;

@AllArgsConstructor
@Component
public class DBinit {
    private final RoleService roleService;
    private final UserService userService;
    
    @PostConstruct
    public void createData() {
        roleService.saveRole(new Role("ADMIN"));
        roleService.saveRole(new Role("USER"));
        
        User user1 = new User();
        user1.setFirstName("Tom");
        user1.setLastName("Jones");
        user1.setAge((byte) 27);
        user1.setEmail("tom@gmail.com");
        user1.setPassword("tom");
        user1.addRole(roleService.getRoleByName("ADMIN"));
        
        User user2 = new User();
        user2.setFirstName("Анна");
        user2.setLastName("Волкова");
        user2.setAge((byte) 24);
        user2.setEmail("ann@mail.ru");
        user2.setPassword("анна");
        user2.addRole(roleService.getRoleByName("USER"));
        
        User user3 = new User();
        user3.setFirstName("Sam");
        user3.setLastName("Black");
        user3.setAge((byte) 35);
        user3.setEmail("sam@yahoo.com");
        user3.setPassword("sam");
        user3.addRole(roleService.getRoleByName("ADMIN"));
        user3.addRole(roleService.getRoleByName("USER"));
        
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
