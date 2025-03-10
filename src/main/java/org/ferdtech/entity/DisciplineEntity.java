package org.ferdtech.entity;
import jakarta.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "tb_disciplines")
public class DisciplineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID disciplineId;

    private String name;

    private String professor;

    private Integer credit;

    private String syllabus;

    private String program;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    private SemesterEntity semester;

    public UUID getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(UUID disciplineId) {
        this.disciplineId = disciplineId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public SemesterEntity getSemester() {
        return semester;
    }

    public void setSemester(SemesterEntity semester) {
        this.semester = semester;
    }
}
