package com.ed.webcompany.technico.repositories;

import com.ed.webcompany.technico.models.Property;
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
public class PropertyRepository implements Repository<Property> {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Property> findById(Long id) {
        try {
            entityManager.getTransaction().begin();
            Property property = entityManager.find(getEntityClass(), id);
            entityManager.getTransaction().commit();
            return Optional.of(property);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public List<Property> findAll() {
        TypedQuery<Property> query = entityManager.createQuery("from " + getEntityClassName(), getEntityClass());
        return query.getResultList();
    }

    /**
     * Returns a List<Properties> with all the properties that the given owner
     * has.
     */
//    public List<Property> findAll(PropertyOwner owner) {
//        TypedQuery<Property> query = entityManager.createQuery("from " + getEntityClassName()
//                + " where " + PropertyOwner.class.getName() + " = " + owner,
//                 getEntityClass());
//        return query.getResultList();
//    }
    @Override
    @Transactional
    public Long save(Property property) {
        try {
            if (property.getPropertyId() == null) {
                entityManager.persist(property);
            } else {
                entityManager.merge(property);
            }

            return property.getPropertyId();
        }catch (Exception e) {
            Logger.getLogger(Property.class.getName()).log(Level.SEVERE, "Unable to save the data due to an error", e);
            return -1L;
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) {
        Property persistentInstance = entityManager.find(getEntityClass(), id);
        if (persistentInstance != null) {

            try {
                entityManager.remove(persistentInstance);
            } catch (Exception e) {
                System.out.println("ERROR!!! Could NOT delete this item...");
                return false;
            }
            return true;
        }
        return false;
    }

    private Class<Property> getEntityClass() {
        return Property.class;
    }

    private String getEntityClassName() {
        return Property.class.getName();
    }
}
