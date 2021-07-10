package src;

public class Client {
    public void Test() {
        System.out.println("This is Client");
        Request request = new Request();
        request.Get();
        
        Player player = new Player();
        player.Play("");
    }
}
