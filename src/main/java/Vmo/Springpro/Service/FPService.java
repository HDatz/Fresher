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
import Vmo.Springpro.Dtorequest.FPCreationRequest;
import Vmo.Springpro.Error.AppException;
import Vmo.Springpro.Error.ErrorClass;

import java.util.List;

@Service
public class FPService {

    @Autowired
    private FPRepository fpRepository;
    

    @Autowired
    private FresherRepository fresherRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Transactional
    public Fresher_Project createFresherProject(FPCreationRequest request) {
        Fresher fresher = fresherRepository.findById(request.getFresherid())
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
        Project project = projectRepository.findById(request.getProjectid())
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));
        
        Fresher_Project fresherProject = new Fresher_Project();
        fresherProject.setFresher_id(fresher);
        fresherProject.setProject_id(project);
        
        return fpRepository.save(fresherProject);
    }
    
    public List<Fresher_Project> getAllFresherProjects() {
        return fpRepository.findAll();
    }

    public Fresher_Project getFresherProjectById(int id) {
        return fpRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
    }

    @Transactional
    public Fresher_Project updateFresherProject(int id, Fresher_Project updatedFresherProject) {
        Fresher_Project fresherProject = fpRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
        
        fresherProject.setFresher_id(updatedFresherProject.getFresher_id());
        fresherProject.setProject_id(updatedFresherProject.getProject_id());
        
        return fpRepository.save(fresherProject);
    }

    @Transactional
    public void deleteFresherProject(int id) {
        Fresher_Project fresherProject = fpRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
        fpRepository.delete(fresherProject);
    }

    public Fresher getFresherById(int id) {
        return fresherRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.FP_NOT_FOUND));
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorClass.PROJECT_NOT_FOUND));
    }
}
