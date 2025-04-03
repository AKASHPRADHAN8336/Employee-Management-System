package com.example.organisationservice.service;

import com.example.organisationservice.dto.OrganisationDto;

public interface OrganisationService {

    OrganisationDto saveOrganosation(OrganisationDto organisationDto);

    OrganisationDto getOrganisationByCode(String code);
}
