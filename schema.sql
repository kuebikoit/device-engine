CREATE TABLE IF NOT EXISTS device_engine (
  hostname varchar(45) NOT NULL,
  ip varchar(45),
  last_updated_date timestamp DEFAULT current_timestamp,
  vulnerability varchar(45) NOT NULL,
  PRIMARY KEY (id)
);