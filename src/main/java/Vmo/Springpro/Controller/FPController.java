package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Vmo.Springpro.Dtorequest.ApiRespone;
import Vmo.Springpro.Dtorequest.FPCreationRequest;
import Vmo.Springpro.Model.Fresher_Project;
import Vmo.Springpro.Service.FPService;

import java.util.List;

@RestController
@RequestMapping("/fresproject")
public class FPController {

    @Autowired
    private FPService fpService;

    @PostMapping
    public ResponseEntity<ApiRespone<Fresher_Project>> createFP(@RequestBody FPCreationRequest request) {
        try {
            Fresher_Project fp = fpService.createFresherProject(request);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(201, "Fresher_Project created successfully", fp);
            return ResponseEntity.status(201).body(response);
        } catch (RuntimeException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(400, "Error creating Fresher_Project", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping
    public ResponseEntity<ApiRespone<List<Fresher_Project>>> getAllFresherProjects() {
        List<Fresher_Project> fresherProjects = fpService.getAllFresherProjects();
        ApiRespone<List<Fresher_Project>> response = new ApiRespone<>(200, "Fetched all Fresher_Projects", fresherProjects);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Fresher_Project>> getFresherProjectById(@PathVariable int id) {
        try {
            Fresher_Project fresherProject = fpService.getFresherProjectById(id);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(200, "Fresher_Project found", fresherProject);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(404, "Fresher_Project not found", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiRespone<Fresher_Project>> updateFresherProject(@PathVariable int id, @RequestBody FPCreationRequest request) {
        try {
            Fresher_Project updatedFresherProject = new Fresher_Project();
            updatedFresherProject.setFresher(fpService.getFresherById(request.getFresherid()));
            updatedFresherProject.setProject(fpService.getProjectById(request.getProjectid()));
            Fresher_Project fp = fpService.updateFresherProject(id, updatedFresherProject);
            ApiRespone<Fresher_Project> response = new ApiRespone<>(200, "Fresher_Project updated successfully", fp);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            ApiRespone<Fresher_Project> response = new ApiRespone<>(404, "Error updating Fresher_Project", null);
            return ResponseEntity.status(404).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiRespone<Void>> deleteFresherProject(@PathVariable int id) {
        try {
            fpService.deleteFresherProject(id);
            ApiRespone<Void> response = new ApiRespone<>(204, "Fresher_Project deleted successfully", null);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            ApiRespone<Void> response = new ApiRespone<>(404, "Error deleting Fresher_Project", null);
            return ResponseEntity.status(404).body(response);
        }
    }
}
