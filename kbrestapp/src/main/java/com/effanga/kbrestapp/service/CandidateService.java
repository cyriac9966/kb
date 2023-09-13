package com.effanga.kbrestapp.service;

import com.effanga.kbrestapp.entity.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateService {

    List<Candidate> findAll();

    Optional<Candidate> findById(int theId);

    Candidate save(Candidate theCandidate);

    void deleteById(int theId);
}
