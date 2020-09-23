package APP;

import uk.co.caprica.vlcj.media.MediaEventListener;
import uk.co.caprica.vlcj.player.base.MediaApi;
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.lang.Thread.sleep;

public class MediaPlayer {

    private AudioPlayerComponent audioPlayerComponent;
    private final ScheduledExecutorService executorService;
    private Music musicPlaying;
    private List<Music> musicList;                              /* Lista de músicas a reproduzir */
    private Integer pos;                                        /* Posição da música a reproduzir no array*/

    public MediaPlayer() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.musicList = new ArrayList<Music>();
        this.audioPlayerComponent = new AudioPlayerComponent();
        this.pos = 0;
    }

    public void playMusic(Music m) {
        this.audioPlayerComponent.mediaPlayer().media().play(m.getPath());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.musicPlaying = m;
        this.musicList.add(this.pos,m);
    }

    public void pauseMusic() {
        this.audioPlayerComponent.mediaPlayer().controls().pause();
    }

    public void resumeMusicPlay() {
        if(this.audioPlayerComponent.mediaPlayer().status().state().toString().equals("NOTHING_SPECIAL")) {
            if(this.musicList.size()>0) this.playMusic(this.musicList.get(this.pos));
        } else {
            this.audioPlayerComponent.mediaPlayer().controls().play();
        }
    }

    public void stopMusicPlay() {
        this.audioPlayerComponent.mediaPlayer().controls().stop();
    }

    public void playNextMusic() {
        if(this.pos+1<musicList.size()) {
            this.pos++;
            this.audioPlayerComponent.mediaPlayer().media().play(musicList.get(this.pos).getPath());
        }
    }

    public void playPreviousMusic() {
        if(this.pos>0) {
            this.pos--;
            this.audioPlayerComponent.mediaPlayer().media().play(musicList.get(this.pos).getPath());
        }
    }

    public void addToQueue(Music m) {
        this.musicList.add(m);
    }

    public void clearList() {
        this.musicList.clear();
    }

    public void setMusicSound(Integer sound) {
        this.audioPlayerComponent.mediaPlayer().audio().setVolume(sound);
    }
}

