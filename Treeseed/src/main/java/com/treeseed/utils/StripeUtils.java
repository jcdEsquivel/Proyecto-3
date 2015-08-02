package com.treeseed.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Plan;
import com.stripe.model.StripeObject;
import com.stripe.model.Subscription;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;

import javassist.expr.NewArray;


public class StripeUtils {

	private static String API_KEY = "sk_test_0L9gz0bNILLeY5efuPFuz2Qa";

	public static ArrayList<Object> createDonation(String idDonorStripe, double amount, String sourceToken, String cardStripeId ,int idDonation,  NonprofitWrapper nonProfit, int idCampaign, String nameCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;		
		
		ArrayList<Object> result = new ArrayList<Object>();
		Customer customer = getCustumer(idDonorStripe);
		Card card;

		if(sourceToken.equals("")){
			card = getCard(idDonorStripe, cardStripeId);
			result.add(0,"");
		}else{
			card = createCard(customer, sourceToken);
			result.add(0,card.getId());
		}
		
		result.add(1,(createCharge(amount, card.getId(),customer.getId(), idDonation, nonProfit.getId(), idCampaign, nonProfit.getName(), nameCampaign)).getId());
		
		return result;

	}
	
	public static ArrayList<Object> createDonationNewCustomer(String completeName,String email, double amount, String sourceToken, int idDonation ,NonprofitWrapper nonProfit, int idCampaign, String nameCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		Customer customer = createCustomers(completeName, email);
		Card card = createCard(customer, sourceToken);
		
		result.add(0, customer.getId());
		result.add(1, card.getId());
		result.add(2, (createCharge(amount, card.getId(), customer.getId(),idDonation, nonProfit.getId(), idCampaign, nonProfit.getName(), nameCampaign).getId()));
		
		
		return result;

	}
	
	public static ArrayList<Object> createRecurrableDonation(String idDonorStripe,int planNumber ,String sourceToken, String cardStripeId,  NonprofitWrapper nonProfit, int idCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;		
		
		ArrayList<Object> result = new ArrayList<Object>();
		Customer customer = getCustumer(idDonorStripe);
		Plan plan = getPlan(Integer.toString(nonProfit.getId()), Integer.toString(idCampaign), Integer.toString(planNumber));
		
		Card card;

		if(sourceToken.equals("")){
			card = getCard(idDonorStripe, cardStripeId);
			result.add(0,"");
		}else{
			card = createCard(customer, sourceToken);
			result.add(0,card.getId());
		}
		
		result.add(1,(createSubscription(plan, customer, card)));
		
		return result;

	}
	
	public static ArrayList<Object> createRecurrableDonationNewCustomer(String completeName,String email,String idDonorStripe,int planNumber ,String sourceToken, String cardStripeId,  NonprofitWrapper nonProfit, int idCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		Customer customer = createCustomers(completeName, email);
		Card card = createCard(customer, sourceToken);
		Plan plan = getPlan(Integer.toString(nonProfit.getId()), Integer.toString(idCampaign), Integer.toString(planNumber));
		
		result.add(0, customer.getId());
		result.add(1, card.getId());
		result.add(2, (createSubscription(plan, customer, card)));
		
		
		return result;

	}

	public static Plan createPlan(int count, int idNonprofit, int idCampaign, String nameNonprofit, String nameCampaign,
			int amount) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
					APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> planParams = new HashMap<String, Object>();
		planParams.put("amount", amount);
		planParams.put("interval", "month");
		planParams.put("currency", "usd");
		if (idCampaign > 0) {
			planParams.put("id", idNonprofit + "#" + idCampaign + "#" + count);
			planParams.put("name",
					idNonprofit + "#" + idCampaign + "#_Plan_#" + nameNonprofit + "#" + nameCampaign + "#" + amount);
		} else {
			planParams.put("id", idNonprofit + "#" + count);
			planParams.put("name", idNonprofit + "#_Plan_#" + nameNonprofit + "#" + amount);
		}

		return Plan.create(planParams);

	}
	
	
	
	public static Subscription createSubscription(Plan plan, Customer customer, Card card)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> subscriptionParams = new HashMap<String, Object>();

		subscriptionParams.put("plan", plan.getId());
		subscriptionParams.put("source", card.getId());

		return customer.createSubscription(subscriptionParams);

	}

	public static Customer createCustomers(String completeName, String email)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> customerParams = new HashMap<String, Object>();

		customerParams.put("description", completeName);
		customerParams.put("email", email);

		return Customer.create(customerParams);

	}

	public static Card createCard(Customer customer, String token) throws AuthenticationException, InvalidRequestException,
					APIConnectionException, CardException, APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("source", token);

		return customer.createCard(params);

	}

	public static Charge createCharge(double amount, String cardId,String customerId ,int idDonation, int idNonprofit,
			int idCampaign, String nameNonprofit, String nameCampaign) throws AuthenticationException,
					InvalidRequestException, APIConnectionException, CardException, APIException {

		DecimalFormat format = new DecimalFormat("0.00");
		String amountDoubleString = format.format(amount);
		amountDoubleString = amountDoubleString.replace(".", "");
		int amountInt = Integer.parseInt(amountDoubleString);

		Stripe.apiKey = API_KEY;

		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", amountInt);
		chargeParams.put("currency", "usd");
		chargeParams.put("customer", customerId);	
		chargeParams.put("source", cardId); 

		if (idCampaign > 0) {

			chargeParams.put("description",
					"Donation #" + idDonation + "# Nonprofit #" + idNonprofit + "# Nonprofit Name #" + nameNonprofit
							+ "# Campaign #" + idCampaign + "# Campaign Name #" + nameCampaign);
		} else {
			chargeParams.put("description",
					"Donation #" + idDonation + "# Nonprofit #" + idNonprofit + "# Nonprofit Name #" + nameNonprofit);
		}

		return Charge.create(chargeParams);

	}
	
	public static Customer getCustumer(String idDonorStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		
		Customer customer = Customer.retrieve(idDonorStripe);
		
		return customer;
	}
	
	public static Card getCard(String idDonorStripe, String stripeCardId) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		
		Customer customer = getCustumer(idDonorStripe);
		
		Card card = (Card)customer.getSources().retrieve(stripeCardId);
			
		
		return card;
	}
	
	public static Plan getPlan(String idNonprofit, String idCampaign, String planNumber) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Plan plan;
		
		if(idCampaign.equals("")){
			plan = Plan.retrieve(idNonprofit+"#"+planNumber);
		}else{
			plan = Plan.retrieve(idNonprofit+"#"+idCampaign+"#"+planNumber);
		}
		
		return plan;
	}
}
