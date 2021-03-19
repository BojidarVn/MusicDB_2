package bg.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MusicDb2Application {

    public static void main(String[] args) {
        SpringApplication.run(MusicDb2Application.class, args);
    }

}
