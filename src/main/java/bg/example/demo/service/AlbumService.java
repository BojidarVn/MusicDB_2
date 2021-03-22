package bg.example.demo.service;

import bg.example.demo.model.service.AlbumServiceModel;
import bg.example.demo.model.view.AlbumViewModel;

public interface AlbumService {
    void createAlbum(AlbumServiceModel serviceModel);

    AlbumViewModel findById(Long id);
}
