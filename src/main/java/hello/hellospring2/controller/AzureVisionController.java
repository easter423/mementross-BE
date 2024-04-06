package hello.hellospring2.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.hellospring2.controller.DTO.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class AzureVisionController {

    @GetMapping("/keywords")
    public String getKeywords(@RequestParam String imageUrl) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://junseoj98.cognitiveservices.azure.com/computervision/imageanalysis:analyze?api-version=2024-02-01&features=DenseCaptions&model-version=latest&language=en&gender-neutral-caption=False";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Ocp-Apim-Subscription-Key", "704992cf082f4da49811ca3406ae55ce");

        String body = "{\n" +
                "    \"url\": \"" + imageUrl + "\"\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        String response = restTemplate.postForObject(url, entity, String.class);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> map = objectMapper.readValue(response, Map.class);
        Map<String, Object> denseCaptionsResult = (Map<String, Object>) map.get("denseCaptionsResult");

        ArrayList values = (ArrayList) denseCaptionsResult.get("values");

        Set<String> result = new HashSet<>();
        for (Object value : values) {
            Map<String, Object> valueMap = (Map<String, Object>) value;
            Value valueObj = objectMapper.convertValue(valueMap, Value.class);

            if(valueObj.getConfidence() > 0.7) {
                result.add(valueObj.getText());
            }
        }

        return objectMapper.writeValueAsString(result);
    }

}
