package org.koreait.commons;

import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.ResourceBundle;

@Component
public class Utils {
    private static ResourceBundle validationsBundle;
    private static ResourceBundle errorsBundle;

    static {
        validationsBundle = ResourceBundle.getBundle("messages.validations");
        errorsBundle = ResourceBundle.getBundle("messages.errors");
    }

    public static String getMessage(String code, String bundleType) {
        // bundleType 이 비어있을 경우 에러가 발생할 수 있기 때문에 Objects.requiredNonNullElse를 사용해 null 값이 들어오면 validation 을 넣는다.
        bundleType = Objects.requireNonNullElse(bundleType, "validation");
        ResourceBundle bundle = bundleType.equals("error")?errorsBundle:validationsBundle;
        try{
            return bundle.getString(code);
        } catch (Exception e){
            return null;
        }
    }
}
