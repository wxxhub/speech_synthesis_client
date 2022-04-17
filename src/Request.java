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


    public static String sendRequest(String content) {
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
                String filePath = "C:\\Users\\ADMIN\\Desktop\\语音\\"+content+".wav";
                FileOutputStream fos = new FileOutputStream(filePath);
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, len);
                }
                fos.close();
                return filePath;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

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
