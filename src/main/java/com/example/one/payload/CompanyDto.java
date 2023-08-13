package com.example.one.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto{
    private String name;
    private String directorName;
    private String street;
    private Integer home_number;

}
