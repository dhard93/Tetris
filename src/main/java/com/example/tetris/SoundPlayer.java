package com.example.tetris;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * This class converts all of the game's sound files into MediaPlayers and is used to play sound effects as well as start, stop, and resume music.
 */

public class SoundPlayer
{
    private static final SoundPlayer instance = new SoundPlayer();
    private AudioClip[] audioClipList;
    private MediaPlayer musicPlayer;
    private Duration musicCyclePosition = Duration.ZERO;

    private SoundPlayer() {
    }

    public static SoundPlayer getInstance()
    {
        return instance;
    }

    /**
     * Iterate audio files and convert each one into a MediaPlayer object that can be used to play them.
     */
    public void getSounds()
    {
        String soundsPath = Objects.requireNonNull(SoundPlayer.class.getResource("/com/example/tetris/Tetris_Sounds")).toExternalForm();
        File directory = new File(Objects.requireNonNull(SoundPlayer.class.getResource("/com/example/tetris/Tetris_Sounds")).getFile());
        File[] files = directory.listFiles();

        assert files != null;
        Arrays.sort(files);

        audioClipList = new AudioClip[files.length];

        String filePath;
        StringBuilder filename;

        for (int fileIndex = 0; fileIndex < Objects.requireNonNull(files).length; fileIndex++)
        {
            if (files[fileIndex].isFile())
            {
                char[] filenameChars = files[fileIndex].getName().toCharArray();
                ArrayList<String> updateChars = new ArrayList<>();
                for (char c : filenameChars)
                {
                    if (c != '/')
                        updateChars.add(String.valueOf(c));
                }

                filename = new StringBuilder();
                for (String character : updateChars)
                    filename.append(character);

                filePath = soundsPath + filename;

                if (fileIndex == 14)
                {
                    Media musicMedia = new Media(filePath);
                    musicPlayer = new MediaPlayer(musicMedia);
                }
                else
                    audioClipList[fileIndex] = new AudioClip(filePath);
            }
        }
    }

    /**
     * Play the sound of the MediaPlayer indicated by the passed in index.
     * @param index The index indicating which MediaPlayer to play.
     */
    public void playSound(int index)
    {
        AudioClip audioClip = audioClipList[index];
        audioClip.stop();
        audioClip.play();
    }

    /**
     * Start the game music loop from the beginning.
     */
    public void startMusic()
    {
        musicPlayer.setAutoPlay(true);
        musicPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        musicPlayer.seek(Duration.ZERO);
        musicPlayer.play();
    }

    /**
     * Stop game music.
     */
    public void stopMusic()
    {
        musicPlayer.pause();
        musicCyclePosition = musicPlayer.getCurrentTime();
    }

    /**
     * Resume game music from where it was last stopped.
     */
    public void resumeMusic()
    {
        musicPlayer.seek(musicCyclePosition);
        musicPlayer.play();
    }
}
