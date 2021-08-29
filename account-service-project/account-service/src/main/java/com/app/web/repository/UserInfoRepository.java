package com.app.web.repository;

import com.app.web.model.Role;
import com.app.web.model.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.web.codegen.jooq.Tables.*;

@Repository
public class UserInfoRepository {

    @Autowired
    private DSLContext jooqDSL;

    public void addUser(User user){
        jooqDSL.insertInto(USER)
                .set(USER.NAME, user.getName())
                .set(USER.USERNAME, user.getUsername())
                .set(USER.PASSWORD, user.getPassword())
                .execute();

        user.getRoles().forEach(role -> {
            addRoleToUser(user.getUsername(), role.getName());
        });
    }

    public void addRoleToUser(String username, String roleName){

        jooqDSL.insertInto(USER_ROLES)
                .set(USER_ROLES.USERNAME, username)
                .set(USER_ROLES.ROLE_NAME, roleName)
                .execute();
    }

    public List<User> getAllUsers(){
        return jooqDSL.selectFrom(USER).fetch(this::mapUser);
    }

    public User getUserByName(String username){
        return jooqDSL.selectFrom(USER).where(USER.USERNAME.eq(username)).fetchOne(this::mapUser);
    }

    public User mapUser(Record record){
        User user = new User();
        user.setId(record.get(USER.ID));
        user.setUsername(record.get(USER.USERNAME));
        user.setName(record.get(USER.NAME));
        user.setPassword(record.get(USER.PASSWORD));

        Set<Role> roles = jooqDSL.select().from(USER_ROLES)
                        .join(ROLE).on(USER_ROLES.ROLE_NAME.eq(ROLE.NAME))
                        .join(USER).on(USER_ROLES.USERNAME.eq(USER.USERNAME)).stream().map(this::mapRole)
                        .collect(Collectors.toSet());

        user.setRoles(roles);
        return user;
    }

    public Role mapRole(Record record){
        Role role = new Role();
        role.setId(record.get(ROLE.ID));
        role.setName(record.get(ROLE.NAME));

        return role;
    }
}
