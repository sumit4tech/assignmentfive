package com.sumit.assignmentfive.response;

import com.sumit.assignmentfive.utils.Constants;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ResponseHandler {

	public static Map<String , Object> generateResponse
	(String message,HttpStatus status, boolean error,Object response){
		Map<String, Object> map = new HashMap<>();

		try {
			map.put(Constants.MESSAGE, message);
			map.put(Constants.STATUS, status);
			map.put(Constants.ERROR, error);
			map.put(Constants.DATA,response);
			map.put(Constants.TIME_STAMP, new Date());
			return map;
		} catch (Exception e) {
			map.clear();
			map.put(Constants.MESSAGE, e.getMessage());
			map.put(Constants.STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
			map.put(Constants.TIME_STAMP, new Date());			
			return map;
		}
	}
	
	public static Map<String , Object> generateErrorResponse
	(String message,HttpStatus status, boolean error,Object response){
		Map<String, Object> map = new HashMap<>();
		
		try {
			map.put(Constants.MESSAGE, message);
			map.put(Constants.STATUS, status);
			map.put(Constants.ERROR, error);
			map.put(Constants.DATA,response);
			map.put(Constants.TIME_STAMP, new Date());
			return map;
		} catch (Exception e) {
			map.clear();
			map.put(Constants.MESSAGE, e.getMessage());
			map.put(Constants.STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
			map.put(Constants.TIME_STAMP, new Date());			
			return map;
		}
	}
	
}
