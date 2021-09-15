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
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");
        roleService.saveRole(role1);
        roleService.saveRole(role2);
        
        User user1 = new User();
        user1.setLogin("tom");
        user1.setPassword("tom");
        user1.setName("Tom");
        user1.setSurname("Jones");
        user1.setEmail("tom@gmail.com");
        user1.addRole(role1);
        
        User user2 = new User();
        user2.setLogin("анна");
        user2.setPassword("анна");
        user2.setName("Анна");
        user2.setSurname("Волкова");
        user2.setEmail("ann@mail.ru");
        user2.addRole(role2);
        
        User user3 = new User();
        user3.setLogin("sam");
        user3.setPassword("sam");
        user3.setName("Sam");
        user3.setSurname("Black");
        user3.setEmail("sam@yahoo.com");
        user3.addRole(role1);
        user3.addRole(role2);
        
        userService.saveUser(user1);
        userService.saveUser(user2);
        userService.saveUser(user3);
    }
}
