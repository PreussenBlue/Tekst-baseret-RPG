package Save_Load;

import java.sql.*;
import java.time.Instant;

import java.util.ArrayList;
import java.util.Scanner;

public class Save {

    private static final String[] updateSet = {
            "update character set name = ",
            "update character set age = ",
            "update character set race = ",
            "update character set gender = ",
            "update character set class = ",
            "update character set HP = ",
            "update character set speed = ",
            "update character set attack = ",
            "update character set AC = ",
            "update character set SaveName = "
    };

    private static final String createTableSQLC = "create table character(\r\n" +
            " id    varchar(3) primary key,\r\n" +
            " name varchar(20),\r\n" +
            " age int(5),\r\n" +
            " race varchar(10),\r\n" +
            " gender varchar(10),\r\n" +
            " class varchar(20),\r\n" +
            " HP int(5),\r\n" +
            " speed int(5),\r\n" +
            " attack int(5),\r\n" +
            " AC int(5),\r\n" +
            " SaveName  varchar(20)\r\n" +
            "  );";


    private static final String createTableSQLI = "create table inventory(\r\n" +
            " id    varchar(3) primary key,\r\n" +
            " id_save varchar(3),\r\n" +
            " item_name varchar(10),\r\n" +
            " modS int(5),\r\n" +
            " modAC int(5),\r\n" +
            " modA int(5),\r\n" +
            " modHP int(5),\r\n" +
            "equipped int(5)\r\n" +
            " );";

    private static final String[] updateSetI = {
            "update inventory set item_name = ",
            "update inventory set modS = ",
            "update inventory set modAC = ",
            "update inventory set modA = ",
            "update inventory set modHP = ",
            "update inventory set id_save = ",
            "update inventory set equipped = "

    };

    private static final String[] GetI = {
            "select id from inventory where id_save = '%s' and (equipped = %s)",
            "select id from inventory where id_save = '%s' and (item_name = %s)",
            "select id from inventory where id_save = '%s' and (modS = %s)",
            "select id from inventory where id_save = '%s' and (modAC = %s)",
            "select id from inventory where id_save = '%s' and (modA = %s)",
            "select id from inventory where id_save = '%s' and (modHP = %s)"
    };

    private static final String QUERY_C = "select id,name,age,race,gender,class,HP,speed,attack,AC,SaveName from character";

    private static final String QUERY_I ="select id,id_save,item_name,modS,modAC,modA,modHP,equipped from inventory";

    private static final String QUERY_iV = "select id,id_save,item_name,modS,modAC,modA,modHP,equipped from inventory where id_save = ";

    public static void main(String[] argv) throws SQLException{

            Save SQLRun = new Save();


            SQLRun.SQLMainCreateTable();

    }



    public void SQLMainCreateTable() throws SQLException{




        Connection c;



        Save CreateCharacter  = new Save();


        try {

            c = DriverManager.getConnection("jdbc:sqlite:Save.db");
            System.out.println("Opened database successfully");


            try {
                CreateCharacter.createTableC(c);

            }catch (SQLException e){printSQLException(e);
                 }

            try {
                CreateCharacter.createTableI(c);
            }catch (SQLException e){printSQLException(e);};

                //UpdateCharacter.UpdateCharacterS(c,);


                //ArrayList<String> idA =  GetCharacterId(c);
                //String id = idA.get(0);

                //RemoveCharacter.RemoveCharacterS(c,id);
                //GetCharacter.GetCharacterS(c);






                //ArrayList<String> PRS = GetCharacter.GetCharacterS(c);

                //System.out.println(PRS.get(0));




                //ArrayList<String> CharacterInfo = new ArrayList<>();

                //CharacterInfo.add("hero steve");
                //CharacterInfo.add("30");
                //CharacterInfo.add("human");
                //CharacterInfo.add("");
                //CharacterInfo.add("warriors");
                //CharacterInfo.add("");
                //CharacterInfo.add("");
                //CharacterInfo.add("");
                //CharacterInfo.add("");
                //CharacterInfo.add("new");


                //InputCharacter.InputCharacterS(c,CharacterInfo);


            c.close();
        } catch (SQLException e) {

            printSQLException(e);
        }

    }


    //character
    public void createTableC(Connection c) throws SQLException {
        System.out.println(createTableSQLC);
        Statement stmt;


        stmt = c.createStatement();


        stmt.executeUpdate(createTableSQLC);


        stmt.close();

    }

    public void createTableI(Connection c) throws SQLException {
        System.out.println(createTableSQLI);
        Statement stmt;


        stmt = c.createStatement();


        stmt.executeUpdate(createTableSQLI);


        stmt.close();

    }

    public void UpdateCharacterS(Connection c, String[] UpdateC, String NVUSet, String NVUId,int CommandV) throws SQLException{

        String UpdateCharacterSQL = String.format(UpdateC[CommandV] + "\"%s\" where id = '%s';",NVUSet,NVUId);
        Statement statement = c.createStatement();

        statement.executeUpdate(UpdateCharacterSQL);

        statement.close();
    }

    public void RemoveCharacterS(Connection c,String id) throws SQLException{


        Statement statement = c.createStatement();
        String RemoveSQL = String.format("delete from character where id = '%s' ;",id);

        int result = statement.executeUpdate(RemoveSQL);
        System.out.println(result);


    }

