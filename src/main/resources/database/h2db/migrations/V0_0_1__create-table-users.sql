CREATE TABLE `tb_users` (
    `user_id` SERIAL PRIMARY KEY,
    --`user_uuid` uuid DEFAULT gen_random_uuid() UNIQUE,
     `user_uuid` uuid DEFAULT random_uuid() UNIQUE,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL UNIQUE,
    `password` VARCHAR(50) NOT NULL,
    `role` VARCHAR(30) DEFAULT 'USER',
    `created_at` TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `role_exists` CHECK( role IN ('USER', 'EMPLOYEE', 'ADMIN'))
);