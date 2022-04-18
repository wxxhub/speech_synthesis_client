package src;


import javax.sound.sampled.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class Request {
    public Request() {

    }

    public boolean get() {
        System.out.println("This is request");


        return true;
    }

    //当传入文本、保存地址、网址时，下载到本地保存
    public  void getWav(String content,String savePath,String urlPath) {
        HttpURLConnection con = null;

        try {
//            String  urlPath ="http://127.0.0.1:8888/speech_synthesis";
            URL url = new URL(urlPath);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            //设置请求参数类型
            con.setRequestProperty("Content-type", "application/x-www-form-urlencoded");

            con.setDoInput(true);
            con.setDoOutput(true);
            //不使用缓存
            con.setUseCaches(false);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            writer.write("content=\""+content+"\"");

            writer.close();

            //获得请求码
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //获得响应流
                InputStream inputStream = con.getInputStream();

                FileOutputStream fos = new FileOutputStream(savePath);
                byte[] buf = new byte[1024];
                int len = 0;

                while ((len = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, len);

                }

                fos.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //当传入文本和网址时，合成语音并直接播放
    public void getWav(String content,String urlPath){
        HttpURLConnection conn=null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type"," application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bufferedWriter.write("content=\""+content+"\"");
            bufferedWriter.close();
            int responseCode =conn.getResponseCode();
            //将字节流转换为声音系统可以接受的格式
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis);
            AudioFormat audioFormat = audioInputStream.getFormat();
            System.out.println("采样率:"+audioFormat.getSampleRate());
            System.out.println("总帧数:"+audioFormat.getFrameSize());
            System.out.println("时长(秒):"+audioFormat.getFrameSize()/audioFormat.getSampleRate());

            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

            byte[] bytes = new byte[1024];
            int len =0;
            sourceDataLine.open(audioFormat,1024);
            sourceDataLine.start();
            while ((len=audioInputStream.read(bytes))>0){
                sourceDataLine.write(bytes,0,len);
            }
            audioInputStream.close();
            sourceDataLine.drain();
            sourceDataLine.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
