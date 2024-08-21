package Vmo.Springpro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Vmo.Springpro.Dtorequest.ProjectCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.Model.Project;
import Vmo.Springpro.repository.CenterRepository;
import Vmo.Springpro.repository.ProjectRepository;
import jakarta.transaction.Transactional;

@Service
public class ProjectService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Project createProject(ProjectCreationRequest request) {
        Project project = new Project();
        project.setName(request.getName());
        project.setManager(request.getManager());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setLanguage(request.getLanguage());
        project.setStatus(request.getStatus());

        // Liên kết project với center
        Center center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new AppException(ErrorClass.CENTER_NOT_FOUND));

        project.setCenter(center);

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));
    }

    @Transactional
    public Project updateProject(int id, ProjectCreationRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));

        project.setName(request.getName());
        project.setManager(request.getManager());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setLanguage(request.getLanguage());
        project.setStatus(request.getStatus());

        return projectRepository.save(project);
    }

    @Transactional
    public void deleteProject(int id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));
        projectRepository.delete(project);
    }
}
