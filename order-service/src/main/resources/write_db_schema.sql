CREATE TABLE "order"
(
    id          BIGINT NOT NULL,
    customer_id int8   NOT NULL,
    order_date  timestamp,
    product     VARCHAR(255),
    status      VARCHAR(10),
    CONSTRAINT pk_order PRIMARY KEY (id)
);


CREATE SEQUENCE order_id_seq START 101;