package com.example.writeFile;

import sun.net.ftp.FtpClient;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Package: com.example.writeFile
 * @Description: ${todo}
 * @author: liuxin
 * @date: 17/4/24 下午3:24
 */
public class FileTest {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMdd");

        System.out.println(sdf.parse("20170424").getTime());
        System.out.println(new Date().getTime());
        System.out.println(sdf.format(new Date()));

        System.out.println(sdf.parse(sdf.format(new Date())).getTime());

        String xxx = "fsafd|ordDate|ordDate|resv|ordid|ordDate|resv|ordDate||||||| price |ordStat";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(xxx).append("\n");
        }
        LocalDate date = LocalDate.now();
        System.out.println(date);
        String fileName = date.toString();
        File file = new File(fileName + "1.txt");
        System.out.println(file.getAbsolutePath()+"AAA");
        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        BufferedWriter bw = new BufferedWriter(fw);
        String content = sb.toString();
        bw.write(content);
        bw.close();
        System.out.println("Done");

    }

    public void writeBill(String content) {
        LocalDate date = LocalDate.now();
        BufferedWriter bw = null;
        System.out.println(date);
        String fileName = date.toString();
        File file = new File(fileName + ".txt");
        // if file doesnt exists, then create it
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
             bw = new BufferedWriter(fw);
            bw.write(content);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
          try {
              bw.close();
          }catch (Exception e){
          }
        }
    }
}
