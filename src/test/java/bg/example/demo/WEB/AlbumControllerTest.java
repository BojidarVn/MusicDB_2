package bg.example.demo.WEB;


import bg.example.demo.model.entity.enums.Genre;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.ArtistRepository;
import bg.example.demo.repository.LogRepository;
import bg.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
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

    @Autowired
    private LogRepository logRepository;

    private AlbumTestDate testDate;

    @BeforeEach
    public void setup() {

        testDate=new AlbumTestDate(userRepository, albumRepository, artistRepository, logRepository);

        testDate.init();
        testAlbumId=testDate.getTestAlbumId();
    }

    @AfterEach
    public void tearDown() {
        testDate.cleanUp();
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


    }




}
