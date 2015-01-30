package com.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.Device;





@Path("/hello")
public class DeviceService {
	
	private static final String PERSISTENCE_UNIT_NAME = "DevicePersist";
    private static EntityManagerFactory factory;

	
	/* To test the string as output.
	 * url: http://localhost:8080/WebServiceRest/rest/hello/Swapna
	 */
	@GET  
	@Path("/{paramname}")  
  public String sayHello(@PathParam("paramname") String name)
	{   
		String Name = name;
		return " HEllo  your are using Jersey Web service,"+Name;
	}
	
	/* to test JSON as output
	 * url : http://localhost:8080/WebServiceRest/rest/hello/deviceid/122311
	 * 
	 */
	
	@GET
	@Path("/deviceid/{did}")
	//@Path("/id={did}&type={dtype}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody  Device createDevice(@PathParam("did") String devId)
	{   String id=devId;
		Device device=new Device(id, "Apple");
		return device;
	}
	
	
	/* To test JSON as output when two path parameter are passed.   Adding Device in devaicedetails table.
	 * url : http://localhost:8080/WebServiceRest/rest/hello/id/5643/type/Android
	 */
	@POST
	@Path("/id/{did}/type/{dtype}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody  Device createDevice(@PathParam("did") String devId,@PathParam("dtype") String devType)
	{    Device device=new Device(devId, devType);
	 try{
		 
	
	 factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	 EntityManager em = factory.createEntityManager();
	 em.getTransaction().begin();
	 em.persist(device);
     em.getTransaction().commit();
     em.close();
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
	 }
     return device;
	}
	
	
	/* To test JSON as output when two path parameter are passed.   Updating Device in devaicedetails table.
	 * url : http://localhost:8080/WebServiceRest/rest/hello/id/111/new/999
	 */
	
	@PUT
	@Path("/id/{did}/new/{newdid}")
	@Produces(MediaType.APPLICATION_JSON)
	public @ResponseBody  Device updateDevice(@PathParam("did") String devId,@PathParam("newdid") String newdevId)
	{   
		Device updateDevice=null;
		Device newDevice=null;
	   try
		{factory=Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		 EntityManager em = factory.createEntityManager();
		 em.getTransaction().begin();
		 updateDevice=em.find(Device.class, devId);
		 newDevice=new Device(newdevId, updateDevice.getDeviceType());
		 em.remove(updateDevice);
		 em.merge(newDevice);
		 em.getTransaction().commit();
	     em.close();
	     
		}
	   catch(Exception e)
	   {
		   e.printStackTrace(); 
	   }
	   
	   return newDevice;
		
	}
	
}
