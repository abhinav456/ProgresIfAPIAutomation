package api.payload;

import java.util.HashMap;
import java.util.Map;
public class MerchantDiscovery {
	
	public static Map<String, Object> SearchMerchant(
	        String query, 
	        int limit) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("query", query);
	    payload.put("limit", limit);

	    return payload;
	}

}
