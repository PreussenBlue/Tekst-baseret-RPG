
// READ ME
// denne class (main.java) er kun for at vise hvordan man bruger Save classen
// den endelige kode er Save classen

package Save_Load;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

//  forskælige commandoer for UpdateCharacterS funktionen i en string[] format som man vælger i mellem, det betyder at der skal indsætes et tal fra 0 - 9 i commandoV for at vælge en commando
//            "update character set name = ",
//            "update character set age = ",
//            "update character set race = ",
//            "update character set gender = ",
//            "update character set class = ",
//            "update character set HP = ",
//            "update character set speed = ",
//            "update character set attack = ",
//            "update character set AC = ",
//            "update character set SaveName = "

//  forskælige commandoer for UpdateItems funktionen i en string[] format som man vælger i mellem, det betyder at der skal indsætes et tal fra 0 - 6 i commandoV for at vælge en commando
//            "update inventory set item_name = ",
//            "update inventory set modS = ",
//            "update inventory set modAC = ",
//            "update inventory set modA = ",
//            "update inventory set modHP = ",
//            "update inventory set id_save = ",
//            "update inventory set equipped = "

//  forskælige commandoer for Get1Item_id funktionen i en string[] format som man vælger i mellem, det betyder at der skal indsætes et tal fra 0 - 5 i commandoV for at vælge en commando
//            "select id from inventory where id_save = '%s' and (equipped = %s)",
//            "select id from inventory where id_save = '%s' and (item_name = %s)",
//            "select id from inventory where id_save = '%s' and (modS = %s)",
//            "select id from inventory where id_save = '%s' and (modAC = %s)",
//            "select id from inventory where id_save = '%s' and (modA = %s)",
//            "select id from inventory where id_save = '%s' and (modHP = %s)"

public class main {

    // vi laver en array som kommer til at reprisentere input variabler fra vores bruger til character
    public static final String[] easy_Q = {
            "",//name
            "0",//age
            "",//race
            "",//gender
            "",//class
            "1",//hp
            "1",//speed
            "1",//attack
            "1",//AC
            "",//SaveGame

    };

    // vi laver en array som kommer til at reprisentere input variabler fra vores bruger til inventory
    public static final String[] easy_qi = {
            "",
            "1",
            "1",
            "1",
            "1",
            "1"

    };



    public static void main(String[] args) throws SQLException {



        Connection c;

        // caller classen Save med variabel navnet get
        Save get = new Save();

        // conecter til drive med variable c
        c = DriverManager.getConnection("jdbc:sqlite:Save.db");
        System.out.println("Opened database successfully");

        // laver data database table character og inventory
        get.SQLMainCreateTable();

        // inputer variabler i character table
        get.InputCharacterS(c,easy_Q);
        ArrayList<String> f = get.GetCharacterId(c);


        System.out.println("chose a save:");

        // få character saves i arraylist, vi kan ændre det senere til array hvis det skal være
        ArrayList<String> j = get.GetCharacterS(c);

        // printer alle saves så brugen kan vælge et save
        for (int i = 0; i< j.size(); i++) {
            System.out.println(i+":"+j.get(i));
        }

        // vænter for int input, for valg af save
        Scanner lyt = new Scanner(System.in);

        int l = lyt.nextInt();

        // save info
        String hS = j.get(l);
        // id af save
        String hId = f.get(l);


        // inputer item variabler i inventory table,
        get.InputItems(c, easy_qi, hId);

        // henter inventory info fra selected save som er som er conected med hinaden
        ArrayList<String> b = get.GetItems(c,hId);

        // printer information ud
        System.out.println(
                hS+
                "\n inventory:" +
                b
                );

        // får character stats i en int[]
        int[] CV = get.get_CV_ints(c,hId);


        try {

            // får item stat modefiers, fra inventory table i en int[]
            int[] IV = get.get_IV_ints(c, hId);

            // pluser item modefieres sammen med character stats
            int iv0 = CV[0] + IV[0];
            int iv1 = CV[1] + IV[1];
            int iv2 = CV[2] + IV[2];
            int iv3 = CV[3] + IV[3];

            // printer modificeret stats
            System.out.println(iv0 + "," + iv1 + "," + iv2 + "," + iv3);

            //printer hvis der ikke er nogen items equipped
        }catch (SQLException e){System.out.println("no items equipped!");}

        // henter id fra en item med et spicifikt unikt variable
        String I_Id = get.Get1Item_id(c,hId,"1",0);

        // updater item til ikke at være equipped
        get.UpdateItems(c, "0", I_Id, 6);

        // printer item igen for at chekke om den er unequipped
        CV = get.get_CV_ints(c,hId);

        System.out.println(
                hS+
                        "\n inventory:" +
                        b
        );


        try {


            int[] IV = get.get_IV_ints(c, hId);


            System.out.println(CV);

            System.out.println(IV);

            int iv0 = CV[0] + IV[0];
            int iv1 = CV[1] + IV[1];
            int iv2 = CV[2] + IV[2];
            int iv3 = CV[3] + IV[3];
            System.out.println(iv0 + "," + iv1 + "," + iv2 + "," + iv3);
        }catch (SQLException e){System.out.println("no items equipped!");}


    }

}