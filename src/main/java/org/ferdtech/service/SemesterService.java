package org.ferdtech.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import org.ferdtech.entity.SemesterEntity;
import org.ferdtech.repository.SemesterRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    public List<SemesterEntity> findAll(Integer page, Integer pageSize) {
    return semesterRepository.findAll()
            .page(page, pageSize)
            .list();
    }

    public SemesterEntity findById(UUID semesterId) {
        return semesterRepository.findByIdOptional(semesterId)
                .orElseThrow(() -> new NotFoundException("Semester not found"));
    }

    public SemesterEntity createSemester(SemesterEntity semesterEntity) {
        semesterRepository.persist(semesterEntity);
        return semesterEntity;
    }

    public SemesterEntity updateSemester(UUID semesterId, SemesterEntity semesterEntity) {
        var semester = findById(semesterId);
        semester.setName(semesterEntity.getName());
        semesterRepository.persist(semester);
        return semester;
    }

    public void deleteSemester(UUID semesterId) {
        var semester = findById(semesterId);
        semesterRepository.delete(semester);
    }
}
