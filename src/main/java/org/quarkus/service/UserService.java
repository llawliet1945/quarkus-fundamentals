package org.quarkus.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.quarkus.UserDetailRepository;
import org.quarkus.dto.ListUserDto;
import org.quarkus.dto.RequestNewUserDto;
import org.quarkus.model.User;
import org.quarkus.model.UserDetail;
import org.quarkus.utility.GenerateResponse;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequiredArgsConstructor
@ApplicationScoped
public class UserService {

    Logger logger = Logger.getLogger(getClass().getName());

    private final ModelMapper mapper;

    private final EntityManager entityManager;

    private final UserDetailRepository userDetailRepository;

    public Response getAllDataPanache() throws JsonProcessingException {
        List<?> allUser = User.listAll();
        if (allUser.isEmpty()) return new GenerateResponse().notFound("Data not found", null);
        return new GenerateResponse().success("Get data success", allUser.stream().map(x -> mapper.map(x, ListUserDto.class)).toList());
    }

    public Response getUserByUsername(String username) throws JsonProcessingException {
        TypedQuery<User> query = entityManager.createNamedQuery("findUserByUserUsernameOrUserEmail", User.class);
        query.setParameter(1, username);
        query.setParameter(2, 0);
        List<User> users = query.getResultList();
        return users.isEmpty() ? new GenerateResponse().notFound("Data not found", null) : new GenerateResponse().success("Get data success", users.stream().map(x -> mapper.map(x, ListUserDto.class)).toList());
    }

    @Transactional
    public Response addNewUser(RequestNewUserDto request) throws JsonProcessingException {
        try {
            TypedQuery<User> validateUsername = entityManager.createNamedQuery("findUserByUserUsername", User.class);
            validateUsername.setParameter(1, request.getUser().getUserUsername());
            validateUsername.setParameter(2, 0);
            if (!validateUsername.getResultList().isEmpty()) return new GenerateResponse().badRequest("Username is already taken", null);

            TypedQuery<User> validateEmail = entityManager.createNamedQuery("findUserByUserEmail", User.class);
            validateEmail.setParameter(1, request.getUser().getUserEmail());
            validateEmail.setParameter(2, 0);
            if (!validateEmail.getResultList().isEmpty()) return new GenerateResponse().badRequest("Email is already taken", null);

            User user = mapper.map(request.getUser(), User.class);
            user.setUserUuid(UUID.randomUUID().toString());
            user.setCreatedDate(new Date());
            user.persist();

            UserDetail userDetail = mapper.map(request.getUserDetail(), UserDetail.class);
            userDetail.setUserDetailId(user.getUserId());
            userDetail.setUserDetailUuid(UUID.randomUUID().toString());
            userDetail.setCreatedBy(user.getUserId());
            userDetail.setCreatedDate(new Date());
            if (!entityManager.contains(userDetail)) {
                userDetail = entityManager.merge(userDetail);
            }
            userDetail.persistAndFlush();
            return new GenerateResponse().created("Save data success", null);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new GenerateResponse().error("Internal Server Error: " + e.getLocalizedMessage(), null);
        }
    }

}
