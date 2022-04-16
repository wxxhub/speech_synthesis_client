import src.Client;
import src.Request;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        String content="你好世界";
        System.out.println(Request.sendRequest(content));
        Client client = new Client();
        client.Test();
    }
}
