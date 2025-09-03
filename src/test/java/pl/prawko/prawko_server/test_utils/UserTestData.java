package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTestData {

    private static final Role ROLE = new Role().setName("USER");

    public static final User TESTER = new User()
            .setId(44L)
            .setFirstName("Peregrin")
            .setLastName("Tuk")
            .setUserName("pippin")
            .setEmail("pippin@shire.me")
            .setPassword("lembasy")
            .setRoles(List.of(ROLE))
            .setEnabled(true)
            .setCreated(LocalDateTime.now())
            .setUpdated(LocalDateTime.now())
            .setExams(new ArrayList<>());

    public static final RegisterDto VALID_REGISTER_DTO = new RegisterDto(
            "Peregrin",
            "Tuk",
            "pippin",
            "pippin@shire.me",
            "lembasy");

    public static final RegisterDto INVALID_REGISTER_DTO = new RegisterDto(
            "Supercalifragilisticexpialidocious",
            " ",
            "OK",
            "notValidMail@mail@mail",
            "lembas"
    );

}
