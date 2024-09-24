package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.exceptions.OwnerException;
import com.ed.webcompany.technico.models.PropertyOwner;
import com.ed.webcompany.technico.services.OwnerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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

    /**
     * Path: Technico/resources/owner/getAllOwners
     * Doesn't contain any additional parameters.
     * @return A List<PropertyOwner> with the app's whole user list.
     */
    @Path("getAllOwners")
    @GET
    @Produces("text/json")
    public List<PropertyOwner> getOwners() {
        return ownerService.getAllOwners();
    }

    /**
     * Path: Technico/resources/owner/getOwnerByVat/{the VAT number of the user to find}
     * @param vat
     * @return A PropertyOwner with the user found or with (id:-1) and (name:{Error message}) if the user wasn't found
     */
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

    /**
     * Path: Technico/resources/owner/getOwnerByEmail/{the email of the user to find}
     * @param email
     * @return A PropertyOwner with the user found or with (id:-1) and (name:{Error message}) if the user wasn't found
     */
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

    /**
     * Path: Technico/resources/owner/login/{the email of the user to log in}/{the password of the user to log in}
     * @param email
     * @param password
     * @return A PropertyOwner with the logged in user or with (id:-1) and (name:{Error message}) if the log in process wasn't completed successfully
     */
    @Path("login/{email}/{password}")
    @GET
    @Produces("text/json")
    public PropertyOwner login(@PathParam("email") String email,
            @PathParam("password") String password) {
        return ownerService.login(email, password);
    }

    /**
     * Path: Technico/resources/owner/saveOwner
     * Requires as  body: a compatible json file with the user to save
     * If it is a new user to save DO NOT include the id
     * If the user exists and this method is called to update the user
     * include the id of the user in the json body.
     * @param owner
     * @return the id of the user saved or -1 if an error occured while saving
     */
    @Path("saveOwner")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveCustomer(PropertyOwner owner) {
        return ownerService.saveOwner(owner);
    }

    /**
     * Path: Technico/resources/owner/deleteOwner/{the id of the user to be deleted}
     * @param ownerId
     * @return Boolean true if deletion was successful and false if it wasn't
     */
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
