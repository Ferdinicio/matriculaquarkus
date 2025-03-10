package org.ferdtech.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import org.ferdtech.entity.DisciplineEntity;

import java.util.UUID;

@ApplicationScoped
public class DisciplineRepository implements PanacheRepositoryBase<DisciplineEntity, UUID> {
}
