package src;


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


    public  void getWav(String content,String savePath) {
        HttpURLConnection con = null;

        try {
            String  urlPath ="http://127.0.0.1:8888/speech_synthesis";
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

}
