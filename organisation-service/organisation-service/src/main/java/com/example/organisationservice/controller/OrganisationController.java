package com.example.organisationservice.controller;

import com.example.organisationservice.dto.OrganisationDto;
import com.example.organisationservice.service.OrganisationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organisations")
@AllArgsConstructor
public class OrganisationController {
    @Autowired
    private OrganisationService organisationService;

    @PostMapping
    public ResponseEntity<OrganisationDto> saveOrganisation(@RequestBody OrganisationDto organisationDto){
         OrganisationDto savedOrganisation =  organisationService.saveOrganosation(organisationDto);
        return new ResponseEntity<>(savedOrganisation, HttpStatus.CREATED);

    }


    @GetMapping("{code}")
    public  ResponseEntity<OrganisationDto> getOrganisation(@PathVariable("code") String code){
        OrganisationDto getOrganisation = organisationService.getOrganisationByCode(code);
        return ResponseEntity.ok(getOrganisation);
    }

}
