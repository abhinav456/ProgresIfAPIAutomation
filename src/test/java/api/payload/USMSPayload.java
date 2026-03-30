package api.payload;

import java.util.HashMap;
import java.util.Map;

public class USMSPayload {

	public static Map<String, Object> GetMeterDetails(
	        String MeterNumber
	) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("Meter_Number", MeterNumber);

	    return payload;
	}
	
	public static Map<String, Object> GetMeterHistory(
			int limit,
			int offset){
		   
		  Map<String, Object> payload = new HashMap<>();
		  payload.put("limit", limit);
		  payload.put("offset", offset);
				return payload;
	}
	
	public static Map<String, Object> GetSessionUSMSPayment(
			String amount,
			String cardName,
			String cardNum,
			String cardExpMth,
			String cardExpYr,
			String cardCvn){
		
		   
		  Map<String, Object> payload = new HashMap<>();
		  payload.put("amount", amount);
		  payload.put("cardName", cardName);
		  payload.put("cardNum", cardNum);
		  payload.put("cardExpMth", cardExpMth);
		  payload.put("cardExpYr", cardExpYr);
		  payload.put("cardCvn", cardCvn);
				return payload;
	}
	
	public static Map<String, Object> getUSMSCardPayment(
	        String sessionId,
	        String orderId,
	        String meterNumber,
	        String meterType,
	        String amount) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("sessionId", sessionId);
	    payload.put("orderId", orderId);
	    payload.put("meterNumber", meterNumber);
	    payload.put("metertype", meterType);
	    payload.put("amount", amount);

	    return payload;
	}
	
	public static Map<String, Object> getUSMSPaymentExitCard(
	        String amount,
	        String meterNumber,
	        String meterType,
	        String description) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("amount", amount);
	    payload.put("meterNumber", meterNumber);
	    payload.put("metertype", meterType);
	    payload.put("description", description);

	    return payload;
	}
	
	public static Map<String, Object> getUSMSPaymentWallet(
	        String amount,
	        String meterNumber,
	        String meterType) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("amount", amount);
	    payload.put("meterNumber", meterNumber);
	    payload.put("metertype", meterType);

	    return payload;
	}
	
	public static Map<String, Object> USMSMeterCreatePayload(
	        String meterNumber,
	        String meterLabel,
	        String meterType) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("meter_number", meterNumber);
	    payload.put("meter_label", meterLabel);
	    payload.put("meter_type", meterType);

	    return payload;
	}
	public static Map<String, Object> USMSMeterUpdatePayload(
	        String meterNumber,
	        String meterLabel,
	        String meterType) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("meter_number", meterNumber);
	    payload.put("meter_label", meterLabel);
	    payload.put("meter_type", meterType);

	    return payload;
	}
}
