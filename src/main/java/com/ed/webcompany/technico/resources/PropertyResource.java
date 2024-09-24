package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.models.Property;
import com.ed.webcompany.technico.services.PropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author User
 */
@Path("property")
public class PropertyResource {

    @Inject
    private PropertyService propertyService;

    /**
     * Path: Technico/resources/property/getAllProperties
     * Doesn't contain any additional parameters.
     * @return A List<Property> with the app's whole property list.
     */
    @Path("getAllProperties")
    @GET
    @Produces("text/json")
    public List<Property> getProperties() {
        return propertyService.getAllProperties();
    }

    /**
     * Path: Technico/resources/property/getPropertyByE9/{the E9 number of the property to find}
     * @param e9
     * @return The Property found or a Property object with (id:-1) and (name:{Error message}) if it wasn't found
     */
    @Path("getPropertyByE9/{e9}")
    @GET
    @Produces("text/json")
    public Property getPropertyByVat(@PathParam("e9") String e9) {
        Optional<Property> propertyFound = propertyService.findPropertyByE9(e9);
        if (propertyFound.isEmpty()) {
            Property propertyNotFound = propertyService.createProperty(null, "Property Not Found", -1, null, null);
            propertyNotFound.setPropertyId(-1L);
            return propertyNotFound;
        } else {
            return propertyFound.get();
        }
    }

    /**
     * Path: Technico/resources/property/saveProperty
     * Requires as  body: a compatible json file with the property to save
     * If it is a new property to save DO NOT include the id
     * If the property exists and this method is called to update it
     * include the id of the property in the json body.
     * @param property
     * @return the id of the property saved or -1 if an error occured while saving
     */
    @Path("saveProperty")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveProperty(Property property) {
        return propertyService.saveProperty(property);
    }

    /**
     * Path: Technico/resources/property/deleteProperty/{the id of the property to be deleted}
     * @param propertyId
     * @return Boolean true if deletion was successful and false if it wasn't
     */
    @Path("deleteProperty/{propertyId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteProperty(@PathParam("propertyId") Long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

}
