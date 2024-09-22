package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.exceptions.OwnerException;
import com.ed.webcompany.technico.models.PropertyOwner;
import com.ed.webcompany.technico.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
@Path("owner")
public class OwnerResource {

    @Inject
    private OwnerService ownerService;

    @Path("getAllOwners")
    @GET
    @Produces("text/json")
    public List<PropertyOwner> getOwners() {
        return ownerService.getAllOwners();
    }

    @Path("getOwnerByVat/{vat}")
    @GET
    @Produces("text/json")
    public PropertyOwner getOwnerByVat(@PathParam("vat") String vat) {
        try {
            return ownerService.searchOwnerByVat(vat);
        } catch (Exception e) {
            PropertyOwner owner = new PropertyOwner();
            owner.setName(e.getMessage());
            owner.setOwnerId(-1L);
            return owner;
        }
    }

    @Path("getOwnerByUsername/{username}")
    @GET
    @Produces("text/json")
    public PropertyOwner getOwnerByUsername(@PathParam("username") String username) {
        try {
            return ownerService.searchOwnerByUsername(username);
        } catch (Exception e) {
            PropertyOwner owner = new PropertyOwner();
            owner.setName(e.getMessage());
            owner.setOwnerId(-1L);
            return owner;
        }
    }
    
    @Path("getOwnerByEmail/{email}")
    @GET
    @Produces("text/json")
    public PropertyOwner getOwnerByEmail(@PathParam("email") String email) {
        try {
            return ownerService.searchOwnerByEmail(email);
        } catch (Exception e) {
            PropertyOwner owner = new PropertyOwner();
            owner.setName(e.getMessage());
            owner.setOwnerId(-1L);
            return owner;
        }
    }

    @Path("saveOwner")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveCustomer(PropertyOwner owner) {
        return ownerService.saveOwner(owner);
    }

    @Path("deleteOwner/{ownerId}")
    @DELETE
    @Produces("application/json")
    public Boolean deleteCustomer(@PathParam("ownerId") Long ownerId) {
        try {
            return ownerService.deleteOwner(ownerId);
        } catch (OwnerException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(OwnerResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
