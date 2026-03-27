package api.payload;

import java.util.HashMap;
import java.util.Map;

public class CompanyAmend {

    public static Map<String, Object> createCompanyAmendPayload(
            int companyId,
            boolean featureCompany,
            boolean showOnDashboard,
            String startDate,
            String endDate,
            boolean isActive
    ) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("company_id", companyId);
        payload.put("feature_company", featureCompany);
        payload.put("show_on_dashboard", showOnDashboard);
        payload.put("active_start_date", startDate);
        payload.put("active_end_date", endDate);
        payload.put("is_active", isActive);

        return payload;
    }
}
