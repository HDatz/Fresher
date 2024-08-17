package Vmo.Springpro.Controller;

import Vmo.Springpro.Dtorequest.CenterCreationRequest;
import Vmo.Springpro.Model.Center;
import Vmo.Springpro.Service.CenterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/centers")
public class CenterController {

    @Autowired
    private CenterService centerService;

    @PostMapping
    public ResponseEntity<Center> createCenter(@RequestBody CenterCreationRequest request) {
        Center center = centerService.createCenter(request);
        return ResponseEntity.status(201).body(center);
    }
    
    @GetMapping
    public ResponseEntity<List<Center>> getAllCenter() {
        List<Center> centers = centerService.getAllCenter();
        return ResponseEntity.ok(centers);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Center> getCenterById(@PathVariable int id) {
        Center center = centerService.getCenterById(id);
        return ResponseEntity.ok(center);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody CenterCreationRequest request) {
        Center updatedCenter = centerService.updateCenter(id, request);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCenter(@PathVariable int id) {
        centerService.deleteCenter(id);
        return ResponseEntity.noContent().build();
    }
}