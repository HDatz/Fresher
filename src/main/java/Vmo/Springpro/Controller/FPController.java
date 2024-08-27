package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.FPCreationRequest;
import Vmo.Springpro.Model.Fresher;
import Vmo.Springpro.Model.Project;
import Vmo.Springpro.Model.Fresher_Project;
import Vmo.Springpro.Service.FPService;
import Vmo.Springpro.Error.AppException;

import java.util.List;

@RestController
@RequestMapping("/fresproject")
public class FPController {

    @Autowired
    private FPService fpService;

    @PostMapping
    public ResponseEntity<ApiRespone<Fresher_Project>> createFP(@RequestBody FPCreationRequest request) {
        try {
            Fresher fresher = fpService.getFresherById(request.getFresherid());
            Project project = fpService.getProjectById(request.getProjectid());

            if (fpService.existsByFresherAndProject(fresher, project)) {
                ApiRespone<Fresher_Project> response = new ApiRespone<>(
                    409, 
                    "Fresher is already assigned to this project", 
                    null
                );
                return ResponseEntity.status(409).body(response);
            }

            Fresher_Project fp = fpService.createFresherProject(request);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                201, 
                "Fresher_Project created successfully", 
                fp
            );
            return ResponseEntity.status(201).body(response);
        } catch (AppException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                e.getErrorClass().getCode(), 
                e.getErrorClass().getMessage(), 
                null
            );
            return ResponseEntity.status(e.getErrorClass().getCode()).body(response);
        } catch (RuntimeException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                400, 
                "Error creating Fresher_Project", 
                null
            );
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiRespone<List<Fresher_Project>>> getAllFresherProjects() {
        List<Fresher_Project> fresherProjects = fpService.getAllFresherProjects();
        ApiRespone<List<Fresher_Project>> response = new ApiRespone<>(
            200, 
            "Fetched all Fresher_Projects", 
            fresherProjects
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Fresher_Project>> getFresherProjectById(@PathVariable int id) {
        try {
            Fresher_Project fresherProject = fpService.getFresherProjectById(id);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                200, 
                "Fresher_Project found", 
                fresherProject
            );
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                e.getErrorClass().getCode(), 
                e.getErrorClass().getMessage(), 
                null
            );
            return ResponseEntity.status(e.getErrorClass().getCode()).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiRespone<Fresher_Project>> updateFresherProject(
            @PathVariable int id, @RequestBody FPCreationRequest request) {
        try {
            Fresher_Project existingFresherProject = fpService.getFresherProjectById(id);
            existingFresherProject.setFresher(fpService.getFresherById(request.getFresherid()));
            existingFresherProject.setProject(fpService.getProjectById(request.getProjectid()));
            Fresher_Project fp = fpService.updateFresherProject(id, existingFresherProject);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                200, 
                "Fresher_Project updated successfully", 
                fp
            );
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(
                e.getErrorClass().getCode(), 
                e.getErrorClass().getMessage(), 
                null
            );
            return ResponseEntity.status(e.getErrorClass().getCode()).body(response);
        }
    }

    @DeleteMapping("/removeFresher/{fresherId}/{projectId}")
    public ResponseEntity<ApiRespone<Void>> removeFresherFromProject(
            @PathVariable int fresherId,
            @PathVariable int projectId) {
        try {
            fpService.removeFresherFromProject(fresherId, projectId);
            ApiRespone<Void> response = new ApiRespone<>(
                200, 
                "Fresher removed from project successfully", 
                null
            );
            return ResponseEntity.ok(response);
        } catch (AppException e) {
            ApiRespone<Void> response = new ApiRespone<>(
                e.getErrorClass().getCode(), 
                e.getErrorClass().getMessage(), 
                null
            );
            return ResponseEntity.status(e.getErrorClass().getCode()).body(response);
        }
    }
}
