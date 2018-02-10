package mylab.mas.entity;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ApiResponseEntity {

    private String banksTrend;
    private String fcTrend;

    private Double banksFixedDep3m;
    private Double banksFixedDep6m;
    private Double banksFixedDep12m;

    private Double banksSavingsDep;

    private Double fcFixedDep3m;
    private Double fcFixedDep6m;
    private Double fcFixedDep12m;

    private Double fcSavingsDep;

}
