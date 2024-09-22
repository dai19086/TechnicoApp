package com.ed.webcompany.technico.resources;

import com.ed.webcompany.technico.models.PropertyRepair;
import com.ed.webcompany.technico.services.PropertyRepairService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import java.util.List;

/**
 *
 * @author User
 */
@Path("repair")
public class RepairResource {
    
    @Inject
    private PropertyRepairService repairService;
    
    @Path("getAllRepairs")
    @GET
    @Produces("text/json")
    public List<PropertyRepair> getProperties() {
        return repairService.getPropertyRepairs();
    }
    
    @Path("getAllRepairs/{ownerVAT}")
    @GET
    @Produces("text/json")
    public List<PropertyRepair> getProperties(@PathParam("ownerVAT") String ownerVAT) {
        return repairService.getOwnerRepairs(ownerVAT);
    }
    
    @Path("saveRepair")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveRepair(PropertyRepair repair) {
        return repairService.savePropertyRepair(repair);
    }
    
    @Path("deleteRepair/{repairId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteProperty(@PathParam("repairId") Long repairId) {
        return repairService.deletePropertyRepair(repairId);
    }
    
}
