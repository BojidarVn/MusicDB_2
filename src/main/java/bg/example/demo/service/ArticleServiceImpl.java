package bg.example.demo.service;

import bg.example.demo.model.view.ArticleViewModel;
import bg.example.demo.repository.ArticleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ModelMapper modelMapper, ArticleRepository articleRepository) {
        this.modelMapper = modelMapper;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<ArticleViewModel> findAllArticle() {
        return articleRepository
                .findAll()
                .stream()
                .map(ae -> {
                    ArticleViewModel avm = modelMapper.map(ae, ArticleViewModel.class);
                    avm.setAuthor(ae.getUserEntity().getUsername());
                    return avm;
                })
                .collect(Collectors.toList());
    }

}
