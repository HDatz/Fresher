package Vmo.Springpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Vmo.Springpro.Model.Scores;

public interface ScoresRepository extends JpaRepository<Scores, Integer> {

	// Tìm điểm tốt (finalScore >= 8)
	List<Scores> findByFinalScoreGreaterThanEqual(float score);

	// Tìm điểm trung bình (6.5 <= finalScore < 8)
	List<Scores> findByFinalScoreBetween(float minScore, float maxScore);

	// Tìm điểm kém (finalScore < 6.5)
	List<Scores> findByFinalScoreLessThan(float score);

}
