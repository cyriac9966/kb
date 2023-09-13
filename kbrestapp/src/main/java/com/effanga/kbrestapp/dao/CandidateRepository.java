package com.effanga.kbrestapp.dao;

import com.effanga.kbrestapp.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
