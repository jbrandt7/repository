package mule;

import java.sql.*;
import com.mysql.jdbc.Driver;
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

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/mule";

    static final String USER = "root";
    static final String PASSWORD = "password";

    private static Connection conn;
    private static Statement statement;

    public List<String> getSaves() {
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
        return null;
    }

    public void saveGame() {
        PreparedStatement insertSave = null;
        try {
            openConnection();

            String insert = "INSERT INTO mule.saves (name, total_player_count,"
                    + " player1, player2, player3, player4, turn, map,"
                    + " town, time_saved) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            insertSave = conn.prepareStatement(insert);
            insertSave.setString(1, Main.getSaveName());
            insertSave.setInt(2, Main.getPlayerCount());

            for (int i = 0; i < Main.getPlayerCount(); i++) {
                insertSave.setObject(i + 3, Main.getPlayer(i));
            }

            for (int i = Main.getPlayerCount(); i < 4; i++) {
                insertSave.setNull(i + 3, Types.BLOB);
            }

            insertSave.setObject(7, Main.getTurn());
            insertSave.setObject(8, Main.getMap());
            insertSave.setObject(9, Main.getTown());

            long now = new java.util.Date().getTime();
            Date sql_date = new Date(now);
            insertSave.setDate(10, sql_date);

            insertSave.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
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

    public void loadGame(String id) {
        PreparedStatement selectSave = null;
        try {
            openConnection();

            String select = "SELECT total_player_count,"
                    + " player1, player2, player3, player4, turn, map,"
                    + " town, time_saved FROM mule.saves WHERE name = ?";

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

            buf = result.getBytes(6);
            Main.setTurn((Turn) readInObject(buf));

            buf = result.getBytes(7);
            Main.setMap((Map) readInObject(buf));

            buf = result.getBytes(8);
            Main.setTown((Town) readInObject(buf));

        } catch (SQLException ex) {
            ex.printStackTrace();
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

    public boolean checkName(String toCheck) {
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

        } catch (SQLException ex) {
            ex.printStackTrace();
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
        return true;
    }

    private void openConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    private void closeConnection() throws SQLException {
        conn.close();
    }

    private Object readInObject(byte[] buf) throws IOException, ClassNotFoundException {
        ObjectInputStream objectIn = null;
        if (buf != null)
            objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
        return objectIn.readObject();
    }

}
