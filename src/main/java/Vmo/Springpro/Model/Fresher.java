package Vmo.Springpro.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Fresher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fresherId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 20)
    private String phone;

    @Column(length = 50)
    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}
