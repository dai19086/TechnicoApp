package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.enumerations.UserType;
import com.ed.webcompany.technico.exceptions.OwnerException;
import com.ed.webcompany.technico.models.PropertyOwner;
import java.util.List;

public interface OwnerService {

    PropertyOwner createOwner(String vatNumber, String name, String surname, String address, String phoneNumber,
            String email, String password, UserType type);

    Long saveOwner(PropertyOwner propertyowner);

    PropertyOwner login(String email, String password);

    PropertyOwner searchOwnerByEmail(String email) throws OwnerException;

    PropertyOwner searchOwnerByVat(String vatNumber) throws OwnerException;

    PropertyOwner searchOwnerById(String id) throws OwnerException, NumberFormatException;

    Boolean deleteOwner(Long id) throws OwnerException, NumberFormatException;

    List<PropertyOwner> getAllOwners();

//    void updateOwnerEmail(Long ownerId, String newEmail) throws OwnerException;
//
//    void updateOwnerPassword(Long ownerId, String newPassword) throws OwnerException;
//
//    void updateOwnerAddress(Long ownerId, String newAddress) throws OwnerException;
}
