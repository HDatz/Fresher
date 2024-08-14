package Vmo.Springpro.Model;

import java.security.Timestamp;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class EmailLogs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emailLogId;

    @ManyToOne
    @JoinColumn(name = "fresher_id")
    private Fresher fresher;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(length = 50)
    private String action;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp emailSentTime;
}
