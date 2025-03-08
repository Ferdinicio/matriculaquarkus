package org.ferdtech.service;

import jakarta.enterprise.context.ApplicationScoped;

import org.ferdtech.exception.CourseNotFoundException;
import org.ferdtech.repository.CourseRepository;
import org.ferdtech.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseEntity createCourse(CourseEntity courseEntity) {
        courseRepository.persist(courseEntity);
        return courseEntity;
    }

    public List<CourseEntity> findAll(Integer page, Integer pageSize) {
        return courseRepository.findAll()
                .page(page, pageSize)
                .list();
    }

    public List<CourseEntity> findByCoursearea(String coursearea) {
        return courseRepository.findByCoursearea(coursearea);
    }

    public List<CourseEntity> findByCoursename(String coursename) {
        return courseRepository.findByCoursename(coursename);
    }

    public CourseEntity findById(UUID courseId) {
        return (CourseEntity) courseRepository.findByIdOptional(courseId)
                .orElseThrow(CourseNotFoundException::new);
    }

    public CourseEntity updateCourse(UUID courseId, CourseEntity courseEntity) {
        var course = findById(courseId);

        course.setCoursename(courseEntity.getCoursename());

        course.setCoursearea(courseEntity.getCoursearea());

        courseRepository.persist(course);

        return course;
    }

    public void deleteById(UUID courseId) {
        var course = findById(courseId);
        courseRepository.deleteById(course.getCourseId());
    }
}