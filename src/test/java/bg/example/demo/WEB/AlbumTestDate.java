package bg.example.demo.WEB;

import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.model.entity.ArtistEntity;
import bg.example.demo.model.entity.UserEntity;
import bg.example.demo.model.entity.enums.Genre;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.ArtistRepository;
import bg.example.demo.repository.LogRepository;
import bg.example.demo.repository.UserRepository;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

class AlbumTestDate {

    private long testAlbumId;

    private UserRepository userRepository;

    private AlbumRepository albumRepository;

    private ArtistRepository artistRepository;

    private LogRepository logRepository;

    public AlbumTestDate(UserRepository userRepository, AlbumRepository albumRepository,
                         ArtistRepository artistRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.logRepository = logRepository;
    }

    public void init() {

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("METALLICA");
        artistEntity.setCareerInformation("Some info about metallica");

        artistEntity= artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullname("petar petrov");
        userEntity = userRepository.save(userEntity);

        AlbumEntity justiceForAll = new AlbumEntity();
        justiceForAll
                .setName("JUSTIS FOR ALL")
                .setImageUrl("https://cdn.pocket-lint.com/r/s/970x/assets/images/142413-apps-feature-art-and-science-collide-the-best-in-modern-space-art-image1-iha6vzu3wk-jpg.webp?v1")
                .setVideoUrl("HbokBTEBEOE")
                .setDescription("Some description")
                .setCopies(11)
                .setPrice(BigDecimal.TEN)
                .setReleaseDate(LocalDate.of(1988, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant())
                .setGenre(Genre.METAL)
                .setArtistEntity(artistEntity)
                .setUserEntity(userEntity);

        justiceForAll = albumRepository.save(justiceForAll);

        AlbumEntity masterOfPuppets = new AlbumEntity();
        masterOfPuppets
                .setName("MASTER OF POPPETS")
                .setImageUrl("https://cdn.pocket-lint.com/r/s/970x/assets/images/142413-apps-feature-art-and-science-collide-the-best-in-modern-space-art-image1-iha6vzu3wk-jpg.webp?v1")
                .setVideoUrl("HbokBTEBEOE")
                .setDescription("Some description")
                .setCopies(111)
                .setPrice(BigDecimal.TEN)
                .setReleaseDate(LocalDate.of(1988, 3, 3).atStartOfDay(ZoneId.systemDefault()).toInstant())
                .setGenre(Genre.METAL)
                .setArtistEntity(artistEntity)
                .setUserEntity(userEntity);

        masterOfPuppets =albumRepository.save(masterOfPuppets);



        testAlbumId= justiceForAll.getId();


    }
    void cleanUp() {
         logRepository.deleteAll();
        albumRepository.deleteAll();
        artistRepository.deleteAll();
        userRepository.deleteAll();

    }

    public long getTestAlbumId() {
        return testAlbumId;
    }
}
