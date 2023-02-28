package cns.mini.assignment.assignment.service;

import cns.mini.assignment.assignment.entity.Role;
import cns.mini.assignment.assignment.entity.User;
import cns.mini.assignment.assignment.repository.RoleRepository;
import cns.mini.assignment.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {
        if(roleRepository.count()<1){
            Role adminRole=new Role();
            adminRole.setRoleName("Admin");
            adminRole.setRoleDescription("Admin role");
            roleRepository.save(adminRole);

            Role userRole=new Role();
            userRole.setRoleName("User");
            userRole.setRoleDescription("User role");
            roleRepository.save(userRole);
        }
        if (userRepository.count()<1){
            Role role=new Role();
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(getEncodedPassword("123456"));
            adminUser.setEmail("admin@gamil.com");

           role=roleRepository.findByRoleName("Admin").get();

//            role.setId(1);
//            role.setRoleName("Admin");
//            role.setRoleDescription("Admin role");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(role);
            adminUser.setRole(adminRoles);
            userRepository.save(adminUser);


            User user = new User();
            user.setUsername("user");
            user.setPassword(getEncodedPassword("123456"));
            user.setEmail("user@gamil.com");

            role=roleRepository.findByRoleName("User").get();

//            role.setId(2);
//            role.setRoleName("User");
//            role.setRoleDescription(" User role");

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
            userRepository.save(user);
        }else {
            System.out.println("Return value");
        }






//        Role userRole = new Role();
//        //  User userUser;
//        int countAdminRole = roleRepository.countByAdminRole();
//        int countUserRole = roleRepository.countByUserRole();
//        int countAdmin = userRepository.countByAdmin();
//        int countUser = userRepository.countByUser();
//        if (countAdminRole == 0 || countAdminRole == 1 ) {
//            if (countAdminRole == 0){
//                adminRole.setRoleName("Admin");
//                adminRole.setRoleDescription("Admin role");
//                roleRepository.save(adminRole);
//            }
//            else {
//                if (countAdmin == 0) {
//                    User adminUser = new User();
//                    adminUser.setUsername("admin");
//                    adminUser.setPassword(getEncodedPassword("123456"));
//                    adminUser.setEmail("admin@gamil.com");
//                    List<Role> adminRoles = (List<Role>) roleRepository.findByRoleName("Admin").get();
//                    adminUser.setRole(adminRoles);
////               Set<Role> adminRoles = new HashSet<>();
/////              adminRoles.add(adminRole.getId());
////                adminUser.setRole(adminRoles);
//                    userRepository.save(adminUser);
//                }
//            }
//
//
//        }

//        if (countUserRole == 0) {
//            adminRole.setRoleName("Admin");
//            adminRole.setRoleDescription("Admin role");
//            roleRepository.save(adminRole);
//            if (countAdmin == 0) {
//                User adminUser = new User();
//                adminUser.setUsername("admin");
//                adminUser.setPassword(getEncodedPassword("123456"));
//                adminUser.setEmail("admin@gamil.com");
//                Set<Role> adminRoles = new HashSet<>();
//                adminRoles.add(adminRole);
//                adminUser.setRole(adminRoles);
//                userRepository.save(adminUser);
//            }
//        }
//        if(adminRole !=null){
//
//
//            adminUser.setUsername("admin");
//            adminUser.setPassword(getEncodedPassword("123456"));
//            adminUser.setEmail("admin@gamil.com");
//            Set<Role> adminRoles = new HashSet<>();
//            adminRoles.add(adminRole);
//            adminUser.setRole(adminRoles);
//            userRepository.save(adminUser);
//
//        }else {
////            adminRole.setRoleName("Admin");
////            adminRole.setRoleDescription("Admin role");
////            roleRepository.save(adminRole);
//
//            adminUser.setUsername("admin");
//            adminUser.setPassword(getEncodedPassword("123456"));
//            adminUser.setEmail("admin@gamil.com");
//            Set<Role> adminRoles = new HashSet<>();
//            adminRoles.add(adminRole);
//            adminUser.setRole(adminRoles);
//            userRepository.save(adminUser);
//
//        }


//
//        int count= (int) roleRepository.count();
//        Role adminRole = new Role();
//        if (count<=2) {
//
//            adminRole.setRoleName("Admin");
//            adminRole.setRoleDescription("Admin role");
//            roleRepository.save(adminRole);
//
//            Role userRole = new Role();
//            userRole.setRoleName("User");
//            userRole.setRoleDescription("Default role for newly created record");
//            roleRepository.save(userRole);
//        }else{
//            adminRole =  roleRepository.findByRoleName("Admin").get();
//            if(adminRole !=null){
//                User adminUser = new User();
//                adminUser.setUsername("admin");
//                adminUser.setPassword(getEncodedPassword("123456"));
//                adminUser.setEmail("admin@gamil.com");
//                Set<Role> adminRoles = new HashSet<>();
//                adminRoles.add(adminRole);
//                adminUser.setRole(adminRoles);
//                userRepository.save(adminUser);
//
//            }
//
//        }
//
//            User adminUser = new User();
//            adminUser.setUsername("admin");
//            adminUser.setPassword(getEncodedPassword("123456"));
//            adminUser.setEmail("admin@gamil.com");
//            Set<Role> adminRoles = new HashSet<>();
//            adminRoles.add();
//            adminUser.setRole(adminRoles);
//            userRepository.save(adminUser);
//
//
//        User user = new User();
//        user.setUsername("arafat");
//        user.setPassword(getEncodedPassword("123456"));
//        user.setEmail("arafat@gmail.com");
//        Set<Role> userRoles = new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userRepository.save(user);
    }

    public User registerNewUser(User user) {
        if(user.getRole()==null){
            Role role = roleRepository.findByRoleName("User").get();
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(role);
            user.setRole(userRoles);
        }
        user.setPassword(getEncodedPassword(user.getPassword()));
        return userRepository.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
