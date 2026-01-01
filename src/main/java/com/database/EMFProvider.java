package com.database;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * EntityManagerFactory Provider - Centralized EMF creation
 * Uses DBinformation constants instead of hardcoded values in persistence.xml
 * 
 * @author DELL
 */
public class EMFProvider {

    private static EntityManagerFactory emf;
    private static final String PERSISTENCE_UNIT_NAME = "CLDPU";

    /**
     * Get singleton EntityManagerFactory instance
     * Configured with DBinformation constants
     * 
     * @return EntityManagerFactory instance
     */
    public static synchronized EntityManagerFactory getEntityManagerFactory() {
        if (emf == null || !emf.isOpen()) {
            Map<String, String> properties = new HashMap<>();

            // Override persistence.xml properties with DBinformation constants
            properties.put("jakarta.persistence.jdbc.url", DBinformation.dbURL +
                    (DBinformation.driverName.contains("sqlserver") ? ";encrypt=true" : ""));
            properties.put("jakarta.persistence.jdbc.user", DBinformation.userDB);
            properties.put("jakarta.persistence.jdbc.password", DBinformation.passDB);
            properties.put("jakarta.persistence.jdbc.driver", DBinformation.driverName);

            // Auto-detect Hibernate dialect based on driver
            String dialect;
            if (DBinformation.driverName.contains("postgresql")) {
                dialect = "org.hibernate.dialect.PostgreSQLDialect";
            } else {
                dialect = "org.hibernate.dialect.SQLServer2016Dialect";
            }

            properties.put("hibernate.dialect", dialect);
            properties.put("hibernate.hbm2ddl.auto", "validate");
            properties.put("hibernate.show_sql", "true");

            emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, properties);
        }
        return emf;
    }

    /**
     * Close EntityManagerFactory (for cleanup)
     */
    public static synchronized void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    /**
     * Check if EMF is open
     * 
     * @return true if EMF is open
     */
    public static boolean isOpen() {
        return emf != null && emf.isOpen();
    }
}
