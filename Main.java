import src.Client;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        Client client = new Client();
        // client.Test();
        client.Generate("你好世界");
    }
}
