package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.exceptions.OwnerException;
import com.ed.webcompany.technico.models.PropertyOwner;
import java.util.List;

/**
 * The OwnerService interface defines the operations available for managing
 * PropertyOwner entities. It includes methods for creating, updating,
 * retrieving, and deleting PropertyOwner data. This service layer interacts
 * with the repository layer to perform business operations.
 *
 * @author matina
 */
public interface OwnerService {

    PropertyOwner createOwner(String vatNumber, String name, String surname, String address, long phoneNumber,
            String email, String username, String password);

    Long saveOwner(PropertyOwner propertyowner);

    PropertyOwner searchOwnerByEmail(String email) throws OwnerException;

    PropertyOwner searchOwnerByVat(String vatNumber) throws OwnerException;

    PropertyOwner searchOwnerById(String id) throws OwnerException, NumberFormatException;

    Boolean deleteOwner(Long id) throws OwnerException, NumberFormatException;

    List<PropertyOwner> getAllOwners();

    void updateOwnerEmail(Long ownerId, String newEmail) throws OwnerException;

    void updateOwnerPassword(Long ownerId, String newPassword) throws OwnerException;

    void updateOwnerAddress(Long ownerId, String newAddress) throws OwnerException;
}
