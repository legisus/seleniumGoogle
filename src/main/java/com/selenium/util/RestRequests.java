package com.selenium.util;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class RestRequests {


    public ValidatableResponse getBaseRequest(String baseUri, String pathUri, Map<String, Object> parameters, Map<String, String> headers) {
        log.info("Sending universal GET REQUEST on address {}{}", baseUri, pathUri);

        return RestAssured
                .given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .baseUri(baseUri)
                .params(parameters)
                .when()
                .get(pathUri)
                .then()
                .noRootPath();
    }
}
