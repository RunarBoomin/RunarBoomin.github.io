package inf112.skeleton.helper;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class SoundPlayer {
    private AudioSystemWrapper audioSystemWrapper;
    private Random random;
    private FileFactory fileFactory;

    public SoundPlayer(AudioSystemWrapper audioSystemWrapper, Random random, FileFactory fileFactory) {
        this.audioSystemWrapper = audioSystemWrapper;
        this.random = random;
        this.fileFactory = fileFactory;
    }

    public void playSound(String folderPath, String soundFileName) {
        try {
            String soundFilePath = folderPath + File.separator + soundFileName;
            AudioInputStream audioInputStream = audioSystemWrapper.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
            Clip clip = audioSystemWrapper.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playRandomSound(String folderPath) {
        File folder = fileFactory.createFile(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            File[] files = fileFactory.listFiles(folder, (dir, name) -> name.endsWith(".wav"));
            if (files != null && files.length > 0) {
                File randomFile = files[random.nextInt(files.length)];
                playSound(folderPath, randomFile.getName());
            } else {
                System.out.println("No sound files found in the folder.");
            }
        } else {
            System.out.println("Folder does not exist or is not a directory.");
        }
    }

    public static class AudioSystemWrapper {
        public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
            return AudioSystem.getAudioInputStream(file);
        }

        public Clip getClip() throws LineUnavailableException {
            return AudioSystem.getClip();
        }
    }
}