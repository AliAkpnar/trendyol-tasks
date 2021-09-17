CREATE database `trendyol`;

CREATE
    TABLE url_request
    (
        id bigint NOT NULL AUTO_INCREMENT,
        request VARCHAR(500) COLLATE latin1_swedish_ci NOT NULL,
        response VARCHAR(500) COLLATE latin1_swedish_ci NOT NULL,
        url_type VARCHAR(10) COLLATE latin1_swedish_ci NOT NULL,
        link_type VARCHAR(10) COLLATE latin1_swedish_ci NOT NULL,
        PRIMARY KEY (id)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8





