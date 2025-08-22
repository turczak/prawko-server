package pl.prawko.prawko_server.test_utils;

import pl.prawko.prawko_server.model.Role;
import pl.prawko.prawko_server.model.User;

import java.util.List;

public class UserTestData {

    private static final Role role = new Role().setName("USER");

    public static final User TESTER = new User()
            .setFirstName("Peregrin")
            .setLastName("Tuk")
            .setUserName("pippin")
            .setEmail("pippin@shire.me")
            .setPassword("lembas")
            .setRoles(List.of(role))
            .setEnabled(true);

}
