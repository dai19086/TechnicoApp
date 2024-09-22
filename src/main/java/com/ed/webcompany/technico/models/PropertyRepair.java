package com.ed.webcompany.technico.models;

import com.ed.webcompany.technico.enumerations.RepairStatus;
import com.ed.webcompany.technico.enumerations.RepairType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PropertyRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long repairId;

    @ManyToOne
    @JoinColumn(referencedColumnName = "propertyId")
    private Property property;

    @Enumerated(EnumType.STRING)
    private RepairType typeOfRepair;
    
    private String shortDescription;
    private String submissionDate;
    private String workDescription;
    private String proposedStartDate;
    private String proposedEndDate;
    private double proposedCost;
    private boolean ownerAcceptance;
    
    @Enumerated(EnumType.STRING)
    private RepairStatus status;
    
    private String actualStartDate;
    private String actualEndDate;

}
