package com.web.thymeleaf.persistance;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class Users {
    protected ArrayList<HashMap<String, String>> users;

    public Users() {
        HashMap<String, String> admin = new HashMap<>();
        admin.put("user_id", "usr001");
        admin.put("user_name", "Eduardo");
        admin.put("user_role", "Admin");
        admin.put("user_age", "32");
        admin.put("user_gender", "Hombre");

        HashMap<String, String> client = new HashMap<>();
        client.put("user_id", "usr002");
        client.put("user_name", "Felipe");
        client.put("user_role", "User");
        client.put("user_age", "28");
        client.put("user_gender", "Hombre");

        users = new ArrayList<>();
        users.add(admin);
        users.add(client);
    }

    public ArrayList<HashMap<String, String>> getUsers() {
        return users;
    }

    public HashMap<String, String> getUser(String user_id) {
        return users.stream()
                .filter( map -> user_id.equals(map.get("user_id")))
                .toList()
                .get(0);
    }
}
