package bg.example.demo.repository;

import bg.example.demo.model.entity.ArtistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<ArtistEntity, Long> {

    @Query("SELECT a.name FROM ArtistEntity AS a")
     List<String> findALLArtistNames();


    Optional<ArtistEntity> findByName(String name);

}
