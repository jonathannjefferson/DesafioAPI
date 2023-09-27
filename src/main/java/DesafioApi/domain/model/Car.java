package DesafioApi.domain.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Car {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "car_year")
    private int year;

    @Column(name = "license_Plate")
    private String licensePlate;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

}
