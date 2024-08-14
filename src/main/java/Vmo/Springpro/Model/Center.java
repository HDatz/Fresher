package Vmo.Springpro.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Center {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int centerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 255)
    private String address;
}

