package com.ubom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchFactor {
    private Integer id;
    private String username;
    private String nickname;
    private String email;
    private boolean userType;
}
