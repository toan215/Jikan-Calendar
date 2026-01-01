/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.agent.util;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * ConfigLoader - Load configuration from .env file
 * 
 * Migrated from application.properties to .env for better security
 * All API keys and sensitive data should be in .env file
 * 
 * @author Admin
 */
public class ConfigLoader {
    private static final Dotenv dotenv;

    static {
        try {
            // Load .env file from project root
            dotenv = Dotenv.configure()
                    .ignoreIfMissing() // Don't fail if .env is missing (production uses env vars)
                    .load();

            System.out.println("✅ ConfigLoader: Loaded environment variables successfully");
        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi khi load .env file: " + e.getMessage());
        }
    }

    /**
     * Get environment variable value by key
     * First tries .env file, then falls back to system environment variables
     * 
     * @param key Environment variable key
     * @return Value or null if not found
     */
    public static String get(String key) {
        // First try .env file
        String value = dotenv.get(key);

        // Fallback to system environment (for production deployment)
        if (value == null) {
            value = System.getenv(key);
        }

        return value;
    }
}
