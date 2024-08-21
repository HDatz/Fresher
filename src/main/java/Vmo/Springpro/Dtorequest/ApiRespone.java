package Vmo.Springpro.Dtorequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ApiRespone<T> {
    private int statusCode;
    private String message;
    private T data;
}
