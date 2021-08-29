package com.app.web.repository;

import com.app.web.model.Role;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static com.web.codegen.jooq.Tables.ROLE;

@Repository
public class RoleInfoRepository {

    @Autowired
    private DSLContext jooqDSL;

    public void addRole(Role role){
        jooqDSL.insertInto(ROLE)
                .set(ROLE.NAME, role.getName())
                .execute();
    }

    public Role getRoleByName(String name){
        return jooqDSL.selectFrom(ROLE).where(ROLE.NAME.eq(name)).fetchOne(this::mapRole);
    }

    public Role mapRole(Record record){
        Role role = new Role();
        role.setId(record.get(ROLE.ID));
        role.setName(record.get(ROLE.NAME));

        return role;
    }
}
