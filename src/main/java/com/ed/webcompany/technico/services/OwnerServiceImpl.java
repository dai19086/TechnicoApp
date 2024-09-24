package com.ed.webcompany.technico.services;

import com.ed.webcompany.technico.enumerations.UserType;
import com.ed.webcompany.technico.exceptions.OwnerException;
import com.ed.webcompany.technico.models.PropertyOwner;
import com.ed.webcompany.technico.repositories.OwnerRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * The OwnerServiceImpl class provides the implementation for the OwnerService
 * interface. It defines methods for managing PropertyOwner entities, including
 * creating, retrieving, updating, and deleting PropertyOwners. This class
 * interacts with the OwnerRepository to perform the necessary database
 * operations.
 *
 * @author matina
 */
@RequestScoped
public class OwnerServiceImpl implements OwnerService {

    @Inject
    private OwnerRepository ownerRepository;

    /**
     * Creates a new PropertyOwner with the specified details.
     *
     * @param vatNumber The VAT number of the PropertyOwner.
     * @param name The first name of the PropertyOwner.
     * @param surname The last name of the PropertyOwner.
     * @param address The address of the PropertyOwner.
     * @param phoneNumber The phone number of the PropertyOwner.
     * @param email The email address of the PropertyOwner.
     * @param username The username for the PropertyOwner's account.
     * @param password The password for the PropertyOwner's account.
     * @return The created PropertyOwner.
     */
    @Override
    public PropertyOwner createOwner(String vatNumber, String name, String surname, String address, String phoneNumber,
            String email, String password, UserType type) {

        return PropertyOwner.builder()
                .vatNumber(vatNumber)
                .name(name)
                .surname(surname)
                .address(address)
                .phoneNumber(phoneNumber)
                .email(email)
                .password(password)
                .typeOfUser(type)
                .build();
    }

    /**
     * Saves a PropertyOwner entity to the database.
     *
     * @param propertyOwner The PropertyOwner to be saved.
     * @return The ID of the saved PropertyOwner.
     */
    @Override
    public Long saveOwner(PropertyOwner propertyOwner) {

        return ownerRepository.save(propertyOwner);
    }

    /**
     * Retrives the user with the given email and checks if the given password
     * matches with the saved password. If it does return the PropertyOwner to
     * log in. If not returns a PropertyOwner with an id of -1, that signal an
     * unsuccessful log in, and a name that explains the reason for the
     * unsuccessful log in
     *
     * @param email
     * @param password
     * @return PropertyOwner object with the result of the log in process
     */
    @Override
    public PropertyOwner login(String email, String password) {
        String errorMessage = "Everything was executed correctly";
        if (email == null || !email.contains("@")) {
            errorMessage = "Invalid email";
        }
        if (password == null) {
            errorMessage = "Please enter the password to log in...";
        }
        PropertyOwner userToSignIn;
        try {
            userToSignIn = ownerRepository.findOwnerByEmail(email);
            if (password.equals(userToSignIn.getPassword())) {
                return userToSignIn;
            } else {
                errorMessage = "Wrong password please try again....";
            }
        } catch (Exception e) {
            errorMessage = "User not found.....";
        }
        userToSignIn = createOwner(null, errorMessage, null, null, null, null, null, UserType.OWNER);
        userToSignIn.setOwnerId(-1L);
        return userToSignIn;
    }

    /**
     * Retrieves a list of all PropertyOwners.
     *
     * @return A list of all PropertyOwner entities.
     */
    @Override
    public List<PropertyOwner> getAllOwners() {
        return ownerRepository.findAll();
    }

    /**
     * Searches for a PropertyOwner by their email address.
     *
     * @param email The email address of the PropertyOwner to be searched.
     * @return The PropertyOwner associated with the specified email.
     * @throws OwnerException If the email is invalid or no owner is found with
     * that email.
     */
    @Override
    public PropertyOwner searchOwnerByEmail(String email) throws OwnerException {
        if (email == null || !email.contains("@")) {
            throw new OwnerException("Invalid email");
        }
        return ownerRepository.findOwnerByEmail(email);
    }

