package com.ed.webcompany.technico.repositories;

import com.ed.webcompany.technico.exceptions.PropertyRepairException;
import com.ed.webcompany.technico.models.PropertyRepair;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
public class PropertyRepairRepository implements Repository<PropertyRepair> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<PropertyRepair> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            PropertyRepair t = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(t);
        } catch (Exception e) {
            //log.debug("An exception occured");
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public List<PropertyRepair> findAll() {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find all PropertyRepairs", e);
        }
    }

    @Override
    @Transactional
    public Long save(PropertyRepair repair) {
        try {
            if (repair.getRepairId() == null) {
                entityManager.persist(repair);
            } else {
                entityManager.merge(repair);
            }
            return repair.getRepairId();
        } catch (Exception e) {
            Logger.getLogger(PropertyRepair.class.getName()).log(Level.SEVERE, "Unable to save the data due to an error", e);
            return -1L;
        }

    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        PropertyRepair persistentInstance = entityManager.find(getEntityClass(), id);
        if (persistentInstance != null) {

            try {
                entityManager.remove(persistentInstance);
                return true;
            } catch (Exception e) {
                Logger.getLogger(PropertyRepair.class.getName()).log(Level.SEVERE, "Unable to delete the data due to an error", e);
            }
        }
        return false;
    }

    public List<PropertyRepair> findBySubmissionDateBetween(String startDate, String endDate) {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery(
                    "SELECT r FROM PropertyRepair r WHERE r.submissionDate BETWEEN :startDate AND :endDate", PropertyRepair.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find PropertyRepairs between dates", e);
        }
    }

    public List<PropertyRepair> findByOwnerId(Long ownerId) {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery(
                    "SELECT r FROM PropertyRepair r WHERE r.property.propertyOwner.ownerId = :ownerId", PropertyRepair.class);
            query.setParameter("ownerId", ownerId);
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find PropertyRepairs by owner id: " + ownerId, e);
        }
    }

    public List<PropertyRepair> findByOwnerVAT(String ownerVAT) {
        try {
            TypedQuery<PropertyRepair> query = entityManager.createQuery(
                    "SELECT r FROM PropertyRepair r WHERE r.owner.vatNumber = :ownerVAT", PropertyRepair.class);
            query.setParameter("ownerVAT", ownerVAT);
            return query.getResultList();
        } catch (Exception e) {
            throw new PropertyRepairException("Failed to find PropertyRepairs by owner VAT: " + ownerVAT, e);
        }
    }

    private Class<PropertyRepair> getEntityClass() {
        return PropertyRepair.class;
    }

    private String getEntityClassName() {
        return PropertyRepair.class.getName();
    }

}
