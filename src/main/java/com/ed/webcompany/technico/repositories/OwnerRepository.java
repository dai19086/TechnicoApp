package com.ed.webcompany.technico.repositories;

import com.ed.webcompany.technico.models.PropertyOwner;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class OwnerRepository implements Repository<PropertyOwner> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    /**
     * Finds a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be retrieved.
     * @return An Optional containing the PropertyOwner if found, or an empty
     * Optional if not.
     */
    @Override
    @Transactional
    public Optional<PropertyOwner> findById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner == null) {
            return Optional.empty();
        }
        return Optional.of(propertyOwner);
    }

    /**
     * Retrieves all PropertyOwner entities from the database.
     *
     * @return A list of all PropertyOwner entities.
     */
    @Override
    @Transactional
    public List<PropertyOwner> findAll() {
        TypedQuery<PropertyOwner> query = entityManager.createQuery("from PropertyOwner", PropertyOwner.class);
        return query.getResultList();

    }

    /**
     * Saves a new PropertyOwner or updates an existing one in the database.
     *
     * @param owner The PropertyOwner to be saved or updated.
     * @return An Optional containing the saved PropertyOwner if successful, or
     * an empty Optional if not.
     */
    @Override
    @Transactional
    public Long save(PropertyOwner owner) {
        try {
            if (owner.getOwnerId() == null) {
                entityManager.persist(owner);
            } else {
                entityManager.merge(owner);
            }
            return owner.getOwnerId();
        } catch (Exception e) {
            Logger.getLogger(PropertyOwner.class.getName()).log(Level.SEVERE, "Unable to save the data due to an error", e);
            return -1L;
        }
    }

    /**
     * Deletes a PropertyOwner by their ID.
     *
     * @param id The ID of the PropertyOwner to be deleted.
     * @return true if the PropertyOwner was successfully deleted, false
     * otherwise.
     */
    @Override
    @Transactional
    public boolean deleteById(Long id) {
        PropertyOwner propertyOwner = entityManager.find(PropertyOwner.class, id);
        if (propertyOwner != null) {
            try {
                entityManager.remove(propertyOwner);
                return true;
            } catch (Exception e) {
                Logger.getLogger(PropertyOwner.class.getName()).log(Level.SEVERE, "Unable to delete the data due to an error", e);
            }

        }
        return false;
    }

    /**
     * Finds a PropertyOwner by their email address.
     *
     * @param email The email address of the PropertyOwner to be retrieved.
     * @return The PropertyOwner with the specified email address.
     */
    @Transactional
    public PropertyOwner findOwnerByEmail(String email) throws NotFoundException {
        TypedQuery<PropertyOwner> typedQuery = entityManager.createQuery("SELECT p FROM PropertyOwner p WHERE p.email = :data", PropertyOwner.class);
        typedQuery.setParameter("data", email);
        return typedQuery.getSingleResult();
    }

    /**
     * Finds a PropertyOwner by their VAT number.
     *
     * @param vat The VAT number of the PropertyOwner to be retrieved.
     * @return The PropertyOwner with the specified VAT number.
     */
    @Transactional
    public PropertyOwner findOwnerByVat(String vat) throws NotFoundException {
        TypedQuery<PropertyOwner> typedQuery = entityManager.createQuery("SELECT p FROM PropertyOwner p WHERE p.vatNumber = :data", PropertyOwner.class);
        typedQuery.setParameter("data", vat);
        return typedQuery.getSingleResult();
    }
}
