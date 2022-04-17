package src;

public class Client {
    public void Test() {
        System.out.println("This is Client");
        String content="快点起床啦啦啦啦啦";
        String filePath =Request.sendRequest(content);
        Player player = new Player();
        player.Play(filePath);

        


    }
}
