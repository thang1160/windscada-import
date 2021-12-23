package com.example;

import java.sql.Timestamp;
import com.example.dao.GetConnection;

/**
 * Hello world!
 */
public final class App extends Thread {
    private App() {}

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     */
    public static void main(String[] args) throws Exception {
        GetConnection.getConnection();
        App t1 = new App();
        Thread th = new Thread(t1);
        th.start();
    }

    public void run() {
        while (true) {
            try {
                Thread.currentThread().sleep(5000);
                System.out.println("start insert");
                Timestamp timeStamp1 = new Timestamp(System.currentTimeMillis());
                GetConnection.infiny(timeStamp1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
