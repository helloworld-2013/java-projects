package mylab.mas.service;

import mylab.mas.entity.ApiResponseEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class ApiServiceImplTest {

    @Mock
    private RestTemplate restClient;

    @InjectMocks
    private ApiServiceImpl sut;

    @Before
    public void setUp() {
        String filePath = getClass().getClassLoader().getResource("response.json").getFile();
        File file = new File(filePath);
        StringBuilder sb = new StringBuilder();
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
            scanner.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
        Mockito.when(restClient.getForObject(Mockito.anyString(), Matchers.eq(String.class))).thenReturn(sb.toString());
    }

    @Test
    public void testRetrieveRates() {
        Object result = sut.retrieveRates(12, 12, 2017);
        assertTrue(result instanceof ApiResponseEntity);

        ApiResponseEntity responseEntity = (ApiResponseEntity)result;
        assertEquals("Stable", responseEntity.getBanksTrend());
        assertEquals("Stable", responseEntity.getFcTrend());
    }

}
