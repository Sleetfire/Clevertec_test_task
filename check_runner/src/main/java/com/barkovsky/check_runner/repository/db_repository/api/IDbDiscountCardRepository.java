package com.barkovsky.check_runner.repository.db_repository.api;

import com.barkovsky.check_runner.repository.db_repository.entity.DiscountCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDbDiscountCardRepository extends JpaRepository<DiscountCardEntity, Long> {
}
