package pl.prawko.prawko_server.service.implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.prawko.prawko_server.model.Language;
import pl.prawko.prawko_server.repository.LanguageRepository;
import pl.prawko.prawko_server.service.ILanguage;

import java.util.List;

@Service
@AllArgsConstructor
public class LanguageService implements ILanguage {

    private final LanguageRepository repository;

    @Override
    public List<Language> findAll() {
        return repository.findAll();
    }

}
