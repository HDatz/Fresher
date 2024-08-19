package Vmo.Springpro.Dtorequest;

import java.sql.Date;

import lombok.Data;
import Vmo.Springpro.Model.Project.ProjectStatus;

@Data
public class ProjectCreationRequest {
    private String name;

    private int centerId;

    private String manager;

    private Date startDate;

    private Date endDate;
    
    private String language;
    
    private ProjectStatus status;
}
