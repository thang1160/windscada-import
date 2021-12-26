package com.example;

import java.sql.Timestamp;
import java.util.logging.Logger;
import com.example.dao.GetConnection;

public final class App extends Thread {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    private App() {}

    public static void main(String[] args) {
        GetConnection.getConnection();
        App t1 = new App();
        t1.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                logger.info("start insert");
                Timestamp timeStamp1 = new Timestamp(System.currentTimeMillis());
                GetConnection.infiny(timeStamp1);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
