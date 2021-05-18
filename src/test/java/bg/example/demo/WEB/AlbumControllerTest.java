package bg.example.demo.WEB;


import bg.example.demo.model.entity.AlbumEntity;
import bg.example.demo.model.entity.ArtistEntity;
import bg.example.demo.model.entity.UserEntity;
import bg.example.demo.model.entity.enums.Genre;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.ArtistRepository;
import bg.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumControllerTest {

    private static final String ALBUM_CONTROLLER_PREFIX = "/albums";

    private long testAlbumId;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @BeforeEach
    public void setup() {
        this.init();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER","ADMIN"})
    void testShouldReturnValidStatusViewNameAndModel() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(
              ALBUM_CONTROLLER_PREFIX + "/details/{id}"  ,testAlbumId
        ))
                .andExpect(status().isOk())
                .andExpect(view().name("details"))
                .andExpect(model().attributeExists("album"));
    }

    @Test
    @WithMockUser(value = "pesho",roles = {"USER","ADMIN"})
    void addAlbum() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(ALBUM_CONTROLLER_PREFIX + "/add")
        .param("name","test album")
        .param("genre",Genre.METAL.name())
        .param("imageUrl","http://example.com/image.png")
        .param("VideoUrl","HbokBTEBEOE")
        .param("description","Description test")
        .param("copies","123333")
        .param("price","10")
        .param("releaseDate","2000-01-01")
        .param("artist","METALLICA")
                .with(csrf()))
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(2,albumRepository.count());

        //todo may verify the created album properties
    }

    private void init() {

        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("METALLICA");
        artistEntity.setCareerInformation("Some info about metallica");

       artistEntity= artistRepository.save(artistEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("pesho").setPassword("xyz").setFullname("petar petrov");
        userEntity = userRepository.save(userEntity);

        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity
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

        albumEntity=albumRepository.save(albumEntity);

        testAlbumId=albumEntity.getId();



    }


}
