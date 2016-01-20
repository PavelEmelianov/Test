package com.nixsolutions.emelianov.utils;

import java.io.OutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaptchaValidator {

    private static final Logger logger = LoggerFactory
            .getLogger(CaptchaValidator.class);
    public static final String SITE_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || gRecaptchaResponse.length() == 0) {
            return false;
        }

        try {
            URL verifyUrl = new URL(SITE_VERIFY_URL);

            HttpsURLConnection conn = (HttpsURLConnection) verifyUrl
                    .openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            String postParams = "secret="
                    + "6LdNHxMTAAAAAImvZOdZQP_k_4Iie5A_VkyH0HUm" + "&response="
                    + gRecaptchaResponse;
            conn.setDoOutput(true);
            OutputStream outStream = conn.getOutputStream();
            outStream.write(postParams.getBytes());
            outStream.flush();
            outStream.close();
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return false;
        }
    }
}
