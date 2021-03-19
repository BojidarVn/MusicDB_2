package bg.example.demo.WEB;

import bg.example.demo.model.binding.AlbumAddBindingModel;
import bg.example.demo.model.service.AlbumServiceModel;
import bg.example.demo.service.AlbumService;
import bg.example.demo.service.ArtistService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/albums")
public class AlbumController {

    private final ModelMapper modelMapper;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public AlbumController(ModelMapper modelMapper,
                           AlbumService albumService,
                           ArtistService artistService) {
        this.modelMapper = modelMapper;
        this.albumService = albumService;
        this.artistService = artistService;
    }

    @ModelAttribute("albumAddBindingModel")
    public AlbumAddBindingModel createBindingModel() {
        return new AlbumAddBindingModel();
    }

    @GetMapping("/add")
    public Model addAlbum(Model model) {

        model.addAttribute("artists", artistService.findAllArtists());

        return model;
    }

    @PostMapping("/add")
    public String addAlbum(AlbumAddBindingModel bindingModel,
                           @AuthenticationPrincipal UserDetails principal) {
        //TODO: add validation and error handling.
        AlbumServiceModel albumServiceModel = modelMapper.map(
                bindingModel,
                AlbumServiceModel.class);

        albumServiceModel.setUserName(principal.getUsername());

        albumService.createAlbum(albumServiceModel);

        if (albumService.equals(null)) {

        }

        return "redirect:/home";
    }

}