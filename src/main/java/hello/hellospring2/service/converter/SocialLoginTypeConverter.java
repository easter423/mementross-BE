package hello.hellospring2.service.converter;

import org.springframework.core.convert.converter.Converter;
import hello.hellospring2.repository.constants.SocialLoginType;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
    @Override
    public SocialLoginType convert(String s) {
        return SocialLoginType.valueOf(s.toUpperCase());
    }
}