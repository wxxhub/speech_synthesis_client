import src.Client;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        String url = "http://127.0.0.1:8888/speech_synthesis";
        String content = "你好";
        String savePath = "C:\\Users\\ADMIN\\Desktop\\语音\\1.wav";
        if (args != null) {
            for (String arg : args) {
                if (arg.matches("-save=.*")) {
                    savePath = arg.replace("-save=", "");
                    System.out.println("savePath=" + savePath);
                }
                if (arg.matches("-h=.*")) {
                    url = arg.replace("-h=", "");
                    System.out.println("url=" + url);
                }
                if (arg.matches("-c=.*")) {
                    content = arg.replace("-c=", "");
                    System.out.println("content=" + content);
                }
                if (arg.matches("-d=.*")) {
                    boolean bool1 = Boolean.parseBoolean(arg.replace("-d=",""));
                    System.out.println("bool1="+bool1);


                }

            }
        }
        Client client = new Client();
        //生成后保存，之后调用再播放
        client.generate(content, savePath, url);
        client.test(savePath);
        //传入文本直接播放语音
        client.test(content,url);

    }
}
