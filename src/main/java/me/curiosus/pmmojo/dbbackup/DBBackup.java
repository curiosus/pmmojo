package me.curiosus.pmmojo.dbbackup;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: jepeterson@gmail.com
 * Date: 6/22/13
 * Time: 8:41 AM
 */
public class DBBackup {

    private volatile static DBBackup instance;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");

    private DBBackup() {}

    public static DBBackup getInstance() {
        if (instance == null) {
           synchronized (DBBackup.class) {
               if (instance == null) {
                  instance = new DBBackup();
               }
           }
        }
        return instance;
    }



    public synchronized void doBackup(final File dir) throws InterruptedException, IOException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ProcessBuilder processBuilder = new ProcessBuilder(createCommand(dir));
                processBuilder.redirectErrorStream(true);
                Process process;
                try {
                    process = processBuilder.start();
                    process.waitFor();
                    JOptionPane.showMessageDialog(null, "Database backup saved to " + dir.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    private String [] createCommand(File dir) {
        String [] command;
        String osName = System.getProperty("os.name");
        if ("Linux".equals(osName)) {
            command = new String[]{"bash", "resources/scripts/dbbackup.sh", createOutputFile(dir)};
        } else {
            command = new String[]{"windows", "resources/scripts/dbbackup.bat", createOutputFile(dir)};
        }
        return command;
    }

    private String createOutputFile(File dir) {
        StringBuilder sb = new StringBuilder(dir.getAbsolutePath()).append("/tek_db_").append(dateFormat.format(new Date())).append(".backup");
        return sb.toString();
    }


}
