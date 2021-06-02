package bg.example.demo.WEB;

import bg.example.demo.model.entity.enums.Genre;
import bg.example.demo.repository.AlbumRepository;
import bg.example.demo.repository.ArtistRepository;
import bg.example.demo.repository.LogRepository;
import bg.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class AlbumRestControllerTest {

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
    public void setUp() {

        testDate=new AlbumTestDate(userRepository, albumRepository, artistRepository, logRepository);

        testDate.init();
    }

    @AfterEach
    public void tearDown() {
        testDate.cleanUp();
    }

    @Test
    @WithMockUser(value = "pesho", roles = {"USER", "ADMIN"})
    public void testFetchAlbums() throws Exception {
        mockMvc.perform(
                get("/albums/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].name").value("JUSTIS FOR ALL"))
                .andExpect(jsonPath("[0].copies").value(11))
                .andExpect(jsonPath("[0].genre").value(Genre.METAL.name()))
                .andExpect(jsonPath("[1].name").value("MASTER OF POPPETS"))
                .andExpect(jsonPath("[1].copies").value(111))
                .andExpect(jsonPath("[1].genre").value(Genre.METAL.name()));
    }

}
