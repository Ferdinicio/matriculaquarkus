package org.ferdtech.service;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;

import org.ferdtech.exception.CourseNotFoundException;
import org.ferdtech.repository.DisciplineRepository;
import org.ferdtech.entity.DisciplineEntity;

@ApplicationScoped
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public DisciplineEntity createDiscipline(DisciplineEntity disciplineEntity) {
        disciplineRepository.persist(disciplineEntity);
        return disciplineEntity;
    }

    public List<DisciplineEntity> findAll(Integer page, Integer pageSize) {
        return disciplineRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public DisciplineEntity findById(UUID disciplineId) {
        return (DisciplineEntity) disciplineRepository.findByIdOptional(disciplineId)
                .orElseThrow(CourseNotFoundException::new);
    }

    public DisciplineEntity updateDiscipline(UUID disciplineId, DisciplineEntity disciplineEntity) {
        var discipline = findById(disciplineId);

        discipline.setCredit(disciplineEntity.getCredit());

        discipline.setName(disciplineEntity.getName());

        discipline.setProfessor(disciplineEntity.getProfessor());

        discipline.setProgram(disciplineEntity.getProgram());

        discipline.setSyllabus(disciplineEntity.getSyllabus());

        disciplineRepository.persist(discipline);

        return discipline;
    }

    public void deleteById(UUID disciplineId) {
        var discipline = findById(disciplineId);
        disciplineRepository.deleteById(discipline.getDisciplineId());
    }
}
