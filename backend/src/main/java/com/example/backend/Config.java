package com.example.backend;

import com.example.backend.repo.*;
import com.example.backend.repo.impl.*;
import com.example.backend.services.ActivityService;
import com.example.backend.services.ArticleService;
import com.example.backend.services.DestinationService;
import com.example.backend.services.UserService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class Config extends ResourceConfig {

    public Config() {
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(UserRepository.class).to(IUserRepository.class).in(Singleton.class);
                this.bind(UserTypeRepository.class).to(IUserTypeRepository.class).in(Singleton.class);
                this.bind(DestinationRepository.class).to(IDestinationRepository.class).in(Singleton.class);
                this.bind(ArticleRepository.class).to(IArticleRepository.class).in(Singleton.class);
                this.bind(ActivityRepository.class).to(IActivityRepository.class).in(Singleton.class);
                this.bind(ActivityArticleRepo.class).to(IActivityArticleRepo.class).in(Singleton.class);
                this.bind(CommentRepository.class).to(ICommentRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(DestinationService.class);
                this.bindAsContract(ArticleService.class);
                this.bindAsContract(ActivityService.class);
            }
        };
        register(binder);

        packages("com.example.backend");
    }
}
