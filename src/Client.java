package src;

public class Client {
    //传入保存地址直接调用播放
    public void test(String savePath) {
        System.out.println("This is Client");
        Player player = new Player();
        player.play(savePath);
    }
    //发送请求，调用语音生成并保存到本地
    public void generate(String content,String savePath,String url) {
        Request request = new Request();
        request.getWav(content,savePath,url);
    }
    //没有保存地址将会调用语音生成并播放
    public void test(String content,String url){
        Request request = new Request();
        request.getWav(content,url);

    }
}
