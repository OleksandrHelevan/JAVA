package com.example.DBLab11.controller;
import com.example.DBLab11.dto.ApplicantDTO;
import com.example.DBLab11.service.ApplicantService;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping("/applicants/{id}")
    public ResponseEntity<List<Bson>> getApplicantsBySpecialty(@PathVariable String id) {
        return new ResponseEntity<>(applicantService.getApplicantsBySpecialtyId(id), HttpStatus.OK);
    }

    @DeleteMapping("/ap/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id) {
        if(applicantService.remove(id))
            return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);
        else return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("ap/{id}")
    public ResponseEntity<ApplicantDTO> updateApplicant(@PathVariable String id, @RequestBody ApplicantDTO applicantDTO) {
        return new ResponseEntity<>(applicantService.update(id, applicantDTO), HttpStatus.OK);
    }

    @PostMapping("/ap")
    public ResponseEntity<ApplicantDTO> addApplicant(@RequestBody ApplicantDTO applicantDTO) {
        return new ResponseEntity<>(applicantService.save(applicantDTO), HttpStatus.CREATED);
    }
}
