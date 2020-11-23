package com.test.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FtpClient {

    public static void main(String[] args) {
        try {
            FTPClient ftpClient = new FTPSClient("TLSv1.2");

            ftpClient.connect(args[0], Integer.parseInt(args[1]));
            ftpClient.login(args[2], args[3]);

            File file = new File(args[4]);
            InputStream targetStream = new DataInputStream(new FileInputStream(file));

            if(!ftpClient.storeFile(args[5], targetStream)) {
                System.out.println("Reply: " + ftpClient.getReply());
                System.out.println("Code: " + ftpClient.getReplyCode());
                System.out.println("Reply text: " + ftpClient.getReplyString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
