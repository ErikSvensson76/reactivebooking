package com.example.reactivebooking;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class TestConstants {
    public static final Resource TEST_DB = new FileSystemResource("mysql/testdb.sql");
    public static final Resource SEED_APP_ROLES = new FileSystemResource("mysql/seed-approles.sql");
}
