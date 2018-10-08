package com.whxx.core.utils;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Vector;


/**
 * SFTP工具类
 *
 * @author 涂鹏飞
 * @date 2016年12月19日 上午11:41:03
 */
public class SftpUtil {
    private static Log log = LogFactory.getLog(SftpUtil.class.getSimpleName());

    private Session session;
    private ChannelSftp sftp;

    private String username;
    private String password;
    private String host;
    private int port;
    private int timeout = 0;

    /**
     * 构造基于密码认证的sftp对象
     *
     * @param username 登录用户名
     * @param host     服务器IP地址
     * @param port     端口
     * @param password 登录密码
     */
    public SftpUtil(String username, String host, int port, String password) {
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;

    }

    /**
     * 连接sftp服务器
     */
    public void connect() throws Exception {
        JSch jsch = new JSch();
        session = jsch.getSession(username, host, port);
        session.setPassword(password);

        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        session.setTimeout(timeout);

        session.connect();
        log.info("sshSession连接成功");

        sftp = (ChannelSftp) session.openChannel("sftp");
        sftp.connect();
        log.info("sftp连接成功");
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (sftp != null) {
            if (sftp.isConnected()) {
                sftp.disconnect();
                log.info("sftp关闭成功");
            }
        }
        if (session != null) {
            if (session.isConnected()) {
                session.disconnect();
                log.info("sshSession关闭成功");
            }
        }
    }

    /**
     * 将输入流的数据上传到sftp作为文件
     *
     * @param basePath SFTP服务器基础目录
     * @param filePath SFTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 文件保存名
     * @param input    输入流
     */
    public boolean upload(String basePath, String filePath, String filename, InputStream input) {
        boolean flag = false;
        try {
            this.connect();

            try {
                sftp.cd(basePath + filePath);
            } catch (SftpException e) {
                log.warn("目录不存在:" + basePath + filePath, e);
                // 如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (StringUtils.isEmpty(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;

                    try {
                        sftp.cd(tempPath);
                    } catch (SftpException e2) {
                        sftp.mkdir(tempPath);
                        log.info("创建目录成功:" + tempPath);
                        sftp.cd(tempPath);
                    }
                }
            }
            sftp.put(input, filename);
            log.info("上传文件成功:" + filename);
            flag = true;
        } catch (Exception e) {
            log.error("上传文件失败", e);
        } finally {
            try
            {
                input.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            this.disconnect();
        }
        return flag;
    }

    /**
     * 上传单个文件
     *
     * @param basePath 基本路径
     * @param filePath 文件存放路径
     * @param fileName 文件名字
     * @return true or false
     * @throws Exception
     */
    public boolean upload(String basePath, String filePath, String fileName) throws Exception {
        File file = new File(fileName);
        return upload(basePath, filePath, file.getName(), new FileInputStream(file));
    }

    /**
     * 下载文件
     *
     * @param directory 下载目录
     * @param fileName  要下载的文件名
     * @param saveFile  存在本地的路径，包括文件名
     */
    public boolean download(String directory, String fileName, String saveFile) {
        boolean flag = false;
        try {
            this.connect();

            sftp.cd(directory);
            FileUtils.copyInputStreamToFile(sftp.get(fileName), new File(saveFile));
            log.info("下载文件成功:" + fileName);
            flag = true;
        } catch (Exception e) {
            log.error("下载文件失败:" + fileName, e);
        } finally {
            this.disconnect();
        }
        return flag;
    }

    /**
     * 删除文件
     *
     * @param directory 要删除文件所在目录
     * @param fileName  要删除的文件名
     */
    public boolean delete(String directory, String fileName) {
        boolean flag = false;
        try {
            this.connect();
            sftp.cd(directory);
            sftp.rm(fileName);
            log.info("文件删除成功" + fileName);
            flag = true;
        } catch (Exception e) {
            log.error("文件删除失败:" + directory + fileName, e);
        } finally {
            this.disconnect();
        }
        return flag;
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     */
    public Vector<?> listFiles(String directory) throws Exception {
        try {
            this.connect();
            return sftp.ls(directory);
        } finally {
            this.disconnect();
        }
    }


//    /**
//     * 将输入流的图片数据上传到sftp作为文件，并进行压缩
//     * 默认500x500 + 96x96
//     *
//     * @param basePath SFTP服务器基础目录
//     * @param filePath SFTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath     * @param fileType
//     * @param storeName 文件存储名 （不含格式后缀）
//     * @param fileType 文件格式后缀
//     * @param input 文件流
//     * @return
//     */
//    public boolean uploadImageAndResize(String basePath, String filePath, String storeName, String fileType, InputStream input) {
//        boolean flag = false;
//        try {
//            try {
//                sftp.cd(basePath + filePath);
//            } catch (SftpException e) {
//                log.warn("目录不存在:" + basePath + filePath, e);
//                // 如果目录不存在创建目录
//                String[] dirs = filePath.split("/");
//                String tempPath = basePath;
//                for (String dir : dirs) {
//                    if (StringUtils.isEmpty(dir)) {
//                        continue;
//                    }
//                    tempPath += "/" + dir;
//                    try {
//                        sftp.cd(tempPath);
//                    } catch (SftpException e2) {
//                        sftp.mkdir(tempPath);
//                        log.info("创建目录成功:" + tempPath);
//                        sftp.cd(tempPath);
//                    }
//                }
//            }
//            OutputStream out = sftp.put(storeName + fileType);
//            Thumbnails.of(input)
//                    .size(600, 600)
//                    .toOutputStream(out);
//            IOUtils.closeQuietly(out);
//            log.info("上传文件成功:" + storeName + fileType);
//
//            out = sftp.put(storeName + "_small" + fileType);
//            Thumbnails.of(sftp.get(storeName + fileType))
//                    .size(96, 96)
//                    .toOutputStream(out);
//            log.info("上传文件成功:" + storeName + "_small" + fileType);
//            IOUtils.closeQuietly(out);
//            flag = true;
//        } catch (Exception e) {
//            log.error("上传文件失败", e);
//        } finally {
//            IOUtils.closeQuietly(input);
//        }
//        return flag;
//    }


    public static void main(String[] args) throws Exception {
        SftpUtil ftp = new SftpUtil("sftpuser", "192.168.10.65", 22,
                "sftpuser");
        ftp.connect();
//         boolean flag = ftp.upload("/home/ftpuser/www/images",
//         "/2015/01/21", "gaigeming11.jpg",
//         FileUtils.openInputStream(new
//         File("/Users/huludawang/Downloads/20170909120849599031.jpg")));

//        boolean flag = ftp.uploadImageAndResize("/home/sftpuser/www/images",
//                "/2015/01/21", "201709091208495990311",".jpg",  FileUtils.openInputStream(new
//                        File("/Users/huludawang/Downloads/20170909120849599031.jpg")));
//         flag = ftp.uploadImageAndResize("/home/sftpuser/www/images",
//                "/2015/01/21", "201709091208495990311",".jpg",  FileUtils.openInputStream(new
//                        File("/Users/huludawang/Downloads/20170909120849599031.jpg")));
//        ftp.disconnect();
//        System.out.println(flag);
        //
        // flag = ftp.download("/home/ftpuser/www/images/2016/12/16",
        // "20161216182735244007.jpg",
        // "C:/Users/Administrator/Desktop/yourname-111.jpg");
        // System.out.println(flag);

        // flag = ftp.delete("/home/sftpuser/www/images/2016/12/19",
        // "20161219171804399782.jpg");
        // System.out.println(flag);

    }

}