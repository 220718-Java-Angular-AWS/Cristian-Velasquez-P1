CREATE TABLE users (
	user_id SERIAL,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL,
	email VARCHAR(200) NOT NULL,
	"password" VARCHAR(200) NOT NULL,
	user_type_id INT NOT NULL,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pk PRIMARY KEY (user_id),
	CONSTRAINT users_user_type_fk FOREIGN KEY (user_type_id) REFERENCES public.user_type(user_type_id)
);

CREATE TABLE user_type (
	user_type_id SERIAL,
	user_type_title VARCHAR(200) NOT NULL,
	CONSTRAINT user_type_pk PRIMARY KEY (user_type_id),
	CONSTRAINT user_type_user_type_title_key UNIQUE (user_type_title)
);

CREATE TABLE public.reimbursement (
	reimbursement_id SERIAL,
	reimbursement_title VARCHAR(200) NOT NULL,
	reimbursement_description VARCHAR NOT NULL,
	reimbursement_status_id INT NOT NULL,
	user_id INT NOT NULL,
	CONSTRAINT reimbursement_pk PRIMARY KEY (reimbursement_id),
	CONSTRAINT reimbursement_reimbursement_status_ft FOREIGN KEY (reimbursement_status_id) REFERENCES public.reimbursement_status(reimbursement_status_id),
	CONSTRAINT reimbursement_users_fk FOREIGN KEY (user_id) REFERENCES public.users(user_id)
);

CREATE TABLE public.reimbursement_status (
	reimbursement_status_id SERIAL,
	reimbursement_status_name VARCHAR(200) NOT NULL,
	CONSTRAINT reimbursement_status_pk PRIMARY KEY (reimbursement_status_id)
);