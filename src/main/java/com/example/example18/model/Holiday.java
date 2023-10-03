package com.example.example18.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "holidays")
public class Holiday {
    @Id
     String day;
     String reason;
     @Enumerated(EnumType.STRING)
     Type type;


    public enum Type {
        FESTIVAL, FEDERAL
    }
}
