package pl.prawko.prawko_server.service;

import pl.prawko.prawko_server.model.Language;

import java.util.List;

/**
 * Service interface for retrieving {@link Language} entities.
 */
public interface ILanguageService {

    /**
     * Returning list of all existing {@link Language} entities.
     *
     * @return list of {@link Language}
     */
    List<Language> findAll();

}
