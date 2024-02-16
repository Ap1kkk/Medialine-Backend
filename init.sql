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
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    role            VARCHAR(255) NOT NULL,
    status          VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE medialine_products (
     id              BIGSERIAL,
     title           VARCHAR(255) NOT NULL,
     category        VARCHAR(255) NOT NULL,
     description     TEXT NOT NULL,
     specials        TEXT[],
     packaging       TEXT[],
     image_path      VARCHAR(255) NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO medialine_users(email, password, role, status)
        VALUES ('admin@mail.ru', '$2a$12$gChF6b54pJMIwy6iFcI21OXrxToEgifHuO5OMkT785YJ3SqUYQT22', 'ADMIN', 'ACTIVE');
