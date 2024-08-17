package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Vmo.Springpro.Dtorequest.ScoresCreationRequest;
import Vmo.Springpro.Model.Scores;
import Vmo.Springpro.Service.ScoresService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/scores")
public class ScoresController {

    @Autowired
    private ScoresService scoresService;

    // Tạo mới một Scores
    @PostMapping
    public ResponseEntity<Scores> createScores(@RequestBody ScoresCreationRequest request) {
        try {
            Scores scores = scoresService.createScores(request);
            return ResponseEntity.status(201).body(scores); // Trả về mã trạng thái 201 Created
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null); // Trả về mã trạng thái 400 Bad Request nếu có lỗi
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Scores>> getAllScores() {
        List<Scores> scoress = scoresService.getAllScores();
        return ResponseEntity.ok(scoress);
    }
    
    // Lấy thông tin Scores theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Scores> getScoresById(@PathVariable int id) {
        Optional<Scores> scores = Optional.ofNullable(scoresService.getScoresById(id));
        return scores.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build()); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy
    }

    // Cập nhật thông tin Scores
    @PutMapping("/{id}")
    public ResponseEntity<Scores> updateScores(@PathVariable int id, @RequestBody ScoresCreationRequest request) {
        try {
            Scores updatedScores = scoresService.updateScores(id, request);
            return ResponseEntity.ok(updatedScores); // Trả về mã trạng thái 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build(); // Trả về mã trạng thái 404 Not Found nếu không tìm thấy
        }
    }
}
