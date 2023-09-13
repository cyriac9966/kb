package com.effanga.kbrestapp.service;

import com.effanga.kbrestapp.dao.CandidateRepository;
import com.effanga.kbrestapp.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CandidateServiceImp implements CandidateService{

    private CandidateRepository candidateRepository;


    @Autowired
    public CandidateServiceImp(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }


    @Override
    public List<Candidate> findAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> findById(int theId) {
        Optional<Candidate> candidate = candidateRepository.findById(theId);
        return candidate;
    }

    @Override
    public Candidate save(Candidate theCandidate) {
        return candidateRepository.save(theCandidate);
    }

    @Override
    public void deleteById(int theId) {
        candidateRepository.deleteById(theId);
    }
}
