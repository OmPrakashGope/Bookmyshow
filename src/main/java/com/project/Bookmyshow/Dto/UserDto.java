package com.project.Bookmyshow.Dto;


import com.project.Bookmyshow.Enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String name;
    private String email;
    private Integer age;
    private String mobileNumber;
    private Gender gender;
}
