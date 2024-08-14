package Vmo.Springpro.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Fresher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fresherId;

    private String name;

    private String email;

    private String phone;

    private String programmingLanguage;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}

