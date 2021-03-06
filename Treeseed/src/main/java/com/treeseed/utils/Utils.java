package com.treeseed.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Plan;



public class Utils {
	
	private static String RESOURCES_PATH = "resources/file-storage/";
	private static String HOST_PATH = "http://localhost:8080";
	
	// save uploaded file to new location
	public static String writeToFile(MultipartFile file, ServletContext servletContext) {
	
		String extension = getExtension("png",".").toLowerCase();
		
		if (extension == ".undefined")
		{
			extension = ".png";
		}
	
		String consecutiveName = ""+new Date().getTime();
		
		String uploadedFileLocation = servletContext.getRealPath("")+ "/" + RESOURCES_PATH + consecutiveName + extension;
		String databaseFileName = servletContext.getContextPath() + "/" + RESOURCES_PATH + consecutiveName + extension;
		
		byte[] bytes;
		try {
			bytes = file.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(uploadedFileLocation)));
			stream.write(bytes);
			stream.close();
			
			System.out.println("uploadedFileLocation "+uploadedFileLocation);
			System.out.println("databaseFileName "+databaseFileName);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
      
		return databaseFileName;
	}
	
	private static String getExtension(String filename, String extensionSeparator) {
	    int dot = filename.lastIndexOf(extensionSeparator);
	    return "."+filename.substring(dot + 1);
	}
	
	public static byte[] encryption(String text){
	
		try{
			MessageDigest coded = MessageDigest.getInstance("MD5");
			return coded.digest(text.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException ex) {
	        throw new RuntimeException("No MD5 implementation");
	    } catch (UnsupportedEncodingException ex) {
	        throw new RuntimeException("No UTF-8 encoding");
	    }	
	}

	
}
