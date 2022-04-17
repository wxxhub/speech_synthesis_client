import src.Client;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");


        String content="快点起床";
        String filePath =Request.sendRequest(content);
        Player player = new Player();
        player.Play(filePath);

        Client client = new Client();
        // client.Test();
        client.Generate("你好世界");
    }
}
