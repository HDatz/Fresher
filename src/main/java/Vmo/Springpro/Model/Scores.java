package Vmo.Springpro.Model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreId;

    @OneToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @Column(nullable = false)
    private float assignment1;

    @Column(nullable = false)
    private float assignment2;

    @Column(nullable = false)
    private float assignment3;

    @Column(name = "final_score")
    private Float finalScore;

    @PrePersist
    @PreUpdate
    @PostLoad
    private void calculateFinalScore() {
        this.finalScore = (assignment1 + assignment2 + assignment3) / 3;
    }



}
