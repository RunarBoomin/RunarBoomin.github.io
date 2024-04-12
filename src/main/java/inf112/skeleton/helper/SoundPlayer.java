package inf112.skeleton.helper;

import javax.sound.sampled.AudioInputStream;
    import javax.sound.sampled.AudioSystem;
    import javax.sound.sampled.Clip;
    import javax.sound.sampled.LineUnavailableException;
    import javax.sound.sampled.UnsupportedAudioFileException;
    import java.io.File;
    import java.io.IOException;
    import java.util.Random;

    public class SoundPlayer {
        public static void playSound(String folderPath, String soundFileName) {
            try {
                String soundFilePath = folderPath + File.separator + soundFileName;
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
        
        public static void playRandomSound(String folderPath) {
    
            File folder = new File(folderPath);
    
            if (folder.exists() && folder.isDirectory()) {
                // Get a list of all sound files
                File[] files = folder.listFiles((dir, name) -> name.endsWith(".wav"));
    
                // Check if there are any sound files in folder 
                if (files != null && files.length > 0) {
                    // Generate a random index to select a random sound file
                    Random random = new Random();
                    int randomIndex = random.nextInt(files.length);
    
                    
                    File randomFile = files[randomIndex];
    
                    // Play the randomly selected sound file
                    playSound(folderPath, randomFile.getName());
                } else {
                    System.out.println("No sound files found in the folder.");
                }
            } else {
                System.out.println("Folder does not exist or is not a directory.");
            }
        }
    }