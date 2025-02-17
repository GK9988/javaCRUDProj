package com.garvk.CrudProj.repositories;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto {

    private String name;

    private int uid;

    private Map<String, Integer> courseMap;

    @Setter(AccessLevel.NONE)
    private float cgpa;

    public void setCgpa(float aInCgpa){
        this.cgpa = Math.round(aInCgpa * 100.0f) / 100.0f;
    }

}
