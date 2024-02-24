package org.quarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequestNewUser {
    private String userEmail;
    private String userUsername;
    private String userPass;
}
