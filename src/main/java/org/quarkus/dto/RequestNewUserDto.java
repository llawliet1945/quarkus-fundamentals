package org.quarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestNewUserDto {
    private RequestNewUser user;
    private RequestNewUserDetail userDetail;
}
