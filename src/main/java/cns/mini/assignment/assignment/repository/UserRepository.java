package cns.mini.assignment.assignment.repository;

import cns.mini.assignment.assignment.entity.Project;
import cns.mini.assignment.assignment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByUsernameContaining(String name);

    Optional<User>findByUsername(String userName);
}
