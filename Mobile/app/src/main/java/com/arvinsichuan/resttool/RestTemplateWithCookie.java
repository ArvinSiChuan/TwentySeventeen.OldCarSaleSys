/*
 *     This project is one of projects of ArvinSiChuan.com.
 *     Copyright (C) 2017, ArvinSiChuan.com <https://arvinsichuan.com>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.resttool;

import android.util.Log;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Mobile
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 11/21/2017
 * <p>
 * Package: com.arvinsichuan.RestTool
 *
 * @author ArvinSiChuan
 */
public class RestTemplateWithCookie extends RestTemplate {
    public static final String TAG = "RT_WITH_COOKIES";
    private static final String COOKIE_HEADER_NAME = "Cookie";
    private static final String CSRF_HEADER_IDENTIFIER = "headerName";
    private static final String CSRF_TOKEN_IDENTIFIER = "token";
    private static final String CSRF_TOKEN_URL = "http://192.168.137.1:8080/testSession/csrf";

    private static Map<String, List<String>> cookies = new HashMap<>();
    private static Map<String, String> csrfToken = new HashMap<>();
    private List<HttpMessageConverter<?>> converters = new ArrayList<>();

    public RestTemplateWithCookie() {
        super();
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        setMessageConverters(converters);
        if (csrfToken == null || csrfToken.isEmpty()) {
            retrieveCsrfToken();
        }
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Map<String, ?> uriVariables) throws
            RestClientException {
        return retrieveWithCookies(HttpMethod.POST, url, request, responseType, uriVariables);
    }

    @Override
    public <T> T postForObject(URI url, Object request, Class<T> responseType) throws RestClientException {
        return retrieveWithCookies(HttpMethod.POST, url, request, responseType);
    }

    @Override
    public <T> T postForObject(String url, Object request, Class<T> responseType, Object... uriVariables) throws
            RestClientException {
        return retrieveWithCookies(HttpMethod.POST, url, request, responseType, uriVariables);

    }

    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        T t;
        if (cookies.get(COOKIE_HEADER_NAME) == null) {
            t = retrieveWithCookies(HttpMethod.GET, url.toString(), responseType);
        } else {
            t = super.getForObject(url, responseType);
        }
        return t;
    }

    @Override
    public <T> T getForObject(String url, Class<T> responseType, Object... urlVariables) throws RestClientException {
        T t;
        if (cookies.get(COOKIE_HEADER_NAME) == null) {
            t = retrieveWithCookies(HttpMethod.GET, url, responseType, urlVariables);
        } else {
            t = super.getForObject(url, responseType);
        }
        return t;
    }


    @Override
    public <T> T getForObject(String url, Class<T> responseType, Map<String, ?> urlVariables) throws
            RestClientException {
        T t;
        if (cookies.get(COOKIE_HEADER_NAME) == null) {
            t = retrieveWithCookies(HttpMethod.GET, url, responseType, urlVariables);
        } else {
            t = super.getForObject(url, responseType, urlVariables);
        }
        return t;
    }


    private <T> T retrieveWithCookies(HttpMethod method, String url, Class<T> responseType) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method),
                responseType);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private <T> T retrieveWithCookies(HttpMethod method, String url, Class<T> responseType, Object[] urlVariables) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method),
                responseType,
                urlVariables);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private <T> T retrieveWithCookies(HttpMethod method, String url, Class<T> responseType,
                                      Map<String, ?> urlVariables) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method),
                responseType,
                urlVariables);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private <T> T retrieveWithCookies(HttpMethod method, String url, Object request, Class<T> responseType,
                                      Map<String, ?> uriVariables) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method, request),
                responseType,
                uriVariables);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private <T> T retrieveWithCookies(HttpMethod method, URI url, Object request, Class<T> responseType) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method, request),
                responseType);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private <T> T retrieveWithCookies(HttpMethod method, String url, Object request, Class<T> responseType,
                                      Object... uriVariables) {
        ResponseEntity<T> responseEntity = getInnerRestTemplate().exchange(url, method, getHttpEntity(method, request),
                responseType, uriVariables);
        setCookies(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    private HttpHeaders getCookiesHeaders() {
        HttpHeaders headers = new HttpHeaders();

        if (cookies != null && !cookies.isEmpty()) {
            headers.putAll(cookies);
        }
        Log.d(TAG, "getHeaders: " + headers.toString());
        return headers;
    }

    private HttpHeaders getCsrfHeader() {
        HttpHeaders headers = new HttpHeaders();
        if (csrfToken != null && !csrfToken.isEmpty()) {
            headers.setAll(csrfToken);
        } else {
            retrieveCsrfToken();
            headers.setAll(csrfToken);
        }
        return headers;
    }

    private HttpHeaders getFullHeaders() {
        HttpHeaders headers = getCookiesHeaders();
        headers.setAll(getCsrfHeader().toSingleValueMap());
        return headers;
    }

    private RestTemplate getInnerRestTemplate() {
        RestTemplate template = new RestTemplate();
        template.setMessageConverters(converters);
        return template;
    }

    private HttpEntity<?> getHttpEntity(HttpMethod method) {
        HttpEntity<?> httpEntity;
        switch (method) {
            case GET:
                httpEntity = new HttpEntity<>(getCookiesHeaders());
                break;
            default:
                httpEntity = new HttpEntity<>(getFullHeaders());
                break;
        }
        return httpEntity;
    }

    private HttpEntity<?> getHttpEntity(HttpMethod method, Object request) {
        HttpEntity<?> httpEntity;
        switch (method) {
            case GET:
                httpEntity = new HttpEntity<>(request, getCookiesHeaders());
                break;
            default:
                httpEntity = new HttpEntity<>(request, getFullHeaders());
                break;
        }
        return httpEntity;
    }

    private void setCookies(HttpHeaders httpHeaders) {
        List<String> cookieValues = httpHeaders.get("Set-Cookie");
        if (cookieValues != null && !cookieValues.isEmpty()) {
            cookies.put("Cookie", cookieValues);
        }
    }


    private void retrieveCsrfToken() {
        Map<String, String> csrfMap = getForObject(CSRF_TOKEN_URL, Map.class);
        String headerName = csrfMap.get(CSRF_HEADER_IDENTIFIER);
        String headerValue = csrfMap.get(CSRF_TOKEN_IDENTIFIER);
        csrfToken.put(headerName, headerValue);
    }

}
