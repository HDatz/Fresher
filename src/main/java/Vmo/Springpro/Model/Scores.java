package Vmo.Springpro.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Scores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scoreId;

    @OneToOne
    @JoinColumn(name = "fresher_id") // Thêm annotation này để ánh xạ đến khóa ngoại trong bảng
    private Fresher fresher;

    private float assignment1;

    private float assignment2;

    private float assignment3;

    private float finalScore; 
}

