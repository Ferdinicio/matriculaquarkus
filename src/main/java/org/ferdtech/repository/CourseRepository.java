package org.ferdtech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.ferdtech.entity.CourseEntity;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CourseRepository implements PanacheRepositoryBase<CourseEntity, UUID> {
    public List<CourseEntity> findByCoursearea(String coursearea) {
        return find("coursearea LIKE ?1", "%" + coursearea.toUpperCase() + "%").list();
    }

    public List<CourseEntity> findByCoursename(String coursename) {
        return find("coursename LIKE ?1", "%" + coursename.toUpperCase() + "%").list();
    }
}
