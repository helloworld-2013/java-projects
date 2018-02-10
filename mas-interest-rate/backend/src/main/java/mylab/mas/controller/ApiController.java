package mylab.mas.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mylab.mas.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ApiController {

    private final ApiService apiService;

    @RequestMapping("/")
    @ResponseBody
    public Object index(@RequestParam(value = "duration", defaultValue = "12") Integer duration,
                        @RequestParam(value = "endMonth", defaultValue = "12") Integer endMonth,
                        @RequestParam(value = "endYear", defaultValue = "2017") Integer endYear) {
        log.debug("Input parameters : Duration : %d : EndMonth : %d : EndYear : %d", duration, endMonth, endYear);
        return apiService.retrieveRates(duration, endMonth, endYear);
    }

}
