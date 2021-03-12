CREATE TABLE Category (
    CategoryID int,
    Category varchar(255),
    PRIMARY KEY (CategoryID)
);

CREATE TABLE Expense
(
    ExpenseID int,
    Expense varchar(255),
    Category int,
    Cost double precision,
    PRIMARY KEY (ExpenseID),
    FOREIGN KEY (Category)
      REFERENCES Category (CategoryID)
);

DROP TABLE IF EXISTS CategoryLimit CASCADE;
CREATE TABLE CategoryLimit
(
	limitID int,
    categoryLimit double precision,
    category int,
	PRIMARY KEY(limitID),
    FOREIGN KEY (category)
      REFERENCES category (categoryid)
);