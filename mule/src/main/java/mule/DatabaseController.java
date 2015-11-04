package mule;

import java.sql.*;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import mule.model.map.*;
import mule.model.player.*;
import mule.model.*;
import mule.model.town.*;

public class DatabaseController {

    private static final String DB_URL = "jdbc:sqlite:saves.db";

    private static Connection conn;

    private static final int TURN_COL = 7;
    private static final int MAP_COL = 8;
    private static final int TOWN_COL = 9;
    private static final int PLAYER_START_COL = 3;

    private static final int U_TURN_COL = 1;
    private static final int U_MAP_COL = 2;
    private static final int U_TOWN_COL = 3;
    private static final int U_NAME_COL = 4;

    public final List<String> getSaves() {
        PreparedStatement selectSaves = null;
        try {
            openConnection();
            String select = "SELECT name FROM mule.saves";
            selectSaves = conn.prepareStatement(select);
            ResultSet saves = selectSaves.executeQuery();

            List<String> result = new ArrayList<>();

            while (saves.next()) {
                result.add(saves.getString(1));
            }

            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSaves != null) {
                    selectSaves.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null) {
                    closeConnection();
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
                    insertSave.setObject(i + 1, Main.getPlayer(i));
                }

                insertSave.setObject(Main.getPlayerCount() + U_TURN_COL, Main.getTurn());
                insertSave.setObject(Main.getPlayerCount() + U_NAME_COL, Main.getMap());
                insertSave.setObject(Main.getPlayerCount() + U_TOWN_COL, Main.getTown());

                insertSave.setString(Main.getPlayerCount() + U_NAME_COL, Main.getSaveName());
            } else {

                sql = "INSERT INTO mule.saves (name, total_player_count,"
                        + " player1, player2, player3, player4, turn, map,"
                        + " town, time_saved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                insertSave = conn.prepareStatement(sql);
                insertSave.setString(1, Main.getSaveName());
                insertSave.setInt(2, Main.getPlayerCount());

                for (int i = 0; i < Main.getPlayerCount(); i++) {
                    insertSave.setObject(i + PLAYER_START_COL, Main.getPlayer(i));
                }

                for (int i = Main.getPlayerCount(); i < Main.MAX_PLAYERS; i++) {
                    insertSave.setNull(i + PLAYER_START_COL, Types.BLOB);
                }

                insertSave.setObject(TURN_COL, Main.getTurn());
                insertSave.setObject(MAP_COL, Main.getMap());
                insertSave.setObject(TOWN_COL, Main.getTown());

                long now = new java.util.Date().getTime();
                Date sqlDate = new Date(now);
                insertSave.setDate(10, sqlDate);
            }

            insertSave.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (insertSave != null) {
                    insertSave.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
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
        try {
            openConnection();

            String select = "SELECT total_player_count,"
                    + " player1, player2, player3, player4, turn, map,"
                    + " town, time_saved, name FROM mule.saves WHERE name = ?";

            selectSave = conn.prepareStatement(select);
            selectSave.setString(1, id);
            ResultSet result = selectSave.executeQuery();
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

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSave != null) {
                    selectSave.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null) {
                    closeConnection();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public final boolean checkName(String toCheck) {
        PreparedStatement selectSaves = null;
        try {
            openConnection();
            String select = "SELECT name FROM mule.saves";
            selectSaves = conn.prepareStatement(select);
            ResultSet saves = selectSaves.executeQuery();

            while (saves.next()) {
                if (saves.getString(1).equals(toCheck)) {
                    return false;
                }
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (selectSaves != null) {
                    selectSaves.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conn != null) {
                    closeConnection();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private void openConnection() throws SQLException {
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

}
