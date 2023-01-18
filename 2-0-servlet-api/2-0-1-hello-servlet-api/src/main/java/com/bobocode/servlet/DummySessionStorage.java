package com.bobocode.servlet;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

public class DummySessionStorage {
    public static final String BOBO_SESSION = "BOBOSESSIONID";
    private Map<String, Map<String, Object>> sessions;
    private static DummySessionStorage dummySession;

    private DummySessionStorage() {
        this.sessions = new HashMap<>();
    }

    public static DummySessionStorage init() {
        if (dummySession == null) {
            dummySession = new DummySessionStorage();
        }
        return dummySession;
    }

    public Map<String, Object> getSession(HttpServletRequest request, HttpServletResponse response) {
        var cookie =
                Arrays.stream(request.getCookies()).filter(elem -> elem.getName().equals(BOBO_SESSION)).findAny().orElse(
                        new Cookie(BOBO_SESSION, UUID.randomUUID().toString())
                );
        response.addCookie(cookie);
        this.sessions.putIfAbsent(cookie.getValue(), new HashMap<>());
        return this.sessions.get(cookie.getValue());
    }
}
