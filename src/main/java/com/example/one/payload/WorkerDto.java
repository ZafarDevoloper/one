package com.example.one.payload;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WorkerDto {

    @NotNull(message = "name bush bulmasligi kerak")
    private String name;
    @NotNull(message = "phone_number bush bulmasligi kerak")
    private String phoneNumber;
    @NotNull(message = "manzili bush bulmasligi kerak")
    private String street;
    @NotNull(message = "home_number  bush bulmasligi kerak")
    private Integer home_number;
    private Integer department_id;
}
