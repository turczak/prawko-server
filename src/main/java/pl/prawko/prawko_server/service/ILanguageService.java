package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Language;

import java.util.List;

public interface ILanguageService {

    List<Language> findAll();

}
