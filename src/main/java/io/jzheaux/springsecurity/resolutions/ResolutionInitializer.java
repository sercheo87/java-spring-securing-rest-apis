package io.jzheaux.springsecurity.resolutions;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ResolutionInitializer implements SmartInitializingSingleton {
    private final ResolutionRepository resolutions;
    private final UserRepository users;

    public ResolutionInitializer(ResolutionRepository resolutions,
                                 UserRepository users) {
        this.resolutions = resolutions;
        this.users = users;
    }

    @Override
    public void afterSingletonsInstantiated() {
        this.resolutions.save(new Resolution("Read War and Peace", "user"));
        this.resolutions.save(new Resolution("Free Solo the Eiffel Tower", "user"));
        this.resolutions.save(new Resolution("Hang Christmas Lights", "user"));

        User user = new User("user", "{bcrypt}$2a$10$hSY16mw613UlB3wv8xjaBekZKukxh7e5BcVyd83STJLhQgamjtZu2");
        user.grantAuthority("resolution:read");
        user.grantAuthority("resolution:write");
        this.users.save(user);

        User hasread = new User();
        hasread.setId(UUID.randomUUID());
        hasread.setUsername("hasread");
        hasread.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
        hasread.grantAuthority("resolution:read");
        this.users.save(hasread);

        User haswrite = new User();
        haswrite.setId(UUID.randomUUID());
        haswrite.setUsername("haswrite");
        haswrite.setPassword("{bcrypt}$2a$10$MywQEqdZFNIYnx.Ro/VQ0ulanQAl34B5xVjK2I/SDZNVGS5tHQ08W");
        haswrite.grantAuthority("resolution:write");
        this.users.save(haswrite);
    }
}
