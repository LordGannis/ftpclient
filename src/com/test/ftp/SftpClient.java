package com.test.ftp;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpClient {

    public static void main(String[] args) {
        try {
            ChannelSftp channel = setupJsch(args[0], args[1], args[2]);
            System.out.println("Created the Channel");
            channel.connect();
            System.out.println("Connected to the Channel");
            channel.put(args[3], args[4]);
            System.out.println("File sent to the Channel");
            channel.exit();
            System.out.println("Closed the Channel");
        } catch (JSchException | SftpException e) {
            e.printStackTrace();
        }

    }

    private static ChannelSftp setupJsch(String host, String username, String password) throws JSchException {
        JSch jsch = new JSch();
        jsch.setKnownHosts("./known_hosts");
        Session jschSession = jsch.getSession(username, host);
        jschSession.setPassword(password);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");
    }

}
