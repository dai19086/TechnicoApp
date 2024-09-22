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

    @Path("getAllProperties")
    @GET
    @Produces("text/json")
    public List<Property> getProperties() {
        return propertyService.getAllProperties();
    }

    @Path("getPropertyByE9/{e9}")
    @GET
    @Produces("text/json")
    public Property getPropertyByVat(@PathParam("e9") String e9) {
        Optional<Property> propertyFound = propertyService.findPropertyByE9(e9);
        if (propertyFound.isEmpty()) {
            return propertyService.createProperty("-1", "Property Not Found", -1, null, null);
        } else {
            return propertyFound.get();
        }
    }

    @Path("saveProperty")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveProperty(Property property) {
        return propertyService.saveProperty(property);
    }

    @Path("deleteProperty/{propertyId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteProperty(@PathParam("propertyId") Long propertyId) {
        return propertyService.deleteProperty(propertyId);
    }

}
