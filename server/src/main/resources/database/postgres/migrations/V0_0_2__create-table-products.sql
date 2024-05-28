CREATE TABLE tb_products (
    product_id SERIAL PRIMARY KEY,
    code VARCHAR(50) UNIQUE NOT NULL,
    name VARCHAR(50) NOT NULL,
    price NUMERIC(16,4) NOT NULL, -- 000.000.000.000,0000
    description VARCHAR(500),
    weight DOUBLE PRECISION,
    fk_user_uuid uuid,
    supplier VARCHAR(100),
    CONSTRAINT fk_user FOREIGN KEY (fk_user_uuid) REFERENCES tb_users(user_uuid),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);