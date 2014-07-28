package me.curiosus.pmmojo.controller.listeners;

import me.curiosus.pmmojo.dbbackup.DBBackup;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * User: jepeterson@gmail.com
 * Date: 4/2/13
 * Time: 6:37 PM
 */
public class DatabaseBackupListener implements ActionListener {


    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogType(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setDialogTitle("Choose Folder to Save Database Backup");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Database Backup";
            }
        });
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();
        try {
            DBBackup.getInstance().doBackup(file);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }


    }

}
