CREATE TABLE IF NOT EXISTS employers (
  id IDENTITY PRIMARY KEY,
  company VARCHAR(255),
  email VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS seekers (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(255),
  email VARCHAR(255),
  skills VARCHAR(1024)
);

CREATE TABLE IF NOT EXISTS jobs (
  id IDENTITY PRIMARY KEY,
  employer_id BIGINT,
  title VARCHAR(255),
  location VARCHAR(255),
  type VARCHAR(100),
  salary INT,
  description CLOB,
  posted_on TIMESTAMP
);

CREATE TABLE IF NOT EXISTS applications (
  id IDENTITY PRIMARY KEY,
  seeker_id BIGINT,
  job_id BIGINT,
  cover_letter CLOB,
  applied_on TIMESTAMP,
  status VARCHAR(50)
);
