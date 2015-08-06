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
import java.util.List;
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
import com.stripe.model.*;
import com.treeseed.ejbWrapper.CampaignWrapper;
import com.treeseed.ejbWrapper.NonprofitWrapper;

import javassist.expr.NewArray;


// TODO: Auto-generated Javadoc
/**
 * The Class StripeUtils.
 */
public class StripeUtils {

	/** The api key. */
	private static String API_KEY = "sk_test_0L9gz0bNILLeY5efuPFuz2Qa";

	/**
	 * Creates the donation.
	 *
	 * @param idDonorStripe the id donor stripe
	 * @param amount the amount
	 * @param sourceToken the source token
	 * @param cardStripeId the card stripe id
	 * @param idDonation the id donation
	 * @param nonProfit the non profit
	 * @param idCampaign the id campaign
	 * @param nameCampaign the name campaign
	 * @return the array list
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static ArrayList<Object> createDonation(String idDonorStripe, double amount, String sourceToken, String cardStripeId ,int idDonation,  NonprofitWrapper nonProfit, int idCampaign, String nameCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;		
		
		ArrayList<Object> result = new ArrayList<Object>();
		Customer customer = getCustomer(idDonorStripe);
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
	
	/**
	 * Creates the donation new customer.
	 *
	 * @param idDonor the id donor
	 * @param completeName the complete name
	 * @param email the email
	 * @param amount the amount
	 * @param sourceToken the source token
	 * @param idDonation the id donation
	 * @param nonProfit the non profit
	 * @param idCampaign the id campaign
	 * @param nameCampaign the name campaign
	 * @return the array list
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static ArrayList<Object> createDonationNewCustomer(int idDonor, String completeName,String email, double amount, String sourceToken, int idDonation ,NonprofitWrapper nonProfit, int idCampaign, String nameCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		Customer customer = createCustomers(idDonor,completeName, email);
		Card card = createCard(customer, sourceToken);
		
		result.add(0, customer.getId());
		result.add(1, card.getId());
		result.add(2, (createCharge(amount, card.getId(), customer.getId(),idDonation, nonProfit.getId(), idCampaign, nonProfit.getName(), nameCampaign).getId()));
		
		
		return result;

	}
	
	/**
	 * Creates the recurrable donation.
	 *
	 * @param idDonorStripe the id donor stripe
	 * @param planNumber the plan number
	 * @param sourceToken the source token
	 * @param cardStripeId the card stripe id
	 * @param nonProfit the non profit
	 * @param idCampaign the id campaign
	 * @param sameCard the same card
	 * @return the array list
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static ArrayList<Object> createRecurrableDonation(String idDonorStripe,int planNumber ,String sourceToken, String cardStripeId,  NonprofitWrapper nonProfit, int idCampaign, boolean sameCard)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;		
		
		ArrayList<Object> result = new ArrayList<Object>();
		Customer customer = getCustomer(idDonorStripe);
		Plan plan = getPlan(Integer.toString(nonProfit.getId()), Integer.toString(idCampaign), Integer.toString(planNumber));
		
		Card card;

		if(sourceToken.equals("")){
			card = getCard(idDonorStripe, cardStripeId);
			result.add(0,"");
			
		}else{
			card = createCard(customer, sourceToken);
			result.add(0,card.getId());
		}
		
		if(!sameCard){
			updateCustomer(idDonorStripe, card.getId());
		}
		
		result.add(1,(createSubscription(plan, customer, card)).getId());
		
		return result;

	}
	
	/**
	 * Creates the recurrable donation new customer.
	 *
	 * @param idDonor the id donor
	 * @param completeName the complete name
	 * @param email the email
	 * @param planNumber the plan number
	 * @param sourceToken the source token
	 * @param nonProfit the non profit
	 * @param idCampaign the id campaign
	 * @return the array list
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static ArrayList<Object> createRecurrableDonationNewCustomer(int idDonor, String completeName,String email,int planNumber ,String sourceToken,  NonprofitWrapper nonProfit, int idCampaign)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;
		
		ArrayList<Object> result = new ArrayList<Object>();
		
		Customer customer = createCustomers(idDonor,completeName, email);
		Card card = createCard(customer, sourceToken);
		Plan plan = getPlan(Integer.toString(nonProfit.getId()), Integer.toString(idCampaign), Integer.toString(planNumber));
		
		result.add(0, customer.getId());
		result.add(1, card.getId());
		result.add(2, (createSubscription(plan, customer, card)).getId());
		
		
		return result;

	}

	/**
	 * Creates the plan.
	 *
	 * @param count the count
	 * @param idNonprofit the id nonprofit
	 * @param idCampaign the id campaign
	 * @param nameNonprofit the name nonprofit
	 * @param nameCampaign the name campaign
	 * @param amount the amount
	 * @return the plan
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Plan createPlan(int count, int idNonprofit, int idCampaign, String nameNonprofit, String nameCampaign,
			int amount) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
					APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> planParams = new HashMap<String, Object>();
		planParams.put("amount", amount);
		planParams.put("interval", "month");
		planParams.put("currency", "usd");
		
		
		/*
		 * Simbolo del id
		 *
		 * # Sepra los id en [Id de la ONG], [Id de la Campaña](De haber), [Numero de Plan](1-$10, 2-$18, 3-$36, 4-$50, 5-$100, 6-$250)
		 */
		
		/*
		 * Simbolo del nombre
		 * & Separa los nombres de los id
		 * _ Separa los nombres en [Palabra 'Plan'], [Nombre de ONG], [Nombre de Campaña](De haber), [Cantidad a cobrar del plan en centavos]
		 * # Sepra los id en [Id de la ONG], [Id de la Campaña](De haber)
		 */
		if (idCampaign > 0) {
			planParams.put("id", idNonprofit + "#" + idCampaign + "#" + count);
			planParams.put("name",
					idNonprofit + "#" + idCampaign + "&Plan_" + nameNonprofit + "_" + nameCampaign + "_" + amount);
		} else {
			planParams.put("id", idNonprofit + "#" + count);
			planParams.put("name", idNonprofit + "&Plan_" + nameNonprofit + "_" + amount);
		}

