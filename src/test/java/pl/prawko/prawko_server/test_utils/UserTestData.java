package pl.prawko.prawko_server.test_utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.prawko.prawko_server.dto.LoginDto;
import pl.prawko.prawko_server.dto.RegisterDto;
import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.model.User;

import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTestData {

    private static final Role ROLE_USER = new Role().setName("USER");

    public static final User TESTER = new User()
            .setId(44L)
            .setFirstName("Peregrin")
            .setLastName("Tuk")
            .setUserName("pippin")
            .setEmail("pippin@shire.me")
            .setPassword(new BCryptPasswordEncoder().encode("lembasy"))
            .setRoles(List.of(ROLE_USER))
            .setEnabled(true)
            .setCreated(LocalDateTime.now())
            .setUpdated(LocalDateTime.now());

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

    public static final LoginDto VALID_LOGIN_REQUEST = new LoginDto("pippin", "lembasy");

    public static final LoginDto INVALID_LOGIN_REQUEST = new LoginDto("wrongUser", "wrongPassword");

}
