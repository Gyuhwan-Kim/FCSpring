package com.example.swagger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRes {
    @ApiModelProperty(value = "사용자의 이름", example = "lueble", required = true)
    private String name;
    @ApiModelProperty(value = "사용자의 나이", example = "29", required = true)
    private int age;
}
