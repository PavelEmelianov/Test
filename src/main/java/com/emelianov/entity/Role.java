package com.emelianov.entity;

import java.io.Serializable;
import javax.inject.Named;
import javax.persistence.*;

@Entity
@Named
@Table(name = "Role")
public class Role implements Serializable {

    private static final long serialVersionUID = -3869194470824513072L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " NAME: " + name;
    }

}
