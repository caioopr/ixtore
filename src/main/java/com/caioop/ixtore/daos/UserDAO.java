package com.caioop.ixtore.daos;

import java.util.UUID;

public interface UserDAO {
    byte[] getUuid();
    String getName();
    String getEmail();
    String getRole();
}
