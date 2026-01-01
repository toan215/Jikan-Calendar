package com.database;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Database Configuration Interface
 * Reads credentials from .env file for security
 * Supports multiple database types: SQL Server (Local) and PostgreSQL
 * (Supabase)
 * 
 * @author DELL
 */
public interface DBinformation {

    // Load .env file (automatically looks for .env in project root)
    Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing() // Don't fail if .env is missing (use defaults)
            .load();

    // ========== DATABASE TYPE SELECTION ==========
    String DB_TYPE = dotenv.get("DB_TYPE", "SQLSERVER");

    // ========== SQL SERVER CONFIGURATION (Local) ==========
    String SQLSERVER_DRIVER = dotenv.get("SQLSERVER_DRIVER", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
    String SQLSERVER_URL = dotenv.get("SQLSERVER_URL",
            "jdbc:sqlserver://localhost:1433;databaseName=Calendar;trustServerCertificate=true");
    String SQLSERVER_USER = dotenv.get("SQLSERVER_USER", "sa");
    String SQLSERVER_PASS = dotenv.get("SQLSERVER_PASS", "123456");

    // ========== SUPABASE CONFIGURATION (PostgreSQL Cloud) ==========
    String SUPABASE_DRIVER = dotenv.get("SUPABASE_DRIVER", "org.postgresql.Driver");
    String SUPABASE_URL = dotenv.get("SUPABASE_URL",
            "jdbc:postgresql://aws-0-ap-southeast-1.pooler.supabase.com:5432/postgres");
    String SUPABASE_USER = dotenv.get("SUPABASE_USER", "postgres.YOUR_PROJECT_REF");
    String SUPABASE_PASS = dotenv.get("SUPABASE_PASS", "your_supabase_password_here");

    // ========== ACTIVE DATABASE CONFIGURATION ==========
    // Auto-selected based on DB_TYPE
    String driverName = DB_TYPE.equals("SUPABASE") ? SUPABASE_DRIVER : SQLSERVER_DRIVER;
    String dbURL = DB_TYPE.equals("SUPABASE") ? SUPABASE_URL : SQLSERVER_URL;
    String userDB = DB_TYPE.equals("SUPABASE") ? SUPABASE_USER : SQLSERVER_USER;
    String passDB = DB_TYPE.equals("SUPABASE") ? SUPABASE_PASS : SQLSERVER_PASS;
}
