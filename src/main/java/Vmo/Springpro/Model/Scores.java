package Vmo.Springpro.Model;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
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

    @Transient
    private float finalScore;

    @PostLoad
    private void calculateFinalScore() {
        this.finalScore = (assignment1 + assignment2 + assignment3) / 3;
    }
}
