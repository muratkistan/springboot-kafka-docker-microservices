package com.muratkistan.db.repository;

import com.muratkistan.db.model.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepository extends JpaRepository<Wikimedia,Long> {
}