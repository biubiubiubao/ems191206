package com.ix.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Emp implements Serializable {
    @Id
    private String id;
    private String name;
    private Double salary;
    private Integer age;
}
