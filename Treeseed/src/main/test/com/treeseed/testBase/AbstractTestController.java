package com.treeseed.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;










import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treeseed.contracts.CampaignResponse;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.ParentUserWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.CatalogPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostNonprofitServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

@WebAppConfiguration
public abstract class AbstractTestController extends AbstractTest {

	protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired  CatalogServiceInterface serviceCatalog;
    @Autowired  NonprofitServiceInterface serviceNonProfit;
    @Autowired  UserGeneralServiceInterface serviceUserGeneral;
    @Autowired	PostNonprofitServiceInterface postNonprofitService;
    @Autowired	DonorServiceInterface donorService;
    @Autowired	CampaignServiceInterface campaignService;
    @Autowired  HttpServletRequest requestHttp;
    
    /**
     * Prepares the test class for execution of web tests. Builds a MockMvc
     * instance. Call this method from the concrete JUnit test class in the
     * <code>@Before</code> setup method.
     */
    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * Prepares the test class for execution of web tests. Builds a MockMvc
     * instance using standalone configuration facilitating the injection of
     * Mockito resources into the controller class.
     * @param controller A controller object to be tested.
     */
    //protected void setUp(Controller controller);

    /**
     * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
     * @param obj The Object to map.
     * @return A String of JSON.
     * @throws JsonProcessingException Thrown if an error occurs while mapping.
     */
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

    /**
     * Maps a String of JSON into an instance of a Class of type T. Uses a
     * Jackson ObjectMapper.
     * @param json A String of JSON.
     * @param clazz A Class of type T. The mapper will attempt to convert the
     *        JSON into an Object of this Class type.
     * @return An Object of type T.
     * @throws JsonParseException Thrown if an error occurs while mapping.
     * @throws JsonMappingException Thrown if an error occurs while mapping.
     * @throws IOException Thrown if an error occurs while mapping.
     */
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, clazz);
    }
    
    
    
    
public  CatalogPOJO getCatalogPOJO(int id){
		
		CatalogWrapper wrapper = serviceCatalog.findCatalogById(id);
		CatalogPOJO pojo = new CatalogPOJO();
		
		PojoUtils.pojoMappingUtility(pojo, wrapper);
		
		return pojo;
		
	}
	
	public  CatalogWrapper getCatalogWrapper(int id){
		return serviceCatalog.findCatalogById(id);
	}
	
	
