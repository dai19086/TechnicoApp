package com.ed.webcompany.technico.services;

import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@RequestScoped
public class ManagerServiceImpl implements ManagerService{
    @PersistenceContext(unitName = "Persistence")
    protected EntityManager em;

    @Override
    public String doWork(String input) {
       return "Ready to process the " + input;
    }
}
