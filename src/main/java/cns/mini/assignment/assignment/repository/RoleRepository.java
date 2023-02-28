package cns.mini.assignment.assignment.repository;

import cns.mini.assignment.assignment.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);

    @Query(
            value = "select count(*) from role where role_name = 'Admin' ",
            nativeQuery = true)
    int countByAdminRole();


    @Query(
            value = "select count(*) from role where role_name = 'User' ",
            nativeQuery = true)
    int countByUserRole();
}
