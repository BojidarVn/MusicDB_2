package bg.example.demo.service.impl;

import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.model.entity.UserEntity;
import bg.example.demo.model.service.AlbumServiceModel;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.UserRepository;
import bg.example.demo.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AlbumServiceImpl(AlbumRepository albumRepository,
                            UserRepository userRepository,
                            ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createAlbum(AlbumServiceModel serviceModel) {
        AlbumEntity albumEntity = modelMapper.map(serviceModel, AlbumEntity.class);
        UserEntity creator = userRepository.
                findByUsername(serviceModel.getUserName()).
                orElseThrow(() -> new IllegalArgumentException("Creator " + serviceModel.getUserName() + " could not be found"));
        albumEntity.setUserEntity(creator);

        albumRepository.save(albumEntity);
    }
}
