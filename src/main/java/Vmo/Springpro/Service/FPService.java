package Vmo.Springpro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Model.Project;
import Vmo.Springpro.Model.Fresher_Project;
import Vmo.Springpro.repository.FPRepository;
import Vmo.Springpro.repository.FresherRepository;
import Vmo.Springpro.repository.ProjectRepository;
import jakarta.mail.MessagingException;
import Vmo.Springpro.Dtorequest.FPCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FPService {

    @Autowired
    private FPRepository fpRepository;

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SendEmailService sendEmailService; // Inject EmailService

    @Transactional
    public Fresher_Project createFresherProject(FPCreationRequest request) {
        Fresher fresher = fresherRepository.findById(request.getFresherid())
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
        Project project = projectRepository.findById(request.getProjectid())
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));

        if (fpRepository.existsByFresherAndProject(fresher, project)) {
            throw new AppException(ErrorClass.RELATIONSHIP_ALREADY_EXISTS);
        }

        Fresher_Project fresherProject = new Fresher_Project();
        fresherProject.setFresher(fresher);
        fresherProject.setProject(project);

        return fpRepository.save(fresherProject);
    }

    public boolean existsByFresherAndProject(Fresher fresher, Project project) {
        return fpRepository.existsByFresherAndProject(fresher, project);
    }

    public List<Fresher_Project> getAllFresherProjects() {
        return fpRepository.findAll();
    }

    public List<Project> getProjectsByFresher(int fresherId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new AppException(ErrorClass.USER_EXISTED));

        List<Fresher_Project> fresherProjects = fpRepository.findByFresher(fresher);
        return fresherProjects.stream().map(Fresher_Project::getProject).collect(Collectors.toList());
    }

    public Fresher_Project getFresherProjectById(int id) {
        return fpRepository.findById(id).orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
    }

    @Transactional
    public Fresher_Project updateFresherProject(int id, Fresher_Project updatedFresherProject) {
        Fresher_Project fresherProject = fpRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));

        fresherProject.setFresher(updatedFresherProject.getFresher());
        fresherProject.setProject(updatedFresherProject.getProject());

        return fpRepository.save(fresherProject);
    }

    @Transactional
    public void removeFresherFromProject(int fresherId, int projectId) {
        Fresher fresher = fresherRepository.findById(fresherId)
                .orElseThrow(() -> new AppException(ErrorClass.USER_EXISTED));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));

        Fresher_Project fresherProject = fpRepository.findByFresherAndProject(fresher, project)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));

        fpRepository.delete(fresherProject);

        // Gửi email thông báo sử dụng Thymeleaf
        String subject = "Thông báo xóa khỏi dự án";
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("name", fresher.getName());
        templateModel.put("projectName", project.getName());

        try {
            sendEmailService.sendHtmlEmail(fresher.getEmail(), subject, "index", templateModel);
        } catch (MessagingException e) {
            throw new RuntimeException("Không thể gửi email", e);
        }
    }

    public Fresher getFresherById(int id) {
        return fresherRepository.findById(id).orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));
    }
}
