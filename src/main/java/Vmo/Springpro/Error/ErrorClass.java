package Vmo.Springpro.Error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorClass {
    private int code;
    private String message;

    // Các hằng số lỗi có thể dùng trong ứng dụng
    public static final ErrorClass CENTER_NOT_FOUND = new ErrorClass(1001, "Center không tồn tại.");
    public static final ErrorClass FP_NOT_FOUND = new ErrorClass(1002, "Fresher_Project không tồn tại.");
    public static final ErrorClass PROJECT_NOT_FOUND = new ErrorClass(1003, "Project không tồn tại.");
    public static final ErrorClass USER_EXISTED = new ErrorClass(1004, "User đã tồn tại.");
    public static final ErrorClass SCORE_NOT_FOUND = new ErrorClass(1005, "Scores không tồn tại.");
    public static final ErrorClass USER_NOT_EXISTED = new ErrorClass(1006, "User không tồn tại.");
    public static final ErrorClass UNAUTHENTICATED = new ErrorClass(1007, "KHÔNG ĐƯỢC XÁC THỰC");

 
}
