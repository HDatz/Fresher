package Vmo.Springpro.Dtorequest;

import lombok.Data;

@Data
public class FresherCreationRequest {
    private String name;
    private String email;
    private String phone;
    private String programmingLanguage;
    private int centerId; 
    
}