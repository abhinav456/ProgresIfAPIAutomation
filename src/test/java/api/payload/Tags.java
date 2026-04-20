package api.payload;

import java.util.*;
public class Tags {
	
	public static Map<String, Object> createTagPayload(
	        String tagName,
	        String tagDescription,
	        boolean isBannerTag,
	        String bannerImage,
	        String bannerStartDate,
	        String bannerEndDate,
	        String tagStartDate,
	        String tagEndDate,
	        int displayOrder
	) {

	    Map<String, Object> payload = new HashMap<>();

	    int randomNum = 1000 + new Random().nextInt(9000);
	    String uniqueTagName = tagName + "_" + randomNum;

	    payload.put("tag_name", uniqueTagName);
	    payload.put("tag_description", tagDescription);
	    payload.put("is_banner_tag", isBannerTag);
	    payload.put("banner_image", bannerImage);
	    payload.put("banner_active_start_date", bannerStartDate);
	    payload.put("banner_active_end_date", bannerEndDate);
	    payload.put("tag_active_start_date", tagStartDate);
	    payload.put("tag_active_end_date", tagEndDate);
	    payload.put("display_order", displayOrder);

	    return payload;
	}
	
	
}
