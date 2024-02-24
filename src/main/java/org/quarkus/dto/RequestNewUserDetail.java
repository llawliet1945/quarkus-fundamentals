package org.quarkus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RequestNewUserDetail {
    private String userDetailFirstName;
    private String userDetailLastName;
    private String userDetailPhone;
    private String userDetailAddress;
    private String userDetailPhotos;
}
