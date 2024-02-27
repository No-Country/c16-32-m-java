CREATE DATABASE IF NOT EXISTS c16_32_m_java;

USE c16_32_m_java;

CREATE TABLE IF NOT EXISTS artists (
	artist_id VARCHAR(255) NOT NULL,
	artist_name VARCHAR(255),
	PRIMARY KEY (artist_id)
);

create table if not exists chats (
	chat_id BIGINT NOT NULL AUTO_INCREMENT,
	active BIT,
	date DATETIME(6),
	last_message VARCHAR(255),
	previous_messages VARBINARY(255),
	receiver_id BIGINT,
	sender_id BIGINT,
	PRIMARY KEY (chat_id)
);

CREATE TABLE IF NOT EXISTS genres (
	genre_id BIGINT NOT NULL AUTO_INCREMENT,
	genre_name VARCHAR(255),
	PRIMARY KEY (genre_id)
);

CREATE TABLE IF NOT EXISTS match_preferences (
	match_preference_id BIGINT NOT NULL AUTO_INCREMENT,
	active BIT NOT NULL,
	compatibility_percentage ENUM('MELODIAS_GEMELAS','RITMO_PERFECTO','BUENOS_ACORDES','NOTAS_SIMILARES','NO_DESAFINAN'),
	distance ENUM('MUY_CERCA','CERCA','LEJOS','MUY_LEJOS'),
	female BIT NOT NULL,
	just_friends BIT NOT NULL,
	long_term_relationship BIT NOT NULL,
	male BIT NOT NULL,
	max_age INTEGER NOT NULL,
	min_age INTEGER NOT NULL,
	other BIT NOT NULL,
	right_now BIT NOT NULL,
	user_id BIGINT,
	PRIMARY KEY (match_preference_id)
);

CREATE TABLE IF NOT EXISTS matches (
	match_id BIGINT NOT NULL AUTO_INCREMENT,
	active BIT,
	compatibility_percentage FLOAT(23),
	date_of_match DATETIME(6),
	chat_id BIGINT,
	user_id_1 BIGINT,
	user_id_2 BIGINT,
	PRIMARY KEY (match_id)
);

CREATE TABLE IF NOT EXISTS reported_messages (
	reported_message_id BIGINT NOT NULL AUTO_INCREMENT,
	date DATETIME(6),
	message VARCHAR(255),
	reviewed BIT,
	chat_id BIGINT,
	receiver_id BIGINT,
	sender_id BIGINT,
	PRIMARY KEY (reported_message_id)
);

CREATE TABLE IF NOT EXISTS users (
	user_id BIGINT NOT NULL AUTO_INCREMENT,
	active BIT NOT NULL,
	banned_users VARBINARY(255),
	birthdate DATETIME(6),
	current_song VARCHAR(255),
	description VARCHAR(255),
	email VARCHAR(255),
	gender ENUM('MASCULINO','FEMENINO','OTRX'),
	name VARCHAR(255),
	password VARCHAR(255),
	photo VARCHAR(255),
	pronouns VARCHAR(255),
	social_battery ENUM('AVAILABLE','AWAY','BUSY'),
	PRIMARY KEY(user_id)
);

CREATE TABLE IF NOT EXISTS users_artists (
	user_user_id BIGINT NOT NULL,
	artists_artist_id VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users_genres (
	user_user_id BIGINT NOT NULL,
	genres_genre_id BIGINT NOT NULL
);
 
ALTER TABLE matches 
ADD CONSTRAINT UNIQUE(user_id_1, user_id_2);

ALTER TABLE matches 
ADD CONSTRAINT UNIQUE(chat_id);

ALTER TABLE chats 
ADD CONSTRAINT FOREIGN KEY(receiver_id) REFERENCES users(user_id);

ALTER TABLE chats 
ADD CONSTRAINT FOREIGN KEY(sender_id) REFERENCES users(user_id);

ALTER TABLE matches 
ADD CONSTRAINT FOREIGN KEY(chat_id) REFERENCES chats(chat_id);

ALTER TABLE matches 
ADD CONSTRAINT FOREIGN KEY(user_id_1) REFERENCES users(user_id);

ALTER TABLE matches 
ADD CONSTRAINT FOREIGN KEY(user_id_2) REFERENCES users(user_id);

ALTER TABLE reported_messages 
ADD CONSTRAINT FOREIGN KEY(chat_id) REFERENCES chats(chat_id);

ALTER TABLE reported_messages 
ADD CONSTRAINT FOREIGN KEY(receiver_id) REFERENCES users(user_id);

ALTER TABLE reported_messages 
ADD CONSTRAINT FOREIGN KEY(sender_id) REFERENCES users(user_id);

ALTER TABLE users_artists 
ADD CONSTRAINT FOREIGN KEY(artists_artist_id) REFERENCES artists(artist_id);

ALTER TABLE users_artists 
ADD CONSTRAINT FOREIGN KEY(user_user_id) REFERENCES users(user_id);

ALTER TABLE users_genres 
ADD CONSTRAINT FOREIGN KEY(genres_genre_id) REFERENCES genres(genre_id);

ALTER TABLE users_genres 
ADD CONSTRAINT FOREIGN KEY(user_user_id) REFERENCES users(user_id);