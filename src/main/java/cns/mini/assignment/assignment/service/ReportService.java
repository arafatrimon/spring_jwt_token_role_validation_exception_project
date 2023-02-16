package cns.mini.assignment.assignment.service;


import cns.mini.assignment.assignment.entity.Project;
import cns.mini.assignment.assignment.repository.ProjectRepository;
//import net.sf.jasperreports.engine.*;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProjectRepository repository;


//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\basan\\Desktop\\Report";
//        List<Project> projects = repository.findAll();
//        //load file and compile it
//        File file = ResourceUtils.getFile("classpath:project.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projects);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "Java Techie");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\projects.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projects.pdf");
//        }
//
//        return "report generated in path : " + path;
//    }
}
