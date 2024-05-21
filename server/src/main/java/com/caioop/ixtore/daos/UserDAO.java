package com.caioop.ixtore.daos;

import java.util.UUID;

public interface UserDAO {
    UUID getUuid();
    String getName();
    String getEmail();
    String getRole();
}
