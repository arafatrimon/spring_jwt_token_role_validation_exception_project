package cns.mini.assignment.assignment.repository;

import cns.mini.assignment.assignment.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project,Long> {
    //List<Project> findByPublished(boolean published);
    List<Project> findByNameContaining(String title);

    List<Project> findAllByStartDateTimeBetween(
            Date startDateTime,
            Date endDateTime);
}
