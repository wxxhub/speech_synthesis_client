import src.Client;

import java.io.BufferedInputStream;

public class Main {
    public static void main(String[] args) throws Exception {

        String urlPath = "http://127.0.0.1:8888/speech_synthesis";
        String content = "你好";
        String savePath = "C:\\Users\\ADMIN\\Desktop\\语音\\1.wav";
        boolean loop = false;
        if (args != null) {
            for (String arg : args) {
                if (arg.matches("-save=.*")) {
                    savePath = arg.replace("-save=", "");
                }
                if (arg.matches("-h=.*")) {
                    urlPath = arg.replace("-h=", "");
                }
                if (arg.matches("-c=.*")) {
                    content = arg.replace("-c=", "");
                }
                if (arg.matches("-d=.*")) {
                    loop = Boolean.parseBoolean(arg.replace("-d=", ""));

                }

            }
        }
        //保存到本地,从本地读取播放
        Client client2 = new Client(content, savePath, urlPath, loop);
        client2.generate(content, savePath, urlPath, loop);
        client2.test(savePath);

        //将初始的语音进行播放
        Client client1 = new Client();
        BufferedInputStream generate = client1.generate(content, urlPath);
        client1.test(generate);

        //调用循环输入的线程，启动依次播放的方法
        Client client = new Client(content, urlPath);
        Thread thread1 = new Thread(client);
        thread1.start();
        client.test(loop);


    }
}
