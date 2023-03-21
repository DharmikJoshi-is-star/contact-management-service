package com.contactmangementservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class AuthService {

    private final Boolean authEnabled;
    private final String username;
    private final String password;
    private final List<String> publicUri;

    @Autowired
    public AuthService(@Value("${cms.auth.enabled}") Boolean authEnabled,
                       @Value("${cms.auth.username}") String username,
                       @Value("${cms.auth.password}") String password,
                       @Value("${cms.public.uris}") List<String> publicUri) {
        this.authEnabled = authEnabled;
        this.username = username;
        this.password = password;
        this.publicUri = publicUri;
    }

    public boolean isPublicAPI(String requestUri) {
        return !CollectionUtils.isEmpty(publicUri) &&
                publicUri.stream().anyMatch(uri -> uri.equals(requestUri));
    }

    public boolean isValidCredentials(String username, String password) {
        return (null != authEnabled && !authEnabled) ||
                this.username.equals(username) && this.password.equals(password);
    }
}
