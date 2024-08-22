package Vmo.Springpro.Dtorequest;

import lombok.Data;

@Data
public class FresherCreationRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String programmingLanguages;
    private int centerId;
}
