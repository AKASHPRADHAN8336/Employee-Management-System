package com.example.organisationservice.service.Impl;

import com.example.organisationservice.dto.OrganisationDto;
import com.example.organisationservice.entity.Organisation;
import com.example.organisationservice.mapper.OrganisationMapper;
import com.example.organisationservice.repository.OrganisationRepository;
import com.example.organisationservice.service.OrganisationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganisationServiceImpl implements OrganisationService {
    @Autowired
    private OrganisationRepository organisationRepository;
    @Override
    public OrganisationDto saveOrganosation(OrganisationDto organisationDto) {

        Organisation organisation = OrganisationMapper.mapToOrganisation(organisationDto);
         Organisation savedOrganisation =   organisationRepository.save(organisation);

        return OrganisationMapper.maoToOrganisationDto(savedOrganisation);
    }

    @Override
    public OrganisationDto getOrganisationByCode(String code) {

        Organisation organisation = organisationRepository.findByOrganisationCode(code);

        return OrganisationMapper.maoToOrganisationDto(organisation);
    }
}
