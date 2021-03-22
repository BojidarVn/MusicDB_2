package bg.example.demo.model.service;

import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.model.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

public class LogServiceModel {

    private Long id;

    private String user;

    private String album;

    private String action;

    private LocalDateTime dateTime;


    public LogServiceModel() {
    }

    public String getUser() {
        return user;
    }

    public LogServiceModel setUser(String user) {
        this.user = user;
        return this;
    }

    public String getAlbum() {
        return album;
    }

    public LogServiceModel setAlbum(String album) {
        this.album = album;
        return this;
    }

    public String getAction() {
        return action;
    }

    public LogServiceModel setAction(String action) {
        this.action = action;
        return this;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public LogServiceModel setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public Long getId() {
        return id;
    }

    public LogServiceModel setId(Long id) {
        this.id = id;
        return this;
    }
}
