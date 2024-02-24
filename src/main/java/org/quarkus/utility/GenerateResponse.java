package org.quarkus.utility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.quarkus.dto.ResponseApi;

public class GenerateResponse {

    public Response success(String message, Object data) throws JsonProcessingException {
        return Response.ok(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).disable(SerializationFeature.FAIL_ON_EMPTY_BEANS).writeValueAsString(new ResponseApi(message, data, 200))).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public Response created(String message, Object data) throws JsonProcessingException {
        return Response.status(Response.Status.CREATED).entity(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).writeValueAsString(new ResponseApi(message, data, 201))).type(MediaType.APPLICATION_JSON).build();
    }

    public Response error(String message, Object data) throws JsonProcessingException {
        return Response.serverError().entity(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).writeValueAsString(new ResponseApi(message, data, 500))).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public Response notFound(String message, Object data) throws JsonProcessingException {
        return Response.status(Response.Status.NOT_FOUND).entity(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).writeValueAsString(new ResponseApi(message, data, 404))).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public Response unauthorized(String message, Object data) throws JsonProcessingException {
        return Response.status(Response.Status.UNAUTHORIZED).entity(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).writeValueAsString(new ResponseApi(message, data, 401))).type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    public Response badRequest(String message, Object data) throws JsonProcessingException {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ObjectMapper().setSerializationInclusion(JsonInclude.Include.ALWAYS).writeValueAsString(new ResponseApi(message, data, 400))).type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