		return Plan.create(planParams);

	}
	
	
	
	/**
	 * Creates the subscription.
	 *
	 * @param plan the plan
	 * @param customer the customer
	 * @param card the card
	 * @return the subscription
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Subscription createSubscription(Plan plan, Customer customer, Card card)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> subscriptionParams = new HashMap<String, Object>();

		subscriptionParams.put("plan", plan.getId());
		subscriptionParams.put("source", card.getId());

		return customer.createSubscription(subscriptionParams);

	}

	/**
	 * Creates the customers.
	 *
	 * @param idDonor the id donor
	 * @param completeName the complete name
	 * @param email the email
	 * @return the customer
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Customer createCustomers(int idDonor, String completeName, String email)
			throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException,
			APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> customerParams = new HashMap<String, Object>();

		customerParams.put("description", completeName);
		customerParams.put("email", email);
		customerParams.put("description", idDonor);

		return Customer.create(customerParams);

	}

	/**
	 * Creates the card.
	 *
	 * @param customer the customer
	 * @param token the token
	 * @return the card
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Card createCard(Customer customer, String token) throws AuthenticationException, InvalidRequestException,
					APIConnectionException, CardException, APIException {

		Stripe.apiKey = API_KEY;

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("source", token);

		return customer.createCard(params);

	}

	/**
	 * Creates the charge.
	 *
	 * @param amount the amount
	 * @param cardId the card id
	 * @param customerId the customer id
	 * @param idDonation the id donation
	 * @param idNonprofit the id nonprofit
	 * @param idCampaign the id campaign
	 * @param nameNonprofit the name nonprofit
	 * @param nameCampaign the name campaign
	 * @return the charge
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
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

		
		/*
		 * Simbolo de la descripción
		 * & Separa los nombres de los id
		 * _ Separa los nombres en [Palabra 'Donacion'], [Nombre de ONG], [Nombre de Campaña](De haber)
		 * # Sepra los id en [Id de la Donacion], [Id de la ONG], [Id de la Campaña](De haber)
		 */
		if (idCampaign > 0) {

			chargeParams.put("description",
					idDonation + "#" + idNonprofit + "#"+ idCampaign + "&Donation_" + nameNonprofit+"_"+ nameCampaign);
		} else {
			chargeParams.put("description",
					idDonation + "#" + idNonprofit + "&Donation_" + nameNonprofit);
		}

		return Charge.create(chargeParams);

	}
	
	/**
	 * Gets the charge from invoice.
	 *
	 * @param idInvoiceStripe the id invoice stripe
	 * @return the charge from invoice
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Charge getChargeFromInvoice(String idInvoiceStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		
		Invoice invoice = Invoice.retrieve(idInvoiceStripe);
		Charge charge = Charge.retrieve(invoice.getCharge());
		
		return charge;
	}
	
	/**
	 * Update customer.
	 *
	 * @param idDonorStripe the id donor stripe
	 * @param idCardStripe the id card stripe
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static void updateCustomer(String idDonorStripe, String idCardStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		Customer customer = Customer.retrieve(idDonorStripe);
		
		Map<String, Object> customerParams = new HashMap<String, Object>();

		customerParams.put("default_source", idCardStripe);
		
		customer.update(customerParams);
	}
	
	/**
	 * Gets the invoice.
	 *
	 * @param idInvoiceStripe the id invoice stripe
	 * @return the invoice
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Invoice getInvoice(String idInvoiceStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		Invoice invoice = Invoice.retrieve(idInvoiceStripe);
		
		return invoice;
	}
	
	/**
	 * Gets the charge.
	 *
	 * @param idChargeStripe the id charge stripe
	 * @return the charge
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Charge getCharge(String idChargeStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		Charge charge = Charge.retrieve(idChargeStripe);
		
		return charge;
	}
	
	/**
	 * Gets the customer.
	 *
	 * @param idDonorStripe the id donor stripe
	 * @return the customer
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Customer getCustomer(String idDonorStripe) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		Customer customer = Customer.retrieve(idDonorStripe);
		
		return customer;
	}
	
	/**
	 * Gets the card.
	 *
	 * @param idDonorStripe the id donor stripe
	 * @param stripeCardId the stripe card id
	 * @return the card
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Card getCard(String idDonorStripe, String stripeCardId) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		
		Customer customer = getCustomer(idDonorStripe);
		
		Card card = (Card)customer.getSources().retrieve(stripeCardId);
			
		
		return card;
	}
	
	/**
	 * Gets the plan.
	 *
	 * @param idNonprofit the id nonprofit
	 * @param idCampaign the id campaign
	 * @param planNumber the plan number
	 * @return the plan
	 * @throws AuthenticationException the authentication exception
	 * @throws InvalidRequestException the invalid request exception
	 * @throws APIConnectionException the API connection exception
	 * @throws CardException the card exception
	 * @throws APIException the API exception
	 */
	public static Plan getPlan(String idNonprofit, String idCampaign, String planNumber) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException{
		Stripe.apiKey = API_KEY;
		Plan plan;
		Stripe.apiKey = API_KEY;
		if(idCampaign.equals("0")){
			plan = Plan.retrieve(idNonprofit+"#"+planNumber);
		}else{
			plan = Plan.retrieve(idNonprofit+"#"+idCampaign+"#"+planNumber);
		}
		
		return plan;
	}
}
