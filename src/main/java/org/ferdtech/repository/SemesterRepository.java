package org.ferdtech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.ferdtech.entity.SemesterEntity;

import java.util.UUID;

@ApplicationScoped
public class SemesterRepository implements PanacheRepositoryBase<SemesterEntity, UUID> {
}