package bg.example.demo.service;

import bg.example.demo.model.entity.ArtistEntity;
import bg.example.demo.model.view.ArtistViewModel;

import java.util.List;

public interface ArtistService {

    //TODO: accepted practise?

    List<String> findAllArtists();

    void seedArtists();

    ArtistEntity findByName(String artist);
}
