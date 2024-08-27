package Vmo.Springpro.Model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Fresher_Project", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"fresher_id", "project_id"})
	})
@Data
public class Fresher_Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fresherProjectId;

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;
}

