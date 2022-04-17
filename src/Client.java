package src;

public class Client {
    public void test(String savePath) {
        System.out.println("This is Client");
        Player player = new Player();
        player.Play(savePath);
    }

    public void generate(String content,String savePath,String url) {
        Request request = new Request();
        request.getWav(content,savePath,url);
    }
}
