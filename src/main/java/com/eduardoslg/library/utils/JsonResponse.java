package com.eduardoslg.library.utils;


import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonResponse {

    public static void writeError(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = String.format("{\"message\": \"%s\"}", escapeJson(message));
        response.getWriter().write(json);
    }

    private static String escapeJson(String text) {
        return text.replace("\"", "\\\""); // escapa aspas duplas
    }
}
