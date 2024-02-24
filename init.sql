CREATE TABLE medialine_news (
    id                  BIGSERIAL,
    title               VARCHAR(255) NOT NULL,
    time                timestamp,
    text                TEXT NOT NULL,
    image_path          VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medialine_users (
    id              BIGSERIAL,
    email           VARCHAR(255) NOT NULL UNIQUE,
    password        VARCHAR(255) NOT NULL,
    role            VARCHAR(255) NOT NULL,
    status          VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE INDEX idx_user_email ON medialine_users(email);

CREATE TABLE medialine_categories (
    id              BIGSERIAL,
    name            VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medialine_subcategories (
    id              BIGSERIAL,
    category_id     BIGINT NOT NULL,
    name            VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES medialine_categories(id)
);

CREATE INDEX idx_category_id ON medialine_subcategories(category_id);

CREATE TABLE medialine_products (
    id              BIGSERIAL,
    title           VARCHAR(255) NOT NULL,
    category_id     BIGINT NOT NULL,
    subcategory_id  BIGINT,
    description     TEXT NOT NULL,
    specials        TEXT[],
    packaging       TEXT[],
    image_path      VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES medialine_categories(id),
    FOREIGN KEY (subcategory_id) REFERENCES medialine_subcategories(id)
);

CREATE INDEX idx_product_category_id ON medialine_products(category_id);
CREATE INDEX idx_product_subcategory_id ON medialine_products(subcategory_id);

INSERT INTO medialine_users(email, password, role, status)
        VALUES ('admin@mail.ru', '$2a$12$gChF6b54pJMIwy6iFcI21OXrxToEgifHuO5OMkT785YJ3SqUYQT22', 'SUPER_ADMIN', 'ACTIVE');

INSERT INTO medialine_categories(name)
        VALUES ('Без категории')