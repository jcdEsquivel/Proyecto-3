package com.treeseed.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treeseed.ejb.Campaign;
import com.treeseed.ejb.Catalog;
import com.treeseed.ejb.Donor;
import com.treeseed.ejb.Nonprofit;
import com.treeseed.ejb.PostCampaign;
import com.treeseed.ejb.PostNonprofit;
import com.treeseed.ejb.UserGeneral;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.CatalogWrapper;
import com.treeseed.ejbWrapper.DonorWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;
import com.treeseed.ejbWrapper.PostCampaignWrapper;
import com.treeseed.ejbWrapper.PostNonprofitWrapper;
import com.treeseed.ejbWrapper.UserGeneralWrapper;
import com.treeseed.pojo.CatalogPOJO;
import com.treeseed.pojo.NonprofitPOJO;
import com.treeseed.services.CampaignServiceInterface;
import com.treeseed.services.CatalogServiceInterface;
import com.treeseed.services.DonorServiceInterface;
import com.treeseed.services.NonprofitServiceInterface;
import com.treeseed.services.PostCampaignServiceInteface;
import com.treeseed.services.PostNonprofitServiceInterface;
import com.treeseed.services.UserGeneralServiceInterface;
import com.treeseed.utils.PojoUtils;
import com.treeseed.utils.TreeseedConstants;
import com.treeseed.utils.Utils;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractTestController.
 */
@WebAppConfiguration("src/test/webapp/")
public abstract class AbstractTestController extends AbstractTest {

	/** The mvc. */
	protected MockMvc mvc;

    /** The web application context. */
    @Autowired
    protected WebApplicationContext webApplicationContext;

    /** The service catalog. */
    @Autowired  CatalogServiceInterface serviceCatalog;
    
    /** The service non profit. */
    @Autowired  NonprofitServiceInterface serviceNonProfit;
    
    /** The service user general. */
    @Autowired  UserGeneralServiceInterface serviceUserGeneral;
    
    /** The post nonprofit service. */
    @Autowired	PostNonprofitServiceInterface postNonprofitService;
    @Autowired	PostCampaignServiceInteface postCampaignService;
    /** The donor service. */
    @Autowired	DonorServiceInterface donorService;
    
    /** The campaign service. */
    @Autowired	CampaignServiceInterface campaignService;
    
    /** The request http. */
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
     *
     * @param obj the obj
     * @return the string
     * @throws JsonProcessingException the json processing exception
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
     *
     * @param <T> the generic type
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
    
    
    
    
/**
 * Gets the catalog pojo.
 *
 * @param id the id
 * @return the catalog pojo
 */
public  CatalogPOJO getCatalogPOJO(int id){
		
		CatalogWrapper wrapper = serviceCatalog.findCatalogById(id);
		CatalogPOJO pojo = new CatalogPOJO();
		
		PojoUtils.pojoMappingUtility(pojo, wrapper);
		
		return pojo;
		
	}
	
	/**
	 * Gets the catalog wrapper.
	 *
	 * @param id the id
	 * @return the catalog wrapper
	 */
	public  CatalogWrapper getCatalogWrapper(int id){
		return serviceCatalog.findCatalogById(id);
	}
	
	
/**
 * Gets the catalog poj os.
 *
 * @param type the type
 * @return the catalog poj os
 */
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
	
	/**
	 * Gets the catalog wrappers.
	 *
	 * @param type the type
	 * @return the catalog wrappers
	 */
	public  List<CatalogWrapper> getCatalogWrappers(String type){
		return serviceCatalog.getAllByType(type);
	}
	
	
	/**
	 * Creates the random catalog.
	 *
	 * @return the catalog wrapper
	 */
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
	
	/**
	 * Convert catalog wrapper.
	 *
	 * @param wrapper the wrapper
	 * @return the catalog pojo
	 */
	public  CatalogPOJO convertCatalogWrapper(CatalogWrapper wrapper){
		CatalogPOJO newPOJO = new CatalogPOJO();
		PojoUtils.pojoMappingUtility(newPOJO, wrapper);
		return newPOJO;
	}
    
    
    
    
	/**
	 * Gets the nonprofit pojo.
	 *
	 * @param id the id
	 * @return the nonprofit pojo
	 */
	public static NonprofitPOJO getNonprofitPOJO(int id){
		//Not Implemented
		return null;
		/*NonprofitWrapper wrapper = service.(id);
		NonprofitPOJO pojo = new NonprofitPOJO();
		
		PojoUtils.pojoMappingUtility(pojo, wrapper);
		
		return pojo;*/
		
	}
	
