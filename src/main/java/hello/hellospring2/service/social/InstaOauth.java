package hello.hellospring2.service.social;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstaOauth implements SocialOauth {
    @Value("${sns.instagram.url}")
    private String INSTAGRAM_SNS_BASE_URL;
    @Value("${sns.instagram.client.id}")
    private String INSTAGRAM_SNS_CLIENT_ID;
    @Value("${sns.instagram.callback.url}")
    private String INSTAGRAM_SNS_CALLBACK_URL;
    @Value("${sns.instagram.client.secret}")
    private String INSTAGRAM_SNS_CLIENT_SECRET;
    @Value("${sns.instagram.token.url}")
    private String INSTAGRAM_SNS_TOKEN_BASE_URL;
    @Value("${sns.instagram.longtoken.url}")
    private String INSTAGRAM_SNS_LONG_TOKEN_BASE_URL;

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "user_profile,user_media");
        params.put("response_type", "code");
        params.put("client_id", INSTAGRAM_SNS_CLIENT_ID);
        params.put("redirect_uri", INSTAGRAM_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return INSTAGRAM_SNS_BASE_URL + "?" + parameterString;
    }

    @Override
    public String requestAccessToken(String code) {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", INSTAGRAM_SNS_CLIENT_ID);
        params.add("client_secret", INSTAGRAM_SNS_CLIENT_SECRET);
        params.add("grant_type", "authorization_code");
        params.add("redirect_uri", INSTAGRAM_SNS_CALLBACK_URL);
        params.add("code", code);

        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(INSTAGRAM_SNS_TOKEN_BASE_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
//            JSONObject jsonObject = new JSONObject(responseEntity.getBody());
//            String short_access_token = jsonObject.getString("access_token");
//            RestTemplate longRestTemplate = new RestTemplate();
//            MultiValueMap<String, String> longParams = new LinkedMultiValueMap<>();
//            longParams.add("grant_type", "ig_exchange_token");
//            longParams.add("client_secret", INSTAGRAM_SNS_CLIENT_SECRET);
//            longParams.add("access_token", short_access_token);
//            System.out.println(short_access_token);
//
//            ResponseEntity<String> longResponseEntity =
//                    longRestTemplate.postForEntity(INSTAGRAM_SNS_LONG_TOKEN_BASE_URL, longParams, String.class);
//
//            if (longResponseEntity.getStatusCode() == HttpStatus.OK) {
//                return longResponseEntity.getBody();
//            }
//            return "인스타그램 장기 토큰 발행 요청 처리 실패";
        }
        return "인스타그램 로그인 요청 처리 실패";
    }
}