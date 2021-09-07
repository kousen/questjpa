package com.oreilly.quest.dao;

import com.oreilly.quest.entities.Castle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastleRepository extends JpaRepository<Castle,Long> {
    Castle findByName(String name);
}
