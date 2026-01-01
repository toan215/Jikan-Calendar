package com.listener;

import com.reminder.ReminderScheduler;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {

    private ReminderScheduler reminderScheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TEMPORARILY DISABLED - Testing PostgreSQL connection
        // reminderScheduler = new ReminderScheduler();
        // reminderScheduler.start();
        System.out.println(">>> AppListener initialized (ReminderScheduler disabled for connection testing)");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (reminderScheduler != null) {
            reminderScheduler.stop();
        }
        System.out.println(">>> App destroyed");
    }
}