	/**
	 * Gets the nonprofit wrapper.
	 *
	 * @param id the id
	 * @return the nonprofit wrapper
	 */
	public  NonprofitWrapper getNonprofitWrapper(int id){
		//return service.findNonprofitById(id);
		
		//Not Implemented
		return null;
	}
	
	
	/**
	 * Creates the random nonprofit.
	 *
	 * @return the nonprofit wrapper
	 */
	public  NonprofitWrapper createRandomNonprofit(){
			
		String random = getRandomString();
		Nonprofit nonprofit = new Nonprofit();
		CatalogWrapper cause= createRandomCatalog();
		CatalogWrapper country= createRandomCatalog();

		nonprofit.setActive(true);
		nonprofit.setDescription("description");
		nonprofit.setMision("Mision");
		nonprofit.setName("NGO TEST");
		nonprofit.setReason("Reason");
		nonprofit.setWebPage("www.test.com");
		nonprofit.setCause(cause.getWrapperObject());
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
	
	
	/**
	 * Creates the random nonprofit.
	 *
	 * @param addToName the add to name
	 * @return the nonprofit wrapper
	 */
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
	
	
	/**
	 * Creates the random donor.
	 *
	 * @return the donor wrapper
	 */
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
	
	
	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	private String getPassword(){
		 byte[] hash = Utils.encryption("123456789");
			String file_string="";
			
			for(int i = 0; i < hash.length; i++)
		    {
		        file_string += (char)hash[i];
		    }		
			
			return file_string;
	}
	
	/**
	 * Creates the random nonprofit.
	 *
	 * @param nGOName the n go name
	 * @param country the country
	 * @return the nonprofit wrapper
	 */
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
	
	/**
	 * Convert nonprofit wrapper.
	 *
	 * @param wrapper the wrapper
	 * @return the nonprofit pojo
	 */
	public  NonprofitPOJO convertNonprofitWrapper(NonprofitWrapper wrapper){
		NonprofitPOJO newPOJO = new NonprofitPOJO();
		PojoUtils.pojoMappingUtility(newPOJO, wrapper);
		return newPOJO;
	}
	
	
	

	
	/**
	 * Creates the random user general.
	 *
	 * @return the user general wrapper
	 */
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
	
	
	/**
	 * Creates the random post.
	 *
	 * @param nonprofit the nonprofit
	 * @return the post nonprofit wrapper
	 */
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
	
	public  PostCampaignWrapper createRandomCampaignPost(Campaign campaign){
		
		String random = getRandomString();
		PostCampaign post = new PostCampaign();
		post.setTittle("Title "+random);
		post.setDescription("Description");
		post.setCreationDate(new Date());
		post.setActive(true);
		post.setPicture(TreeseedConstants.DEFAULT_POST_IMAGE);
		post.setCampaign(campaign);
		
		PostCampaignWrapper wrapper =  new PostCampaignWrapper(post);
		
		//postCampaignService.savePostCampaign(wrapper);
		
		return wrapper;

	}
		
	/**
	 * Creates the random campaign.
	 *
	 * @param nonprofit the nonprofit
	 * @return the campaign wrapper
	 */
	public CampaignWrapper createRandomCampaign(NonprofitWrapper nonprofit){
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date()); // Now use today date.
		c.add(Calendar.DATE, 20); // Adding 5 days
		Date endDate = c.getTime();
		
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setAmountGoal(100);
		campaign.setCreationDate(new Date());
		campaign.setDescription("Test campaign");
		campaign.setDueDate(endDate);
		campaign.setName("Test campaign"+getRandomString());
		campaign.setNonprofit(nonprofit.getWrapperObject());
		campaign.setStartDate(new Date());
		
		CampaignWrapper wrapper = new CampaignWrapper(campaign);
		
		campaignService.saveCampaign(wrapper );
		
		return wrapper;
	}
	
/**
 * Creates the random campaign.
 *
 * @param nonprofit the nonprofit
 * @param startDate the start date
 * @param endDate the end date
 * @return the campaign wrapper
 */
public CampaignWrapper createRandomCampaign(NonprofitWrapper nonprofit, Date startDate, Date endDate){
		
		Campaign campaign = new Campaign();
		campaign.setActive(true);
		campaign.setAmountGoal(100);
		campaign.setCreationDate(new Date());
		campaign.setDescription("Test campaign");
		campaign.setDueDate(endDate);
		campaign.setName("Test campaign"+getRandomString());
		campaign.setNonprofit(nonprofit.getWrapperObject());
		campaign.setStartDate(startDate);
		
		CampaignWrapper wrapper = new CampaignWrapper(campaign);
		
		campaignService.saveCampaign(wrapper );
		
		return wrapper;
	}
    
    /**
     * Gets the random string.
     *
     * @return the random string
     */
    public  String getRandomString(){
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
    
    /**
     * Creates the random campaign.
     *
     * @return the campaign wrapper
     * @throws IOException Signals that an I/O exception has occurred.
     * @throws Exception the exception
     */
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
    
}
