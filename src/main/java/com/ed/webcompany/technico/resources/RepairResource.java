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
    
    /**
     * Path: Technico/resources/repair/getAllRepairs
     * Doesn't contain any additional parameters.
     * @return A List<PropertyRepair> with the app's whole repairs list.
     */
    @Path("getAllRepairs")
    @GET
    @Produces("text/json")
    public List<PropertyRepair> getProperties() {
        return repairService.getPropertyRepairs();
    }
    
    /**
     * Path: Technico/resources/repair/getAllRepairs/{the VAT number of the owner whose repairs we want to get}
     * @param ownerVAT
     * @return A List<PropertyRepair> with the owner's whole repairs list.
     */
    @Path("getAllRepairs/{ownerVAT}")
    @GET
    @Produces("text/json")
    public List<PropertyRepair> getProperties(@PathParam("ownerVAT") String ownerVAT) {
        return repairService.getOwnerRepairs(ownerVAT);
    }
    
    /**
     * Path: Technico/resources/repair/saveRepair
     * Requires as  body: a compatible json file with the repair to save
     * If it is a new repair to save DO NOT include the id
     * If the repair exists and this method is called to update it
     * include the id of the repair in the json body.
     * @param repair
     * @return the id of the repair saved or -1 if an error occured while saving
     */
    @Path("saveRepair")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Long saveRepair(PropertyRepair repair) {
        return repairService.savePropertyRepair(repair);
    }
    
    /**
     * Path: Technico/resources/repair/deleteRepair/{the id of the repair to be deleted}
     * @param repairId
     * @return Boolean true if deletion was successful and false if it wasn't
     */
    @Path("deleteRepair/{repairId}")
    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    public boolean deleteProperty(@PathParam("repairId") Long repairId) {
        return repairService.deletePropertyRepair(repairId);
    }
    
}
