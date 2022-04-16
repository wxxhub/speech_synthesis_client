package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Request {
    public Request() {

    }

    public boolean Get() {
        System.out.println("This is request");
        return true;
    }

    public void GetWav(String content) {

        HttpURLConnection con = null;

        StringBuffer resultBuffer = new StringBuffer();

        try {
            URL url = new URL("http://www.wxxhome.com:8888/speech_synthesis");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            // 设置请求参数类型
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            con.setDoOutput(true);
            con.setDoInput(true);
            // 不使用缓存
            con.setUseCaches(false);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
            writer.write("content=\"王京\"");
            writer.close();
            // 得到响应码
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 得到响应流
                InputStream in = con.getInputStream();
                String fileName = "E:\\dev\\java\\speech_synthesis_client\\test2.wav";
                File fWriter = new File(fileName);
                OutputStream fw = new FileOutputStream(fWriter);

                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = in.read(buf)) != -1) {
                    fw.write(buf, 0, len);
                }

                fw.flush();

                System.out.println(resultBuffer.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return resultBuffer;
    }
}
