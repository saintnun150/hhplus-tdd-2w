CREATE TABLE t_lecture (
   lecture_id VARCHAR(255) NOT NULL PRIMARY KEY,
   lecture_name VARCHAR(255),
   lecture_description VARCHAR(1000),
   created_at TIMESTAMP,
   updated_at TIMESTAMP,
   deleted_at TIMESTAMP,
   applied_at TIMESTAMP,
   current_registration_cnt INT
);

CREATE TABLE t_lecture_registration (
    lecture_registration_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    lecture_id VARCHAR(255) NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    created_at TIMESTAMP,
    CONSTRAINT UC_lecture_user UNIQUE (lecture_id, user_id)
);


CREATE TABLE t_student (
   user_id VARCHAR(255) PRIMARY KEY,
   username VARCHAR(255),
   created_at TIMESTAMP
);

CREATE TABLE t_teacher (
   teacher_id VARCHAR(255) PRIMARY KEY,
   username VARCHAR(255),
   created_at TIMESTAMP
);


CREATE TABLE t_lecture_assignment (
  assignment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  lecture_id VARCHAR(255) NOT NULL,
  teacher_id VARCHAR(255) NOT NULL,
  created_at TIMESTAMP,
  CONSTRAINT UC_lecture_teacher UNIQUE (lecture_id, teacher_id)
);


