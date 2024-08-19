package Vmo.Springpro.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Vmo.Springpro.Dtorequest.ProjectCreationRequest;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.Model.Project;
import Vmo.Springpro.repository.CenterRepository;
import Vmo.Springpro.repository.ProjectRepository;

@Service
public class ProjectService {

    @Autowired
    private CenterRepository centerRepository;

    @Autowired
    private ProjectRepository projectRepository;

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
                .orElseThrow(() -> new RuntimeException("Center not found"));
        project.setCenter(center);

        return projectRepository.save(project);
    }
}
