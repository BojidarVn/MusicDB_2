package bg.example.demo.service.impl;

import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.model.entity.ArtistEntity;
import bg.example.demo.model.entity.UserEntity;
import bg.example.demo.model.service.AlbumServiceModel;
import bg.example.demo.model.view.AlbumViewModel;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.UserRepository;
import bg.example.demo.service.AlbumService;
import bg.example.demo.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ArtistService artistService;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.artistService = artistService;
    }

    @Override
    public void createAlbum(AlbumServiceModel serviceModel) {
        AlbumEntity albumEntity = modelMapper.map(serviceModel, AlbumEntity.class);
        UserEntity creator = userRepository.
                findByUsername(serviceModel.getUser()).
                orElseThrow(() -> new IllegalArgumentException("Creator " + serviceModel.getUser() + " could not be found"));

        albumEntity.setUserEntity(creator);

        ArtistEntity artistEntity=artistService
                .findByName(serviceModel.getArtist());

        albumEntity.setArtistEntity(artistEntity);

        albumRepository.save(albumEntity);
    }

    @Override
    public AlbumViewModel findById(Long id) {


        return albumRepository.findById(id)
                .map(albumEntity -> {
                    AlbumViewModel albumViewModel=modelMapper
                            .map(albumEntity,AlbumViewModel.class);
                    albumViewModel.setArtist(albumEntity.getArtistEntity().getName());
                    return albumViewModel;
                }).orElseThrow(IllegalArgumentException::new);

    }

    @Override
    public AlbumEntity findEntityById(Long albumId) {


        return albumRepository.findById(albumId)
                .orElseThrow(IllegalArgumentException :: new);
    }
}
