package com.example.example18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "class")
public class EazyClass extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name = "native")
    private int classId;
    @NotBlank(message ="Name must not be blank")
    @Size(min = 3,message = "Name must be at-least 3 characters long ")
    private String name;
    @OneToMany(mappedBy="eazyClass",fetch = FetchType.LAZY,cascade = CascadeType.PERSIST,targetEntity = Person.class)
    @EqualsAndHashCode.Exclude
    private Set<Person> persons;

}
