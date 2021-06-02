package bg.example.demo.service.impl;

import bg.example.demo.service.ImageShuffler;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ImageShufflerImpl implements ImageShuffler {

    @Override
    public void shuffler(List<String> images) {
        Collections.shuffle(images);
    }
}
