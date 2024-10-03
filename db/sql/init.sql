CREATE TABLE t_lecture
(
    lecture_id               VARCHAR(10) NOT NULL,
    lecture_name             VARCHAR(10),
    lecture_description      VARCHAR(255),
    created_at               TIMESTAMP,
    updated_at               TIMESTAMP,
    begin_date               TIMESTAMP,
    end_date                 TIMESTAMP,
    current_registration_cnt TINYINT,
    deleted_at               TIMESTAMP,
    CONSTRAINT pk_t_lecture PRIMARY KEY (lecture_id)
);

CREATE TABLE t_lecture_registration
(
    lecture_id VARCHAR(10) NOT NULL,
    user_id    VARCHAR(10) NOT NULL,
    created_at   TIMESTAMP,
    CONSTRAINT pk_t_lecture_registration PRIMARY KEY (lecture_id, user_id)
);

CREATE TABLE t_lecture_registration_history
(
    log_idx      BIGINT NOT NULL,
    lecture_id   VARCHAR(10),
    user_id      VARCHAR(10),
    history_type VARCHAR(10),
    created_at   TIMESTAMP,
    CONSTRAINT pk_t_lecture_registration_history PRIMARY KEY (log_idx)
);

CREATE TABLE t_student
(
    user_id    VARCHAR(10) NOT NULL,
    username   VARCHAR(10),
    created_at TIMESTAMP,
    CONSTRAINT pk_t_student PRIMARY KEY (user_id)
);

CREATE TABLE t_teacher
(
    teacher_id VARCHAR(10) NOT NULL,
    name       VARCHAR(10),
    created_at TIMESTAMP,
    CONSTRAINT pk_t_teacher PRIMARY KEY (teacher_id)
);

CREATE TABLE t_teacher_lecture
(
    teacher_id VARCHAR(10) NOT NULL,
    lecture_id VARCHAR(10) NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT pk_t_teacher_lecture PRIMARY KEY (teacher_id, lecture_id)
);