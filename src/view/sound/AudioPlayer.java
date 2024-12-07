package view.sound;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.AudioFormat.Encoding;

public class AudioPlayer {
    public AudioPlayer() {
    }

    public static Future<?> playBgm(String path) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            AudioPlayer player = new AudioPlayer();
            System.out.println("Playing: " + path);
            while(true) {
                player.play(path);
            }
        });
    }

    public static Future<?> playSound(String path) {
        return Executors.newSingleThreadExecutor().submit(() -> {
            (new AudioPlayer()).play(path);
        });
    }

    public void play(String filePath) {
        File file = new File(filePath);

        try {
            AudioInputStream in = AudioSystem.getAudioInputStream(file);

            try {
                AudioFormat outFormat = this.getOutFormat(in.getFormat());
                DataLine.Info info = new DataLine.Info(SourceDataLine.class, outFormat);
                SourceDataLine line = (SourceDataLine)AudioSystem.getLine(info);

                try {
                    if (line != null) {
                        line.open(outFormat);
                        line.start();
                        this.stream(AudioSystem.getAudioInputStream(outFormat, in), line);
                        line.drain();
                        line.stop();
                    }
                } catch (Throwable var11) {
                    if (line != null) {
                        try {
                            line.close();
                        } catch (Throwable var10) {
                            var11.addSuppressed(var10);
                        }
                    }

                    throw var11;
                }

                if (line != null) {
                    line.close();
                }
            } catch (Throwable var12) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (Throwable var9) {
                        var12.addSuppressed(var9);
                    }
                }

                throw var12;
            }

            if (in != null) {
                in.close();
            }

        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException var13) {
            Exception e = var13;
            throw new IllegalStateException(e);
        }
    }

    private AudioFormat getOutFormat(AudioFormat inFormat) {
        int ch = inFormat.getChannels();
        float rate = inFormat.getSampleRate();
        return new AudioFormat(Encoding.PCM_SIGNED, rate, 16, ch, ch * 2, rate, false);
    }

    private void stream(AudioInputStream in, SourceDataLine line) throws IOException {
        byte[] buffer = new byte[4096];

        for(int n = 0; n != -1; n = in.read(buffer, 0, buffer.length)) {
            line.write(buffer, 0, n);
        }

    }
}
