package Vmo.Springpro.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Vmo.Springpro.Dtorequest.ApiRespone;
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
    public ResponseEntity<ApiRespone<Scores>> createScores(@RequestBody ScoresCreationRequest request) {
        try {
            Scores scores = scoresService.createScores(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                                 .body(new ApiRespone<>(201, "Scores created successfully", scores));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(new ApiRespone<>(400, "Error creating Scores: " + e.getMessage(), null));
        }
    }

    // Lấy tất cả các Scores
    @GetMapping
    public ResponseEntity<ApiRespone<List<Scores>>> getAllScores() {
        List<Scores> scoresList = scoresService.getAllScores();
        return ResponseEntity.ok(new ApiRespone<>(200, "Fetched all Scores", scoresList));
    }

    // Lấy thông tin Scores theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Scores>> getScoresById(@PathVariable int id) {
        Optional<Scores> scores = Optional.ofNullable(scoresService.getScoresById(id));
        return scores.map(value -> ResponseEntity.ok(new ApiRespone<>(200, "Scores found", value)))
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                    .body(new ApiRespone<>(404, "Scores not found", null)));
    }

    // Cập nhật thông tin Scores
    @PutMapping("/{id}")
    public ResponseEntity<ApiRespone<Scores>> updateScores(@PathVariable int id, @RequestBody ScoresCreationRequest request) {
        try {
            Scores updatedScores = scoresService.updateScores(id, request);
            return ResponseEntity.ok(new ApiRespone<>(200, "Scores updated successfully", updatedScores));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(new ApiRespone<>(404, "Error updating Scores: " + e.getMessage(), null));
        }
    }
}
