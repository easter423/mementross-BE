package hello.hellospring2.service.social;

import hello.hellospring2.repository.constants.SocialLoginType;
import org.json.JSONException;

public interface SocialOauth {
    /**
     * 각 Social Login 페이지로 Redirect 처리할 URL Build
     * 사용자로부터 로그인 요청을 받아 Social Login Server 인증용 code 요청
     */
    String getOauthRedirectURL();
    String requestAccessToken(String code) throws JSONException;

    default SocialLoginType type() {
        if (this instanceof InstaOauth) {
            return SocialLoginType.INSTAGRAM;
        } else {
            return null;
        }
    }
}