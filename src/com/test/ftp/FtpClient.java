package com.test.ftp;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPSClient;

public class FtpClient {

    public static void main(String[] args) {
        try {
        	FTPSClient ftpClient = new FTPSClient("TLSv1.2");
            System.out.println("Created the FTPSClient");

            ftpClient.connect(args[0], Integer.parseInt(args[1]));
            System.out.println("Connected successfully to " + (args[0].concat(":").concat(args[1])));
            boolean login = ftpClient.login(args[2], args[3]);
            System.out.println("Login successfully: " + login);

            File file = new File(args[4]);
            InputStream targetStream = new DataInputStream(new FileInputStream(file));

            System.out.println("Sending file: " + args[4]);
            if(!ftpClient.storeFile(args[5], targetStream)) {
                System.out.println("File sent");
                System.out.println("Reply: " + ftpClient.getReply());
                System.out.println("Code: " + ftpClient.getReplyCode());
                System.out.println("Reply text: " + ftpClient.getReplyString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
