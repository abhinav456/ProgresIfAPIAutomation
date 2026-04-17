package api.payload;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class MerchantCategories {
	
	public static Map<String, Object> createCategoryPayload(
	        String categoryIcon,
	        int showInDashboard,
	        int displayOrder,
	        boolean categoryStatus,
	        String activeStartDate,
	        String activeEndDate
	) {

	    Map<String, Object> payload = new HashMap<>();
	    Random random = new Random();

	    int random1 = random.nextInt(10000);
	    int random2 = random.nextInt(10000);

	    String categoryName = "Cat" + random1 + random2;
	    String categoryCode = "R" + random1 + random2;

	    payload.put("category_name", categoryName);
	    payload.put("category_code", categoryCode);
	    payload.put("category_icon", categoryIcon);
	    payload.put("show_in_dashboard", showInDashboard);
	    payload.put("display_order", displayOrder);
	    payload.put("category_status", categoryStatus);
	    payload.put("active_start_date", activeStartDate);
	    payload.put("active_end_date", activeEndDate);

	    return payload;
	}

	public static Map<String, Object> UpdateCategoryPayload(
	        String categoryName,
	        String categoryCode,
	        String categoryIcon,
	        int showInDashboard,
	        int displayOrder,
	        boolean categoryStatus,
	        String activeStartDate,
	        String activeEndDate
	) {

	    Map<String, Object> payload = new HashMap<>();

	    payload.put("category_name", categoryName);
	    payload.put("category_code", categoryCode);
	    payload.put("category_icon", categoryIcon);
	    payload.put("show_in_dashboard", showInDashboard);
	    payload.put("display_order", displayOrder);
	    payload.put("category_status", categoryStatus);
	    payload.put("active_start_date", activeStartDate);
	    payload.put("active_end_date", activeEndDate);

	    return payload;
	}
}
