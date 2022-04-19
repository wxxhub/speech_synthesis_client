package src;




import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;



public class Request  {


    public Request() {
    }

    public boolean get() {
        System.out.println("This is request");
        return true;
    }

    //当传入文本、保存地址、网址时，下载到本地保存
    public void getWav(String content, String savePath, String urlPath) {
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
            writer.write("content=\"" + content + "\"");

            writer.close();

            //获得请求码
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                //获得响应流
                InputStream inputStream = con.getInputStream();
                byte[] buf = new byte[1024];
                int len = 0;
                //根据文件是否存在进行追加判定
                File file = new File(savePath);

                if (file.exists()) {
                    System.out.println("文件存在，继续追加");
                    FileOutputStream fos = new FileOutputStream(file, true);
                    int j=0;

                    while ((len = inputStream.read(buf)) != -1) {
                        //文件前一部分为配置文件

//                        if((j++)==0&&len>46){
//                            fos.write(buf,46,len-46);
//                            continue;
//                        }

                        fos.write(buf, 0, len);
                    }

                    fos.close();
                } else {
                    System.out.println("文件不存在，新建文件");
                    FileOutputStream fos = new FileOutputStream(file, true);

                    while ((len = inputStream.read(buf)) != -1) {
                        fos.write(buf, 0, len);

                    }
                    fos.close();
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //当传入文本和网址时，返回字节流，该字节流可以直接播放
    public BufferedInputStream getWav(String content, String urlPath) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", " application/x-www-form-urlencoded");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            bufferedWriter.write("content=\"" + content + "\"");
            bufferedWriter.close();
            int responseCode = conn.getResponseCode();
            //将字节流转换为声音系统可以接受的格式
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());

            return bis;


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
