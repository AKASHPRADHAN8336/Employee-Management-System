package com.example.organisationservice.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "organisation")
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String organisationName;
    private String organisationDescription;
    @Column(nullable = false,unique = true)
    private String organisationCode;
    @CreationTimestamp
    private LocalDateTime createdDate;

    public Organisation(Long id, String organisationName, String organisationDescription, String organisationCode, LocalDateTime createdDate) {
        this.id = id;
        this.organisationName = organisationName;
        this.organisationDescription = organisationDescription;
        this.organisationCode = organisationCode;
        this.createdDate = createdDate;
    }


    public Organisation(){

    }

    public Organisation(String organisationCode){
        this.organisationCode=organisationCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisationDescription() {
        return organisationDescription;
    }

    public void setOrganisationDescription(String organisationDescription) {
        this.organisationDescription = organisationDescription;
    }

    public String getOrganisationCode() {
        return organisationCode;
    }

    public void setOrganisationCode(String organisationCode) {
        this.organisationCode = organisationCode;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
