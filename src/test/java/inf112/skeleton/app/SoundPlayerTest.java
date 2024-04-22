package inf112.skeleton.app;

import inf112.skeleton.helper.SoundPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SoundPlayerTest {

    @TempDir
    Path tempDir;

    @Test
    void testPlaySound() {
        try (MockedStatic<AudioSystem> mocked = Mockito.mockStatic(AudioSystem.class)) {
            Clip mockClip = mock(Clip.class);
            AudioInputStream mockStream = mock(AudioInputStream.class);

            mocked.when(AudioSystem::getClip).thenReturn(mockClip);

            doNothing().when(mockClip).open(mockStream);
            doNothing().when(mockClip).start();

            // Call the method under test
            SoundPlayer.playSound("somepath", "test.wav");

            // Verify the behaviors
            verify(mockClip).open(mockStream);
            verify(mockClip).start();
        } catch (LineUnavailableException | IOException e) {
            fail("No exception should be thrown");
        }
    }

    static class AudioSystemMocker {
        void mockGetClip(Clip clip) throws LineUnavailableException {
            try (MockedStatic<AudioSystem> mocked = Mockito.mockStatic(AudioSystem.class)) {
                mocked.when(AudioSystem::getClip).thenReturn(clip);
            }
        }
    }

    @Test
    void testPlayRandomSound_NoFiles() {
        File mockFolder = mock(File.class);
        when(mockFolder.exists()).thenReturn(true);
        when(mockFolder.isDirectory()).thenReturn(true);
        // Specify FilenameFilter explicitly if ambiguity arises
        when(mockFolder.listFiles((FilenameFilter) (dir, name) -> name.endsWith(".wav"))).thenReturn(new File[0]);

        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            @Override
            public void write(int b) {
                // do nothing
            }
        }));

        SoundPlayer.playRandomSound(mockFolder.getAbsolutePath());

        verify(mockFolder, times(1)).listFiles(any(FilenameFilter.class));
    }
}