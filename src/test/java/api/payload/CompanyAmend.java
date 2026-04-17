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
    public static Map<String, Object> CreateUpdateBranchPayload(
            int branchId,
            String onlineBranchUrl,
            String branchType,
            double locationLat,
            double locationLang
    ) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("branch_id", branchId);
        payload.put("online_branch_url", onlineBranchUrl);
        payload.put("branch_type", branchType);
        payload.put("location_lat", locationLat);
        payload.put("location_lang", locationLang);

        return payload;
    }
}
