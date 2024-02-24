package org.quarkus;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.quarkus.model.UserDetail;

@ApplicationScoped
public class UserDetailRepository implements PanacheRepository<UserDetail> {

}

