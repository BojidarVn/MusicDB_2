package bg.example.demo.service;


import bg.example.demo.model.view.ArticleViewModel;

import java.util.List;

public interface ArticleService {

    List<ArticleViewModel> findAllArticle();
}
