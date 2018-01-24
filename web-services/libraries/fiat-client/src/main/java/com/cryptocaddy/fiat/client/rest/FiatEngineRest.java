package com.cryptocaddy.fiat.client.rest;

import com.cryptocaddy.fiat.client.util.Constant;
import com.cryptocaddy.fiat.client.wrapper.RestTemplateWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * Created by Nick Fields
 * Date: 1/7/2018
 */
@Component
public class FiatEngineRest implements IFiatEngineRest {
    private final RestTemplateWrapper restTemplateWrapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(FiatEngineRest.class);

    @Value(Constant.FIAT_ENGINE_BASE_URL)
    private String baseUrl;

    @Autowired
    public FiatEngineRest(RestTemplateWrapper restTemplateWrapper) {
        this.restTemplateWrapper = restTemplateWrapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object lookupFiatValue() {
        try {
            String requestUrl = String.join(Constant.DELIMITER, baseUrl);
            return (Object) restTemplateWrapper.exchange(requestUrl, HttpMethod.GET, null, Object.class);
        } catch (Exception e) {
            LOGGER.error("FiatEngine rest service exchange failed: ", e);
        }

        return null;
    }

}