package com.example.organisationservice.repository;

import com.example.organisationservice.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation,Long> {


    Organisation findByOrganisationCode(String code);
}
