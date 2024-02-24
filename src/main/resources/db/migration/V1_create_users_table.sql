CREATE TABLE fundamentals.usrs (
    usrs_id serial NOT NULL,
    usrs_uuid varchar(100) DEFAULT gen_random_uuid(),
    usrs_email varchar(100),
    usrs_usrnme varchar(64),
    usrs_pass varchar(100),
    usrs_sttus int4 DEFAULT 0,
    created_by int4,
    created_date timestamptz DEFAULT NOW(),
    updated_by int4,
    updated_date timestamptz,
    PRIMARY KEY (usrs_id)
);