    /**
     * Searches for a PropertyOwner by their VAT number.
     *
     * @param vatNumber The VAT number of the PropertyOwner to be searched.
     * @return The PropertyOwner associated with the specified VAT number.
     * @throws OwnerException If the VAT number is invalid or no owner is found
     * with that VAT number.
     */
    @Override
    public PropertyOwner searchOwnerByVat(String vatNumber) throws OwnerException {
        if (vatNumber == null) {
            throw new OwnerException("Invalid vat number");
        }
        return ownerRepository.findOwnerByVat(vatNumber);
    }

    /**
     * Searches for a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be searched.
     * @return The PropertyOwner associated with the specified ID.
     * @throws OwnerException If the ID is invalid or no owner is found with
     * that ID.
     * @throws NumberFormatException If the ID cannot be parsed to a Long.
     */
    @Override
    public PropertyOwner searchOwnerById(String id) throws OwnerException {
        if (id == null) {
            throw new OwnerException("Invalid id");
        }
        Long ownerId = Long.parseLong(id);
        Optional<PropertyOwner> owner = ownerRepository.findById(ownerId);
        if (owner.isPresent()) {
            return owner.get();
        }
        throw new OwnerException("id not found");
    }

    /**
     * Deletes a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be deleted.
     * @return true if the PropertyOwner was successfully deleted, false
     * otherwise.
     * @throws OwnerException If the ID is invalid or the owner cannot be
     * deleted.
     * @throws NumberFormatException If the ID cannot be parsed to a Long.
     */
    @Override
    public Boolean deleteOwner(Long id) throws OwnerException, NumberFormatException {
        if (id == null) {
            throw new OwnerException("Invalid id");
        }
        return ownerRepository.deleteById(id);
    }

//    /**
//     * Updates the address of an existing PropertyOwner.
//     *
//     * @param ownerId The ID of the PropertyOwner whose address is to be
//     * updated.
//     * @param newAddress The new address to be set for the PropertyOwner.
//     * @throws OwnerException If the owner cannot be found or the new address is
//     * invalid.
//     */
//    @Override
//    public void updateOwnerAddress(Long ownerId, String newAddress) throws OwnerException {
//        Optional<PropertyOwner> optionalOwner = ownerRepository.findById(ownerId);
//        if (optionalOwner.isPresent()) {
//            PropertyOwner owner = optionalOwner.get();
//            owner.setAddress(newAddress);
//            ownerRepository.save(owner);
//        } else {
//            throw new OwnerException("Owner not found");
//        }
//    }
//    /**
//     * Updates the email address of an existing PropertyOwner.
//     *
//     * @param ownerId The ID of the PropertyOwner whose email is to be updated.
//     * @param newEmail The new email address to be set for the PropertyOwner.
//     * @throws OwnerException If the owner cannot be found or the new email is
//     * invalid.
//     */
//    @Override
//    public void updateOwnerEmail(Long ownerId, String newEmail) throws OwnerException {
//        if (newEmail == null || !newEmail.contains("@")) {
//            throw new OwnerException("Invalid email");
//        }
//        Optional<PropertyOwner> optionalOwner = ownerRepository.findById(ownerId);
//        if (optionalOwner.isPresent()) {
//            PropertyOwner owner = optionalOwner.get();
//            owner.setEmail(newEmail);
//            ownerRepository.save(owner);
//        } else {
//            throw new OwnerException("Owner not found");
//        }
//    }
//    /**
//     * Updates the password of an existing PropertyOwner.
//     *
//     * @param ownerId The ID of the PropertyOwner whose password is to be
//     * updated.
//     * @param newPassword The new password to be set for the PropertyOwner.
//     * @throws OwnerException If the owner cannot be found or the new password
//     * is invalid.
//     */
//    @Override
//    public void updateOwnerPassword(Long ownerId, String newPassword) throws OwnerException {
//        Optional<PropertyOwner> optionalOwner = ownerRepository.findById(ownerId);
//        if (optionalOwner.isPresent()) {
//            PropertyOwner owner = optionalOwner.get();
//            owner.setPassword(newPassword);
//            ownerRepository.save(owner);
//        } else {
//            throw new OwnerException("Owner not found");
//        }
//    }
}
