import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://reqres.in/api/users?page=2";
        String response = restTemplate.getForObject(url, String.class );
        System.out.println(response);

        //for POST request .postForObject()

        Map<String, String> jsonToSend = new HashMap<>();
        jsonToSend.put("name", "test name");
        jsonToSend.put("job", "test job");
        System.out.println("\n");
        HttpEntity<Map<String ,String>> reqEntity = new HttpEntity<>(jsonToSend);
        String urlForCreate = "https://reqres.in/api/users";
        String responseCreate = restTemplate.postForObject(urlForCreate, reqEntity, String.class);
        System.out.println(responseCreate);

    }
}
