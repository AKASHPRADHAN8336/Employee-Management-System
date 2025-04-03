package com.example.organisationservice.mapper;

import com.example.organisationservice.dto.OrganisationDto;
import com.example.organisationservice.entity.Organisation;

public class OrganisationMapper {

    public static OrganisationDto maoToOrganisationDto(Organisation organisation){
        OrganisationDto organisationDto = new OrganisationDto(
                organisation.getId(),
                organisation.getOrganisationName(),
                organisation.getOrganisationDescription(),
                organisation.getOrganisationCode(),
                organisation.getCreatedDate()
        );

        return organisationDto;
    }


    public static Organisation mapToOrganisation(OrganisationDto organisationDto){
        Organisation organisation = new Organisation(
                organisationDto.getId(),
                organisationDto.getOrganisationName(),
                organisationDto.getOrganisationDescription(),
                organisationDto.getOrganisationCode(),
                organisationDto.getCreatedDate()
        );

        return organisation;
    }
}
