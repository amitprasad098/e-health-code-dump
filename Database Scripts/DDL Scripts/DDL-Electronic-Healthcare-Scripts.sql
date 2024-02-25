create database electronic_healthcare;

use electronic_healthcare;

create table user_type
(
    user_type_id int NOT NULL PRIMARY KEY,
    user_role varchar(20) NOT NULL
);

create table practice
(
    practice_id int NOT NULL PRIMARY KEY,
    practice_name varchar(100) NOT NULL,
    practice_desc text NOT NULL,
    practice_addr varchar(100) NOT NULL
);

create table user
(
    user_id int NOT NULL PRIMARY KEY,
    user_name varchar(50) NOT NULL,
    user_email varchar(50) NOT NULL,
    user_password varchar(50) NOT NULL,
    user_type_id int NOT NULL,
    practice_id int NOT NULL,
    CONSTRAINT `user_fk1` FOREIGN KEY (`user_type_id`) REFERENCES user_type (`user_type_id`),
    CONSTRAINT `user_fk2` FOREIGN KEY (`practice_id`) REFERENCES practice (`practice_id`)
);


create table user_practice_registration
(
    user_practice_registration_id int NOT NULL PRIMARY KEY,
    user_id int NOT NULL,
    practice_id int NOT NULL,
    registration_status varchar(20) NOT NULL,
    user_date_of_birth date NOT NULL,
    user_addr varchar(100) NOT NULL,
    CONSTRAINT `user_practice_registration_fk1` FOREIGN KEY (`user_id`) REFERENCES user (`user_id`),
    CONSTRAINT `user_practice_registration_fk2` FOREIGN KEY (`practice_id`) REFERENCES practice (`practice_id`)
);

create table appointment_booking
(
    appointment_booking_id int NOT NULL PRIMARY KEY,
    appointment_type_id int NOT NULL,
    user_practice_registration_id int NOT NULL,
    appointment_desc varchar(100) NOT NULL,
    appointment_date date NOT NULL,
    appointment_status varchar(20) NOT NULL,
    practitioner_feedback text,
    CONSTRAINT `appointment_booking_fk1` FOREIGN KEY (`appointment_type_id`) REFERENCES appointment_type (`appointment_type_id`),
    CONSTRAINT `appointment_booking_fk2` FOREIGN KEY (`user_practice_registration_id`) REFERENCES user_practice_registration (`user_practice_registration_id`)
);

create table patient_prescription
(
    patient_prescription_id int NOT NULL PRIMARY KEY,
    appointment_booking_id int NOT NULL,
    prescription_details text NOT NULL
    prescription_date date NOT NULL,
    CONSTRAINT `patient_prescription_fk1` FOREIGN KEY (`appointment_booking_id`) REFERENCES appointment_booking (`appointment_booking_id`)
);

create table medical_history
(
    medical_history_id int NOT NULL PRIMARY KEY,
    appointment_booking_id int NOT NULL,
    medical_history_details text NOT NULL,
    medical_history_date date NOT NULL,
    CONSTRAINT `medical_history_fk1` FOREIGN KEY (`appointment_booking_id`) REFERENCES appointment_booking (`appointment_booking_id`)
);