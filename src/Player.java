package src;

import javax.sound.midi.MidiDevice;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.SourceDataLine;
public class Player {
    //读取本地文件，播放语音
    public boolean play(String file) {

        System.out.println("This is Player");
        try {
//            System.out.println(file);
            boolean loop = false;

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file));
            AudioFormat audioFormat = audioInputStream.getFormat();
            System.out.println("采样率:"+audioFormat.getSampleRate());
            System.out.println("总帧数:"+audioFormat.getFrameSize());
            System.out.println("时长(秒):"+audioFormat.getFrameSize()/audioFormat.getSampleRate());

            Info dataLineInfo = new Info(SourceDataLine.class, audioFormat);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);

            byte[] bytes = new byte[1024];
            int len =0;
            sourceDataLine.open(audioFormat,1024);
            sourceDataLine.start();
            while ((len=audioInputStream.read(bytes))>0){
                sourceDataLine.write(bytes,0,len);
            }
            audioInputStream.close();
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        return true;
    };
}
