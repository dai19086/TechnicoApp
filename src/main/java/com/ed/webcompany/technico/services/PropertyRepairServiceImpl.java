package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.enumerations.RepairStatus;
import com.ed.webcompany.technico.enumerations.RepairType;
import com.ed.webcompany.technico.models.Property;
import com.ed.webcompany.technico.models.PropertyRepair;
import com.ed.webcompany.technico.repositories.PropertyRepairRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PropertyRepairServiceImpl implements PropertyRepairService {

    @Inject
    private PropertyRepairRepository propertyRepairRepository;

    @Override
    public PropertyRepair createPropertyRepair(Property property, RepairType typeOfRepair, String submissionDate,
            String shortDescription, String workDescription,
            String proposedStartDate, String proposedEndDate,
            double proposedCost, boolean ownerAcceptance) {

        PropertyRepair repair = PropertyRepair.builder()
                .property(property)
                .typeOfRepair(typeOfRepair)
                .shortDescription(shortDescription)
                .workDescription(workDescription)
                .submissionDate(submissionDate)
                .proposedStartDate(proposedStartDate)
                .proposedEndDate(proposedEndDate)
                .proposedCost(proposedCost)
                .ownerAcceptance(ownerAcceptance)
                .status(RepairStatus.PENDING)
                .build();
        return repair;
    }

    @Override
    public Long savePropertyRepair(PropertyRepair propertyRepair) {
        return propertyRepairRepository.save(propertyRepair);
    }

    @Override
    public List<PropertyRepair> getPropertyRepairs() {
        return propertyRepairRepository.findAll();
    }

    @Override
    public boolean deletePropertyRepair(Long id) {
        return propertyRepairRepository.deleteById(id);
    }

    @Override
    public List<PropertyRepair> searchPropertyRepairsByDateRange(String startDate, String endDate) {
        return propertyRepairRepository.findBySubmissionDateBetween(startDate, endDate);
    }

    @Override
    public List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId) {
        return propertyRepairRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<PropertyRepair> getOwnerRepairs(String ownerVAT) {
        return getPropertyRepairs().stream()
                .filter(repair -> repair.getProperty().getPropertyOwner().getVatNumber().equals(ownerVAT))
                .collect(Collectors.toList());
    }

    @Override
    public List<PropertyRepair> getUnansweredOwnerRepairs(String ownerVAT) {
        return getPropertyRepairs().stream()
                .filter(repair -> repair.getProperty().getPropertyOwner().getVatNumber().equals(ownerVAT))
                .filter(repair -> !repair.isOwnerAcceptance())
                .collect(Collectors.toList());
    }
}