public  List<CatalogPOJO> getCatalogPOJOs(String type){
		
		List<CatalogWrapper> wrappers =serviceCatalog.getAllByType(type);
		List<CatalogPOJO> pojos = new ArrayList<CatalogPOJO>();
		
		
		CatalogPOJO temp;
		
		for (CatalogWrapper wrapper : wrappers) {
			temp = new CatalogPOJO();
			PojoUtils.pojoMappingUtility(temp, wrapper);
			pojos.add(temp);
		}
		
		
		return pojos;
		
	}
	
	public  List<CatalogWrapper> getCatalogWrappers(String type){
		return serviceCatalog.getAllByType(type);
	}
	
	
	public  CatalogWrapper createRandomCatalog(){
		String random = getRandomString();
		Catalog catalog = new Catalog();
		catalog.setActive(true);
		catalog.setDescription("catalogTest");
		catalog.setEnglish(random);
		catalog.setSpanish(random);
		catalog.setType(random);
		catalog.setName(random);
		
		serviceCatalog.saveCatalog(catalog);
		
		CatalogWrapper wrapper = new CatalogWrapper(catalog);
		
		return wrapper;
		
	}
	
	public  CatalogPOJO convertCatalogWrapper(CatalogWrapper wrapper){
		CatalogPOJO newPOJO = new CatalogPOJO();
		PojoUtils.pojoMappingUtility(newPOJO, wrapper);
		return newPOJO;
	}
    
    
    
    
	public static NonprofitPOJO getNonprofitPOJO(int id){
		//Not Implemented
		return null;
		/*NonprofitWrapper wrapper = service.(id);
		NonprofitPOJO pojo = new NonprofitPOJO();
		
		PojoUtils.pojoMappingUtility(pojo, wrapper);
		
		return pojo;*/
		
	}
	
	public  NonprofitWrapper getNonprofitWrapper(int id){
		//return service.findNonprofitById(id);
		
		//Not Implemented
		return null;
	}
	
	
	public  NonprofitWrapper createRandomNonprofit(){
			
		String random = getRandomString();
		Nonprofit nonprofit = new Nonprofit();

		nonprofit.setActive(true);
		nonprofit.setDescription("description");
		nonprofit.setMision("Mision");
		nonprofit.setName("NGO TEST");
		nonprofit.setReason("Reason");
		nonprofit.setWebPage("www.test.com");
			
		NonprofitWrapper wrapper = new NonprofitWrapper(nonprofit);
		
		serviceNonProfit.saveNonprofit(wrapper);
		
		UserGeneral userGeneral = new UserGeneral();
		userGeneral.setEmail(random+"@gmail.com");
		userGeneral.setPassword(getPassword());
		
		List<UserGeneral> tempList = new ArrayList<UserGeneral>();
		
		tempList.add(userGeneral);
		nonprofit.setUsergenerals(tempList);
		userGeneral.setNonprofit(nonprofit);
		UserGeneralWrapper wrapperUser = new UserGeneralWrapper(userGeneral);
		
		serviceUserGeneral.saveUserGeneral(wrapperUser);
		
		return wrapper;
		
	}
	
	
	public  NonprofitWrapper createRandomNonprofit(String addToName){
		CatalogWrapper country= createRandomCatalog();
		String random = getRandomString();
		Nonprofit nonprofit = new Nonprofit();

		nonprofit.setActive(true);
		nonprofit.setDescription("description");
		nonprofit.setMision("Mision");
		nonprofit.setName("NGO TEST"+addToName);
		nonprofit.setReason("Reason");
		nonprofit.setWebPage("www.test.com");
		nonprofit.setConutry(country.getWrapperObject());
			
		NonprofitWrapper wrapper = new NonprofitWrapper(nonprofit);
		
		serviceNonProfit.saveNonprofit(wrapper);
		
		UserGeneral userGeneral = new UserGeneral();
		userGeneral.setEmail(random+"@gmail.com");
		userGeneral.setPassword(getPassword());
		
		List<UserGeneral> tempList = new ArrayList<UserGeneral>();
		
		tempList.add(userGeneral);
		nonprofit.setUsergenerals(tempList);
		userGeneral.setNonprofit(nonprofit);
		UserGeneralWrapper wrapperUser = new UserGeneralWrapper(userGeneral);
		
		serviceUserGeneral.saveUserGeneral(wrapperUser);
		
		return wrapper;
		
	}
	
	
	public  DonorWrapper createRandomDonor(){
		
		Catalog country = createRandomCatalog().getWrapperObject();
		
		String random = getRandomString();
		Donor donor = new Donor();
		
		donor.setActive(true);
		donor.setDescription("description");
		donor.setLastName("Test");
		donor.setName("Test");
		donor.setWebPage("www.test.com");
		donor.setCountry(country);
		
		DonorWrapper wrapper = new DonorWrapper(donor);
		donorService.saveDonor(wrapper);
		
		UserGeneral userGeneral = new UserGeneral();
		userGeneral.setEmail(random+"@gmail.com");
		userGeneral.setPassword(getPassword());
		
		List<UserGeneral> tempList = new ArrayList<UserGeneral>();
		
		tempList.add(userGeneral);
		donor.setUsergenerals(tempList);
		userGeneral.setDonor(donor);
		UserGeneralWrapper wrapperUser = new UserGeneralWrapper(userGeneral);
		
		serviceUserGeneral.saveUserGeneral(wrapperUser);
		
		return wrapper;
		
	}
	
	
	private String getPassword(){
		 byte[] hash = Utils.encryption("123456789");
			String file_string="";
			
			for(int i = 0; i < hash.length; i++)
		    {
		        file_string += (char)hash[i];
		    }		
			
			return file_string;
	}
	
	public  NonprofitWrapper createRandomNonprofit(String nGOName, Catalog country){
		
		String random = getRandomString();
		Nonprofit nonprofit = new Nonprofit();
		
		nonprofit.setActive(true);
		nonprofit.setDescription("description");
		nonprofit.setMision("Mision");
		nonprofit.setName(nGOName);
		nonprofit.setReason("Reason");
		nonprofit.setWebPage("www.test.com");
		nonprofit.setConutry(country);
		
		
		NonprofitWrapper wrapper = new NonprofitWrapper(nonprofit);
		
		serviceNonProfit.saveNonprofit(wrapper);
		
		return wrapper;
		
	}
	
	public  NonprofitPOJO convertNonprofitWrapper(NonprofitWrapper wrapper){
		NonprofitPOJO newPOJO = new NonprofitPOJO();
		PojoUtils.pojoMappingUtility(newPOJO, wrapper);
		return newPOJO;
	}
	
	
	

	
	public  UserGeneralWrapper createRandomUserGeneral(){
	
			String random = getRandomString();
			UserGeneral user = new UserGeneral();
	        UserGeneralWrapper userWrapper = new UserGeneralWrapper(user);
	        
	        byte[] hash = Utils.encryption("123456789");
			String file_string="";
			
			for(int i = 0; i < hash.length; i++)
		    {
		        file_string += (char)hash[i];
		    }		
	        
	        userWrapper.setIsActive(true);
	        userWrapper.setPassword(file_string);
	        userWrapper.setEmail(random+"@prueba1.com");
			
			serviceUserGeneral.saveUserGeneral(userWrapper);
			
			return userWrapper;

	}
	
	
	public  PostNonprofitWrapper createRandomPost(Nonprofit nonprofit){
		
		String random = getRandomString();
		PostNonprofit post = new PostNonprofit();
		post.setTittle("Title "+random);
		post.setDescription("Description");
		post.setCreationDate(new Date());
		post.setIsActive(true);
		post.setPicture(TreeseedConstants.DEFAULT_POST_IMAGE);
		post.setNonprofit(nonprofit);
		
		PostNonprofitWrapper wrapper =  new PostNonprofitWrapper(post);
		
		postNonprofitService.savePostNonprofit(wrapper);
		
		return wrapper;

}
    
    public  String getRandomString(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
    
    public CampaignWrapper createRandomCampaign() throws IOException, Exception{
    	NonprofitWrapper nonprofit = createRandomNonprofit();
		   
		   
	   	String name = "pruebaCrearCampaña";
	   	String description =getRandomString();
		double amount =  800;
		CampaignWrapper campaign = new CampaignWrapper();
		
		campaign.setName(name);
		campaign.setDescription(description);
		campaign.setStartDate(new Date());
		campaign.setDueDate(new Date());
		campaign.setAmountGoal(amount);
		campaign.setNonprofit(nonprofit.getWrapperObject());
		campaign.setActive(true);
		
		campaignService.saveCampaign(campaign);
		
        return campaign;

    }
    
    public int createServerSession(){
    	HttpSession currentSession = requestHttp.getSession();
		currentSession.setAttribute("idUser",99999);
		return 99999;
    }
	
}
