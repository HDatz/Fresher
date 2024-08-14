package Vmo.Springpro.Model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data

public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectId;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    @Column(length = 100)
    private String manager;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column(length = 50)
    private String language;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    public enum ProjectStatus {
        NOT_START, ONGOING, CANCELED, CLOSED
    }
}

