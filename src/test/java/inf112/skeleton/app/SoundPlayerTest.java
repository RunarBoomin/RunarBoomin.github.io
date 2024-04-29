package inf112.skeleton.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Random;

import javax.sound.sampled.*;

import inf112.skeleton.helper.FileFactory;
import inf112.skeleton.helper.SoundPlayer;

public class SoundPlayerTest {
    private SoundPlayer soundPlayer;
    private SoundPlayer.AudioSystemWrapper audioSystemWrapper;
    private Random random;
    private FileFactory fileFactory;
    private Clip clip;
    private AudioInputStream audioInputStream;

    @BeforeEach
    void setUp() throws Exception {
        audioSystemWrapper = mock(SoundPlayer.AudioSystemWrapper.class);
        random = mock(Random.class);
        fileFactory = mock(FileFactory.class);
        soundPlayer = new SoundPlayer(audioSystemWrapper, random, fileFactory);
        clip = mock(Clip.class);
        audioInputStream = mock(AudioInputStream.class);

        when(audioSystemWrapper.getClip()).thenReturn(clip);
    }

    @Test
    void testPlayRandomSoundWithFiles() throws Exception {
        String folderPath = "sounds";
        File folder = mock(File.class);  // Mocking File
        when(fileFactory.createFile(folderPath)).thenReturn(folder);
        when(folder.exists()).thenReturn(true);
        when(folder.isDirectory()).thenReturn(true);

        File[] files = {new File("sound1.wav"), new File("sound2.wav")};
        when(fileFactory.listFiles(eq(folder), argThat(filter -> filter.accept(folder, "sound1.wav") || filter.accept(folder, "sound2.wav")))).thenReturn(files);

        when(random.nextInt(anyInt())).thenReturn(0);
        when(audioSystemWrapper.getAudioInputStream(any(File.class))).thenReturn(audioInputStream);

        soundPlayer.playRandomSound(folderPath);

        verify(fileFactory).listFiles(eq(folder), argThat(filter -> filter.accept(folder, "sound1.wav") || filter.accept(folder, "sound2.wav")));
        verify(audioSystemWrapper).getAudioInputStream(any(File.class));
        verify(clip).open(audioInputStream);
        verify(clip).start();
    }

    @Test
    void testPlayRandomSoundNoFiles() throws Exception {
        String folderPath = "sounds";
        File folder = new File(folderPath);
        File[] files = new File[0];

        when(fileFactory.createFile(folderPath)).thenReturn(folder);
        when(fileFactory.listFiles(any(File.class), any(FilenameFilter.class))).thenReturn(files);

        soundPlayer.playRandomSound(folderPath);

        verify(random, never()).nextInt(anyInt());
        verify(audioSystemWrapper, never()).getAudioInputStream(any(File.class));
        verify(clip, never()).open(any(AudioInputStream.class));
        verify(clip, never()).start();
    }
}