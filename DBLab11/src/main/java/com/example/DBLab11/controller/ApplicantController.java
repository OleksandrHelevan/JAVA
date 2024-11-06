package com.example.DBLab11.controller;

import com.example.DBLab11.dto.ApplicantDTO;
import com.example.DBLab11.service.ApplicantService;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("")
public class ApplicantController {

    @Autowired
    private ApplicantService applicantService;

    @GetMapping("/ap")
    public ResponseEntity<List<ApplicantDTO>> getApplicants() {
        return new ResponseEntity<>(applicantService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ap/{id}")
    public ResponseEntity<ApplicantDTO> getApplicantById(@PathVariable String id) {
        return new ResponseEntity<>(applicantService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/ap/{id}")
    public ResponseEntity<ApplicantDTO> deleteApplicantById(@PathVariable String id) {
        applicantService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/applicants/{id}")
    public List<Bson> getApplicantsBySpecialty(@PathVariable String id) {
        return applicantService.getApplicantsBySpecialtyId(id);
    }
}
