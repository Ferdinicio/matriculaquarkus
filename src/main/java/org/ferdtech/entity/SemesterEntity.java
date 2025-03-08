package org.ferdtech.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_semesters")
public class SemesterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID semesterId;

    private String name;

    @OneToMany(mappedBy = "semester")
    private List<DisciplineEntity> disciplines;

    public UUID getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(UUID semesterId) {
        this.semesterId = semesterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DisciplineEntity> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<DisciplineEntity> disciplines) {
        this.disciplines = disciplines;
    }
}

