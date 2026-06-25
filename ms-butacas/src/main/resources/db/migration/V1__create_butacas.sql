CREATE TABLE butaca (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    funcion_id   INT         NOT NULL,
    sala_id      INT         NOT NULL,
    numero       INT         NOT NULL,
    status       VARCHAR(10) NOT NULL DEFAULT 'FREE',
    locked_until DATETIME    NULL
);
