package bg.example.demo.WEB;

import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.repository.AlbumRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/albums")
@RestController
public class AlbumRestController {

    private final AlbumRepository albumRepository;
    private final ModelMapper modelMapper;

    public AlbumRestController(AlbumRepository albumRepository,
                               ModelMapper modelMapper) {
        this.albumRepository = albumRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/api")
    public ResponseEntity<List<AlbumEntity>> findAll() {

        return ResponseEntity
                .ok()
                .body(albumRepository.findAll());
    }
}