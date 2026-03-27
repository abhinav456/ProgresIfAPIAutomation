package api.payload;

import java.util.HashMap;
import java.util.Map;

public class Banner {
	
	public static Map<String, Object> createBannerPayload(
	        String title,
	        String imageUrl,
	        String redirectUrl,
	        int displayOrder,
	        String startDate
	) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("title", title);
	    payload.put("image_url", imageUrl);
	    payload.put("redirect_url", redirectUrl);
	    payload.put("display_order", displayOrder);
	    payload.put("start_date", startDate);

	    return payload;
	}
	
	public static Map<String, Object> updateBannerPayload(
	        String title,
	        String imageUrl,
	        String redirectUrl,
	        int displayOrder,
	        String startDate,
	        String endDate
	) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("title", title);
	    payload.put("image_url", imageUrl);
	    payload.put("redirect_url", redirectUrl);
	    payload.put("display_order", displayOrder);
	    payload.put("start_date", startDate);
	    payload.put("end_date", endDate);

	    return payload;
	}
}


