package Vmo.Springpro.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Fresher_Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fresherProjectId;

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher_id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project_id;
}

