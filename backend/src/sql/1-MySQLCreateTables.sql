DROP TABLE Sale;
DROP TABLE User;
DROP TABLE Session;
DROP TABLE Movie;
DROP TABLE Screen;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Screen (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(60) NOT NULL,
    capacity SMALLINT NOT NULL,
    CONSTRAINT ScreenPK PRIMARY KEY (id),
    CONSTRAINT ScreenNameUniqueKey UNIQUE (name)
) ENGINE = InnoDB;

CREATE INDEX ScreenIndexByName ON Screen (name);

CREATE TABLE Movie(
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(60) NOT NULL,
    summary VARCHAR(2000) NOT NULL,
    runtime SMALLINT NOT NULL,
    CONSTRAINT MoviePK PRIMARY KEY (id),
    CONSTRAINT MovieTitleUniqueKey UNIQUE (title)
)ENGINE = InnoDB;

CREATE INDEX MovieIndexByTitle ON Movie (title);

CREATE TABLE Session(
    id BIGINT NOT NULL AUTO_INCREMENT,
    sessionDate DATETIME NOT NULL,
    price DECIMAL(11, 2) NOT NULL,
    movieId BIGINT NOT NULL,
    screenId BIGINT NOT NULL,
    remainingSeats INT NOT NULL,
    version BIGINT NOT NULL default 0,
    CONSTRAINT SessionPK PRIMARY KEY (id),
    CONSTRAINT SessionScreenIdFK FOREIGN KEY (screenId)
                    REFERENCES Screen (id),
    CONSTRAINT SessionMovieIdFK FOREIGN KEY (movieId)
                    REFERENCES Movie(id)
)ENGINE = InnoDB;

CREATE TABLE Sale(
    id BIGINT NOT NULL AUTO_INCREMENT,
    saleDate DATETIME NOT NULL,
    bankCard VARCHAR(60) NOT NULL,
    numberTickets SMALLINT NOT NULL,
    delivered BIT NOT NULL,
    sessionId BIGINT NOT NULL,
    userId BIGINT NOT NULL,
    CONSTRAINT SalePK PRIMARY KEY (id),
    CONSTRAINT SaleSessionIdFK FOREIGN KEY (sessionId)
                REFERENCES Session(id),
    CONSTRAINT SaleUserIdFK FOREIGN KEY(userId)
                 REFERENCES User(id)
) ENGINE = InnoDB;