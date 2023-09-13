package com.effanga.kbrestapp.controller;

import com.effanga.kbrestapp.dao.CandidateRepository;
import com.effanga.kbrestapp.entity.Candidate;
import com.effanga.kbrestapp.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kb")
public class CandidateRestController {

    private CandidateService candidateService;
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateRestController(CandidateService candidateService,
                                   CandidateRepository candidateRepository) {
        this.candidateService = candidateService;
        this.candidateRepository = candidateRepository;
    }

    @GetMapping("/candidates")
    public List<Candidate> showCandidates(){
        return candidateService.findAll();
    }

    @GetMapping("/candidate/{candidateId}")
    public Candidate getCandidate(@PathVariable int candidateId){

        Candidate theCandidate = null;

        Optional<Candidate> result = candidateService.findById(candidateId);

        if (result.isPresent()){
            theCandidate = result.get();
        }
        else throw new RuntimeException("did not find candidate with id: "+candidateId);

        return theCandidate;
    }

    @PostMapping("/candidates")
    public Candidate addCandidate(@RequestBody Candidate theCandidate){

        theCandidate.setId(0);

        Candidate dbCandidate = candidateService.save(theCandidate);

        System.out.println("SAVING CANDIDATE: "+theCandidate);

        return dbCandidate;
    }

    @DeleteMapping("/candidate/{candidateId}")
    public void deleteCandidate(@PathVariable int candidateId){
        Optional<Candidate> foundCandidate = candidateRepository.findById(candidateId);


        if (foundCandidate.isPresent()){
            candidateService.deleteById(candidateId);
            System.out.println("DELETING CANDIDATE WITH ID: "+candidateId);
        }

        else throw new RuntimeException("Candidate with id: "+candidateId+ " does not exist");

    }
}
