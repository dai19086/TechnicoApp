package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.enumerations.RepairType;
import com.ed.webcompany.technico.models.Property;
import com.ed.webcompany.technico.models.PropertyRepair;
import java.util.List;

public interface PropertyRepairService {

    PropertyRepair createPropertyRepair(Property property, RepairType typeOfRepair,
            String shortDescription, String workDescription, String submissionDate,
            String proposedStartDate, String proposedEndDate,
            double proposedCost, boolean ownerAcceptance);

    Long savePropertyRepair(PropertyRepair propertyRepair);

    List<PropertyRepair> getPropertyRepairs();

    List<PropertyRepair> getOwnerRepairs(String ownerVAT);

    List<PropertyRepair> getUnansweredOwnerRepairs(String ownerVAT);

    void deletePropertyRepair(Long id);

    List<PropertyRepair> searchPropertyRepairsByDateRange(String startDate, String endDate);

    List<PropertyRepair> searchPropertyRepairsByOwnerId(Long ownerId);
}
