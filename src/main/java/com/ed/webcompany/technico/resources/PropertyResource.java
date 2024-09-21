package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.services.PropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Path;

/**
 *
 * @author User
 */
@Path("property")
public class PropertyResource {
    
    @Inject
    private PropertyService propertyService;
    
}
