package src;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class Client implements Runnable {
    String content;
    String savePath = null;
    String urlPath;
    boolean loop = false;
    public static Vector<BufferedInputStream> bisArr = new Vector<>();
    public static String pause="";
    public static String exit=null;

    //传入保存地址直接调用播放

    public Client() {
    }

    public Client(String content, String urlPath) {
        this.content = content;
        this.urlPath = urlPath;
    }

    public Client(String content, String savePath, String urlPath, boolean loop) {
        this.content = content;
        this.savePath = savePath;
        this.urlPath = urlPath;
        this.loop = loop;
    }

    //发送请求，调用语音生成并保存到本地
    public void generate(String content, String savePath, String url, boolean loop) {

        Request request = new Request();
        request.getWav(content, savePath, url);

    }

    //播放本地音乐
    public void test(String savePath) {
        System.out.println("This is Client");
        Player player = new Player();
        player.play(savePath);
    }


    //生成语音，返回字节流
    public BufferedInputStream generate(String content, String url) {
        Request request = new Request();

        return request.getWav(content, url);

    }


    //传入字节流直接播放
    public void test(BufferedInputStream bis) {
        Player player = new Player();
        player.play(bis);


    }
    //当传入的参数为true时，进行循环播放
    public  void test(Boolean loop) throws Exception{
        while (loop) {
            if(exit=="exit"){
                System.out.println("主程序结束！！！");
                break;
            }
            if(pause.equals("pause") ){
                Thread.sleep(1000);
                continue;
            }
            if (bisArr.size() > 0 ) {
                System.out.println("======");
                //播放集合中最后一个字节流
                test(bisArr.remove(bisArr.size() - 1));
            } else {
                Thread.sleep(3000);
            }


        }
    }

    @Override
    public void run() {
        Client client = new Client();
        bisArr.add(0,client.generate(content, urlPath));
        Scanner scanner = new Scanner(System.in);
        String s = "";
        do {
            System.out.println("请输入需要播放的汉字(输入pause暂停，输入start继续播放):");
            s = scanner.next();

            if (s.equals("exit")) {
                exit="exit";
                System.out.println("生成语音程序结束！！！");
                break;
            }

            if(s.equals("pause")){
                pause=s;
                continue;
            }
            if(s.equals("start")){
                pause=s;
                continue;
            }
            for (int i = 0; i < s.length(); i++) {
                if(s.charAt(i)>'\u4e00'&&s.charAt(i)<'\u9fa5'){
                    //生成字节流添加到集合的第一项
                    bisArr.add(0,client.generate(s, urlPath));
                    break;
                }
            }

        } while (true);


    }
}
