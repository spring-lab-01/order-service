CREATE TABLE order_read.order
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    customer_id int8  NOT NULL,
    order_date  timestamp,
    product     VARCHAR(255),
    status      VARCHAR(10)
);