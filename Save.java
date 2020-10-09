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
            "id   varchar(3) primary key,\r\n" +
            "item_name varchar(10),\r\n" +
            "modS int(5),\r\n" +
            "modAC int(5),\r\n" +
            "modA int(5),\r\n" +
            "modHP int(5),\r\n" +
            " );";

    private static final String QUERY = "select id,name,age,race,class,SaveName from character";



    public static void main(String[] argv) throws SQLException{

            Save SQLRun = new Save();


            SQLRun.SQLMainCreateTable();

    }



    public void SQLMainCreateTable() throws SQLException{

        Scanner Input = new Scanner(System.in);
        int UserInput = Input.nextInt();
        System.out.println(UserInput);


        Connection c;



        Save CreateCharacter  = new Save();
        Save UpdateCharacter = new Save();
        Save RemoveCharacter = new Save();
        Save GetCharacter = new Save();
        Save InputCharacter = new Save();

        try {

            c = DriverManager.getConnection("jdbc:sqlite:Save.db");
            System.out.println("Opened database successfully");


            try {
                CreateCharacter.createTableC(c);
                CreateCharacter.createTableI(c);
            }catch (SQLException e){//printSQLException(e);
                 }


            if (UserInput == 1){
                //UpdateCharacter.UpdateCharacterS(c,);
                
            }

            if (UserInput == 2){

                ArrayList<String> idA =  GetCharacterId(c);
                String id = idA.get(0);

                RemoveCharacter.RemoveCharacterS(c,id);
                GetCharacter.GetCharacterS(c);


            }

            if (UserInput == 3){

                ArrayList<String> PRS = GetCharacter.GetCharacterS(c);

                System.out.println(PRS.get(0));
            }

            if (UserInput == 4){

                ArrayList<String> CharacterInfo = new ArrayList<>();

                CharacterInfo.add("hero steve");
                CharacterInfo.add("30");
                CharacterInfo.add("human");
                CharacterInfo.add("");
                CharacterInfo.add("warriors");
                CharacterInfo.add("");
                CharacterInfo.add("");
                CharacterInfo.add("");
                CharacterInfo.add("");
                CharacterInfo.add("new");


                InputCharacter.InputCharacterS(c,CharacterInfo);
            }

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

    public void UpdateCharacterS(Connection c, String[] UpdateC, String NVUSet, String NVUToo,int CommandV) throws SQLException{

        String UpdateCharacterSQL = String.format(UpdateC[CommandV] + "\"%s\" where id = '%s';",NVUSet,NVUToo);
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
        ResultSet rs = statement.executeQuery(QUERY);

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
        ResultSet rs = statement.executeQuery(QUERY);

        ArrayList<String>RS = new ArrayList<>();


        while (rs.next()){

            String id = rs.getString("id");



            RS.add(id);


        }


        return RS;

    }



    public void InputCharacterS(Connection c, ArrayList<String> CharacterInfo) throws SQLException{

        Instant id = Instant.now();

        String InsertDataC = "insert into character " + String.format(
                "VALUES ( '%s' , '%s', %s, '%s', '%s', '%s','%s','%s','%s','%s','%s');",
                id,
                CharacterInfo.get(0),
                CharacterInfo.get(1),
                CharacterInfo.get(2),
                CharacterInfo.get(3),
                CharacterInfo.get(4),
                CharacterInfo.get(5),
                CharacterInfo.get(6),
                CharacterInfo.get(7),
                CharacterInfo.get(8),
                CharacterInfo.get(9)


        );

        Statement statement = c.createStatement();
        statement.executeUpdate(InsertDataC);

        statement.close();

    }

    //item

    public void UpdateItems(Connection c, String[] UpdateC, String NVUSet, String NVUToo,int CommandV) throws SQLException{

        String UpdateCS = UpdateC[CommandV];

        //"update character set age = "

        String UpdateCharacterSQL = String.format(UpdateCS + "\"%s\"" +" where id = '%s';",NVUSet,NVUToo);

        Statement statement = c.createStatement();

        statement.executeUpdate(UpdateCharacterSQL);

        statement.close();
    }

    public int RemoveItems(Connection c, String nameI) throws SQLException {

        Statement statement = c.createStatement();

        String RemoveSQL = String.format("delete from inventory where name = '%s' ;",nameI);

        return statement.executeUpdate(RemoveSQL);



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
