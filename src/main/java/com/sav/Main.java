package com.sav;

import com.sav.dto.GetUsersResponse;

public class Main {

    public static void main(String[] args) {

        JsonHttpFacade jsonHttp = new JsonHttpFacade();

        String uri = "https://mag-contacts-api.herokuapp.com/users";

        GetUsersResponse resp =  jsonHttp.get(uri, GetUsersResponse.class);

        System.out.println(resp.getStatus());
        for (GetUsersResponse.User user : resp.getUsers()) {
            System.out.println(user);
        }









//        UserResponse resp = jsonHttp.get("http://example.com/users",UserResponse.class);
//
//        CreateUserRequest req = new CreateUserRequest(
//                "examplelogin",
//                "examplepass"
//        );
//
//        StatusResponse resp2 = jsonHttp.post(
//                "http://example.com/register",
//                req,
//                StatusResponse.class
//        );
//



    }
}
