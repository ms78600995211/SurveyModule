/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apogee.gnss.scheduler;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.ws.rs.core.Context;
import org.json.JSONException;

/**
 *
 * @author lENOVO
 */
//@Component
//public class Scheduler {
//
//    @Scheduled(fixedRate = 5000)
//    public String testscheduler() {
//        System.out.println("Hello");
//        System.out.println("Hii");
//        return null;
//    }
//}
public class Scheduler implements ServletContextListener {

//    @Context
    ServletContext servletContext;
    int scheduler_count = 0;
    String path = "";
    Connection connection = null;

    class VodTimerTask extends TimerTask {

        public String testscheduler() throws MalformedURLException, IOException, JSONException {
            System.out.println("Hello");
            System.out.println("Hii");
            return null;
        }

        @Override
        public void run() {
            try {
                testscheduler();
            } catch (IOException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(Scheduler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        TimerTask vodTimer = new VodTimerTask();
        Timer timer = new Timer();

        Date date = new Date();
        // timer.schedule(vodTimer, 900000);
        timer.schedule(vodTimer, 5 * 1000, (5 * 1000));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method will be called when the application is shutting down
        // You can use this method to perform any cleanup tasks if needed

    }
}
