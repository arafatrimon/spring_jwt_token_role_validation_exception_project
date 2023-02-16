package cns.mini.assignment.assignment.controller;

import cns.mini.assignment.assignment.entity.Project;
import cns.mini.assignment.assignment.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    ProjectRepository projectRepository;

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/list")
    public ResponseEntity<List<Project>> getAllProjects(@RequestParam(required = false) String name) {
        try {
            List<Project> projects = new ArrayList<Project>();
            if (name == null) {
                projectRepository.findAll().forEach(projects::add);
            } else {
                projectRepository.findByNameContaining(name).forEach(projects::add);

            }
            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/findById/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable("id") long id) {

        Optional<Project> projectData = projectRepository.findById(id);
        if (projectData.isPresent()) {
            return new ResponseEntity<>(projectData.get(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PreAuthorize("hasRole('User')")
    @PostMapping("/save")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) {
        try {
            Project _project = projectRepository.save(project);
            return new ResponseEntity<>(_project, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PreAuthorize("hasRole('User')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable("id") long id, @RequestBody Project project) {
        Optional<Project> projectData = projectRepository.findById(id);
        if (projectData.isPresent()) {
            Project _project = projectData.get();
            _project.setName(project.getName());
            _project.setIntro(project.getIntro());
            _project.setOwner(project.getOwner());
            _project.setStartDateTime(project.getStartDateTime());
            _project.setEndDateTime(project.getEndDateTime());
            project.setId(id);
            return new ResponseEntity<>(projectRepository.save(project), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteProject(@PathVariable("id") long id) {
        try {
            projectRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteAllProject() {
        try {
            projectRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getProjectBetweenDate/{startDateTime}/{endDateTime}")
    public ResponseEntity<List<Project>> getProjectBetweenDate(@PathVariable("startDateTime") String startDateTime, @PathVariable("endDateTime") String endDateTime) throws ParseException {

        Date fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateTime);
        Date toDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateTime);
        try {

            List<Project> projects = new ArrayList<Project>();
            projectRepository.findAllByStartDateTimeBetween(fromDate, toDate).forEach(projects::add);

            System.out.println(projects);

            if (projects.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
