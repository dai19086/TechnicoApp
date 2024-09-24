package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.enumerations.PropertyType;
import com.ed.webcompany.technico.models.Property;
import com.ed.webcompany.technico.models.PropertyOwner;
import com.ed.webcompany.technico.repositories.PropertyRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RequestScoped
public class PropertyServiceImpl implements PropertyService {

    
    @Inject
    private PropertyRepository propertyRepository;

    @Override
    public Property createProperty(String e9, String address, int yearOfConstruction, PropertyType typeOfProperty, PropertyOwner owner) {

        return Property.builder()
                .e9(e9)
                .address(address)
                .yearOfConstruction(yearOfConstruction)
                .typeOfProperty(typeOfProperty)
                .propertyOwner(owner)
                .build();
    }

    @Override
    public Long saveProperty(Property property) {

        return propertyRepository.save(property);
    }

    @Override
    public boolean deleteProperty(Long id) {
        return propertyRepository.deleteById(id);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findPropertiesByVAT(String ownerVat) {

//        OwnerService ownerService = new OwnerServiceImpl(new OwnerRepository(JpaUtil.getEntityManager()));
//        PropertyOwner owner = ownerService.searchOwnerByVat(ownerVat);
//        return propertyRepository.findAll(owner);
        List<Property> allProperties = propertyRepository.findAll();
        return allProperties.stream()
                .filter(property -> property.getPropertyOwner().getVatNumber().equals(ownerVat))
                .collect(Collectors.toList());

    }

    @Override
    public Optional<Property> findPropertyByID(Long id) throws InvalidParameterException {
        if (id == null) {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, "Null property ID was given");
            throw new InvalidParameterException();
        }
        Optional<Property> property = propertyRepository.findById(id);
        return property;
    }

    @Override
    public Optional<Property> findPropertyByE9(String e9) {
        if (e9 == null) {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, "Null property E9 was given");
            throw new InvalidParameterException();
        }
        Optional<Property> property;
        List<Property> allProperties = propertyRepository.findAll();
        List<Property> e9Properties = allProperties.stream().filter(p -> p.getE9().equals(e9)).collect(Collectors.toList());
        if (e9Properties.size() == 0) {
            property = Optional.empty();
        } else {
            property = Optional.of(e9Properties.get(0));
        }
        return property;
    }
}
