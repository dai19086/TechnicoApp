package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.services.ManagerService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("gen")
public class JakartaEE10Resource {
    
    @Inject
    private ManagerService managerService;
    
    @GET
    @Path("ping")
    @Produces("Application/json")
    public Response ping(){
        String getData = managerService.doWork("data");
        return Response
                .ok("ping......" + getData)
                .build();
    }
}
