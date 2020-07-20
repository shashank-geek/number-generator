package com.shashank.vmware.numbergenerator.repository;

import com.shashank.vmware.numbergenerator.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to interact with the DB
 */
@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {
    Goal findByUuid(Integer uuid);
}