    public ArrayList<String> GetCharacterS(Connection c) throws SQLException{

        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(QUERY_C);

        ArrayList<String>RS = new ArrayList<>();


        while (rs.next()){

            String id = rs.getString("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String race = rs.getString("race");
            String Class = rs.getString("class");
            String SaveName = rs.getString("SaveName");


            String Rs = "("+id+", "+name+", "+age+", "+race+", "+Class+", "+SaveName+")";



            RS.add(Rs);


        }


        return RS;

    }

    public ArrayList<String> GetCharacterId(Connection c) throws SQLException{

        Statement statement = c.createStatement();
        ResultSet rs = statement.executeQuery(QUERY_C);

        ArrayList<String>RS = new ArrayList<>();


        while (rs.next()){

            String id = rs.getString("id");



            RS.add(id);


        }


        return RS;

    }



    public void InputCharacterS(Connection c, String[] CharacterInfo) throws SQLException{

        Instant id = Instant.now();

        String InsertDataC = "insert into character " + String.format(
                "VALUES ( '%s' , '%s', %s, '%s', '%s', '%s','%s','%s','%s','%s','%s');",
                id,
                CharacterInfo[0],
                CharacterInfo[1],
                CharacterInfo[2],
                CharacterInfo[3],
                CharacterInfo[4],
                CharacterInfo[5],
                CharacterInfo[6],
                CharacterInfo[7],
                CharacterInfo[8],
                CharacterInfo[9]


        );

        Statement statement = c.createStatement();
        statement.executeUpdate(InsertDataC);

        statement.close();

    }

    //item

    public void UpdateItems(Connection c, String NVUSet, String NVUToo,int CommandV) throws SQLException{

        String UpdateCS = updateSetI[CommandV];

        //"update character set age = "

        String UpdateCharacterSQL = String.format(UpdateCS + "%s" +" where id = '%s';",NVUSet,NVUToo);

        System.out.println(UpdateCharacterSQL);

        Statement statement = c.createStatement();

        statement.executeUpdate(UpdateCharacterSQL);

        statement.close();
    }

    public int RemoveItems(Connection c, String nameI) throws SQLException {

        Statement statement = c.createStatement();

        String RemoveSQL = String.format("delete from inventory where name = '%s' ;",nameI);

        return statement.executeUpdate(RemoveSQL);



    }

    public ArrayList<String> GetItems(Connection c,String CId) throws SQLException{

        Statement statement = c.createStatement();
        //try {
            ResultSet rs = statement.executeQuery(QUERY_I);

        ArrayList<String>RS = new ArrayList<>();


        while (rs.next()){


            String idSaveC = rs.getString("id_Save");
            if (CId.equals(idSaveC)) {

                String id = rs.getString("id");
                String idSave = rs.getString("id_Save");
                String Items_name = rs.getString("Item_name");
                int modS = rs.getInt("modS");
                String modAC = rs.getString("modAC");
                String modA = rs.getString("modA");
                String modHP = rs.getString("modHP");


                String Rs = "(" + id + ", " + idSave + "," + Items_name + ", " + modS + ", " + modAC + ", " + modA + ", " + modHP + ")";


                RS.add(Rs);
            }

        }


        return RS;
        //}catch (SQLException e){
            //ArrayList<String> f = new ArrayList<>();
            //f.add("no items!");
            //return f;

        //}
    }

    public String Get1Item_id(Connection c,String id_save,String uni,int commandV) throws SQLException{

        Statement statement = c.createStatement();
        String Amore = GetI[commandV];

        String f = String.format(Amore,id_save,uni);

        ResultSet rs = statement.executeQuery(f);
        String id = rs.getString("id");

        return id;

    }

    public int InputItems(Connection c,String[] ItemInfo,String IdSave) throws SQLException{

        Instant id = Instant.now();

        String InsertDataC = "insert into inventory " + String.format(
                "VALUES ( '%s' , '%s', '%s', %s, %s, %s, %s,%s);",
                id,
                IdSave,
                ItemInfo[0],
                ItemInfo[1],
                ItemInfo[2],
                ItemInfo[3],
                ItemInfo[4],
                ItemInfo[5]

        );

        Statement statement = c.createStatement();
        int result = statement.executeUpdate(InsertDataC);

        statement.close();
        return result;

    }

    public int[] get_CV_ints(Connection c,String id) throws SQLException{

        Statement statement = c.createStatement();

        String AMore = QUERY_C + String.format("'%s'",id);

        ResultSet rs = statement.executeQuery(AMore);


        System.out.println(rs);

        int speed = rs.getInt("speed");
        int AC = rs.getInt("AC");
        int attack = rs.getInt("attack");
        int HP = rs.getInt("HP");

        return new int[]{speed,AC,attack,HP};

    }

    public int[] get_IV_ints(Connection c,String id) throws SQLException{

        Statement statement = c.createStatement();

        String AMore = QUERY_iV + String.format("'%s'",id) + String.format(" and (equipped = '%s')","1");

        ResultSet rs = statement.executeQuery(AMore);

        int speed = rs.getInt("modS");
        int AC = rs.getInt("modAC");
        int attack = rs.getInt("modA");
        int HP = rs.getInt("modHP");

        return new int[]{speed,AC,attack,HP};

    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
