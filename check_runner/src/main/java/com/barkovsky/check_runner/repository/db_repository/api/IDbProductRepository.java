package com.barkovsky.check_runner.repository.db_repository.api;

import com.barkovsky.check_runner.repository.db_repository.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDbProductRepository extends JpaRepository<ProductEntity, Long> {
}
