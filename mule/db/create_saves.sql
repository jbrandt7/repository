DROP TABLE IF EXISTS saves;

CREATE TABLE saves (
    name VARCHAR(15),
    total_player_count INTEGER,
    player1 BLOB,
    player2 BLOB,
    player3 BLOB,
    player4 BLOB,
    turn BLOB,
    map BLOB,
    town BLOB,
    time_saved DATE,
    PRIMARY KEY(name)
);
