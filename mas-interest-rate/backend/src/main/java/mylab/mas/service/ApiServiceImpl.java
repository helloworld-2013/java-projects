package mylab.mas.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylab.mas.entity.ApiResponseEntity;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiServiceImpl implements ApiService {

    private final RestTemplate restClient;

    @Override
    public Object retrieveRates(Integer duration, Integer endMonth, Integer endYear) {
        StringBuilder url = new StringBuilder();

        LocalDate endDate = LocalDate.of(endYear, endMonth, 1);
        LocalDate startDate = endDate.minusMonths(duration);

        url.append("https://eservices.mas.gov.sg/api/action/datastore/search.json?resource_id=5f2b18a8-0883-4769-a635-879c63d3caac")
                .append(createQueryParam(duration, startDate, endDate));
        log.debug("Url -> %s", url.toString());

        return createResponse(restClient.getForObject(url.toString(), String.class), startDate);
    }

    private String createQueryParam(Integer duration, LocalDate startDate, LocalDate endDate) {
        StringBuilder sb = new StringBuilder();
        sb.append("&between[end_of_month]=")
                .append(startDate.getYear())
                .append("-")
                .append(String.format("%02d", startDate.getMonthValue()))
                .append(",")
                .append(endDate.getYear())
                .append("-")
                .append(String.format("%02d", endDate.getMonthValue()));

        return sb.toString();
    }

    private ApiResponseEntity createResponse(String response, LocalDate startDate) {
        JSONObject jsonObj = new JSONObject(response);
        JSONArray jsonArr = jsonObj.getJSONObject("result").getJSONArray("records");

        double banksFixedDep3m = 0.0;
        double banksFixedDep6m = 0.0;
        double banksFixedDep12m = 0.0;
        double banksSavingsDep = 0.0;

        double fcFixedDep3m = 0.0;
        double fcFixedDep6m = 0.0;
        double fcFixedDep12m = 0.0;
        double fcSavingsDep = 0.0;

        int count = 0;
        List<Double> banksFixedDep12mList = new ArrayList<>();
        List<Double> fcFixedDep12mList = new ArrayList<>();
        Iterator it = jsonArr.iterator();
        while (it.hasNext()) {
            JSONObject json = (JSONObject) it.next();

            double banksFixedDep12mVal = json.optDouble("banks_fixed_deposits_12m", 0.0);
            double fcFixedDep12mVal = json.optDouble("fc_fixed_deposits_12m", 0.0);

            banksFixedDep3m += json.optDouble("banks_fixed_deposits_3m", 0.0);
            banksFixedDep6m += json.optDouble("banks_fixed_deposits_6m", 0.0);
            banksFixedDep12m += banksFixedDep12mVal;
            banksSavingsDep += json.optDouble("banks_savings_deposits", 0.0);

            banksFixedDep12mList.add(banksFixedDep12mVal);

            fcFixedDep3m += json.optDouble("fc_fixed_deposits_3m", 0.0);
            fcFixedDep6m += json.optDouble("fc_fixed_deposits_6m", 0.0);
            fcFixedDep12m += fcFixedDep12mVal;
            fcSavingsDep += json.optDouble("fc_savings_deposits", 0.0);

            fcFixedDep12mList.add(fcFixedDep12mVal);

            count++;
        }

        banksFixedDep3m /= count;
        banksFixedDep6m /= count;
        banksFixedDep12m /= count;
        banksSavingsDep /= count;

        fcFixedDep3m /= count;
        fcFixedDep6m /= count;
        fcFixedDep12m /= count;
        fcSavingsDep /= count;

        String banksTrend = getTrend(banksFixedDep12mList);
        String fcTrend = getTrend(fcFixedDep12mList);

        ApiResponseEntity result = ApiResponseEntity.builder()
                .banksTrend(banksTrend)
                .banksFixedDep3m(banksFixedDep3m)
                .banksFixedDep6m(banksFixedDep6m)
                .banksFixedDep12m(banksFixedDep12m)
                .banksSavingsDep(banksSavingsDep)
                .fcTrend(fcTrend)
                .fcFixedDep3m(fcFixedDep3m)
                .fcFixedDep6m(fcFixedDep6m)
                .fcFixedDep12m(fcFixedDep12m)
                .fcSavingsDep(fcSavingsDep).build();
        return result;
    }

    private String getTrend(List<Double> data) {
        SimpleRegression regression = new SimpleRegression(true);
        for (int i = 0;i < data.size();i++) {
            regression.addData(i+1, data.get(i));
        }
        double slope = regression.getSlope();
        String trend = (slope > 0)?"Uptrend":(slope < 0)?"Downtrend":"Stable";
        return trend;
    }

}
