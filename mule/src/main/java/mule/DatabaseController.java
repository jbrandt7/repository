package mule;

import java.sql.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import mule.model.map.*;
import mule.model.player.*;
import mule.model.*;
import mule.model.town.*;

public class DatabaseController {

    private static final String DB_URL = "jdbc:sqlite:mule.db";

    private static Connection conn;

    private static final int TURN_COL = 7;
    private static final int MAP_COL = 8;
    private static final int TOWN_COL = 9;
    private static final int PLAYER_START_COL = 3;

    private static final int U_TURN_COL = 1;
    private static final int U_MAP_COL = 2;
    private static final int U_TOWN_COL = 3;
    private static final int U_NAME_COL = 4;

    public DatabaseController() {
        try {
            openConnection();
            tryToCreateTable();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public final List<String> getSaves() {
        PreparedStatement selectSaves = null;
        ResultSet saves = null;
        try {
            openConnection();
            String select = "SELECT name FROM saves";
            selectSaves = conn.prepareStatement(select);
            saves = selectSaves.executeQuery();

            List<String> result = new ArrayList<>();

            while (saves.next()) {
                result.add(saves.getString(1));
            }

            return result;

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSaves != null) {
                    selectSaves.close();
                }
                if (conn != null) {
                    closeConnection();
                }
                if (saves != null) {
                    saves.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public final void saveGame() {
        PreparedStatement insertSave = null;
        try {

            boolean updateNotInsert = checkName(Main.getSaveName());

            openConnection();

            String sql;

            if (!updateNotInsert) {
                sql = "UPDATE saves SET ";

                for (int i = 0; i < Main.getPlayerCount(); i++) {
                    sql = sql + "player" + (i + 1) + "=?, ";
                }

                sql = sql + "turn=?, map=?, town=? WHERE name=?";

                insertSave = conn.prepareStatement(sql);

                for (int i = 0; i < Main.getPlayerCount(); i++) {
                    insertSave.setBytes(i + 1, writeInObject(Main.getPlayer(i)));
                }

                insertSave.setBytes(Main.getPlayerCount() + U_TURN_COL, writeInObject(Main.getTurn()));
                insertSave.setBytes(Main.getPlayerCount() + U_MAP_COL, writeInObject(Main.getMap()));
                insertSave.setBytes(Main.getPlayerCount() + U_TOWN_COL, writeInObject(Main.getTown()));

                insertSave.setString(Main.getPlayerCount() + U_NAME_COL, Main.getSaveName());
            } else {

                sql = "INSERT INTO saves (name, total_player_count,"
                        + " player1, player2, player3, player4, turn, map,"
                        + " town, time_saved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                insertSave = conn.prepareStatement(sql);
                insertSave.setString(1, Main.getSaveName());
                insertSave.setInt(2, Main.getPlayerCount());

                for (int i = 0; i < Main.getPlayerCount(); i++) {
                    insertSave.setBytes(i + PLAYER_START_COL, writeInObject(Main.getPlayer(i)));
                }

                for (int i = Main.getPlayerCount(); i < Main.MAX_PLAYERS; i++) {
                    insertSave.setNull(i + PLAYER_START_COL, Types.BLOB);
                }

                insertSave.setBytes(TURN_COL, writeInObject(Main.getTurn()));
                insertSave.setBytes(MAP_COL, writeInObject(Main.getMap()));
                insertSave.setBytes(TOWN_COL, writeInObject(Main.getTown()));

                long now = new java.util.Date().getTime();
                Date sqlDate = new Date(now);
                insertSave.setDate(10, sqlDate);
            }

            insertSave.executeUpdate();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (insertSave != null) {
                    insertSave.close();
                }
                if (conn != null) {
                    closeConnection();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public final void loadGame(String id) {
        PreparedStatement selectSave = null;
        ResultSet result = null;
        try {
            openConnection();

            String select = "SELECT total_player_count,"
                    + " player1, player2, player3, player4, turn, map,"
                    + " town, time_saved, name FROM saves WHERE name = ?";

            selectSave = conn.prepareStatement(select);
            selectSave.setString(1, id);
            result = selectSave.executeQuery();
            result.next();

            Main.setPlayerCount(result.getInt(1));

            byte[] buf;

            for (int i = 2; i < Main.getPlayerCount() + 2; i++) {
                buf = result.getBytes(i);
                Main.setPlayer(i - 2, (Player) readInObject(buf));
            }

            buf = result.getBytes(TURN_COL - 1);
            Main.setTurn((Turn) readInObject(buf));

            buf = result.getBytes(MAP_COL - 1);
            Main.setMap((Map) readInObject(buf));

            buf = result.getBytes(TOWN_COL - 1);
            Main.setTown((Town) readInObject(buf));

            Main.setSaveName(id);

            result.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSave != null) {
                    selectSave.close();
                }
                if (conn != null) {
                    closeConnection();
                }
                if (result != null) {
                    result.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private boolean checkName(String toCheck) {
        PreparedStatement selectSaves = null;
        ResultSet saves = null;
        try {
            openConnection();
            String select = "SELECT name FROM saves";
            selectSaves = conn.prepareStatement(select);
            saves = selectSaves.executeQuery();

            while (saves.next()) {
                if (saves.getString(1).equals(toCheck)) {
                    return false;
                }
            }

            saves.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSaves != null) {
                    selectSaves.close();
                }
                if (conn != null) {
                    closeConnection();
                }
                if (saves != null) {
                    saves.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(DB_URL);
    }

    private void closeConnection() throws SQLException {
        conn.close();
    }

    private Object readInObject(byte[] buf) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = null;
        if (buf != null) {
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        }
        return objectIn.readObject();
    }

    private byte[] writeInObject(Object o) throws IOException {
        ObjectOutputStream objectOut = null;
        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        if (o != null) {
            objectOut = new ObjectOutputStream(resultStream);
        }
        objectOut.writeObject(o);
        return resultStream.toByteArray();
    }

    private void tryToCreateTable() {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS saves (" +
                    "name VARCHAR(15), " +
                    "total_player_count INTEGER, " +
                    "player1 BLOB, " +
                    "player2 BLOB, " +
                    "player3 BLOB, " +
                    "player4 BLOB, " +
                    "turn BLOB, " +
                    "map BLOB, " +
                    "town BLOB, " +
                    "time_saved DATE, " +
                    "PRIMARY KEY(name));";

            stmt.executeUpdate(sql);
        } catch(SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
