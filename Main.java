import src.Client;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        String savePath ="C:\\Users\\ADMIN\\Desktop\\语音\\1.wav";
        Client client = new Client();
        client.generate("你好",savePath);
        client.test(savePath);

    }
}
