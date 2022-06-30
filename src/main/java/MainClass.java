import dto.Item;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.print("아이템 등록\n이름 입력 : ");
        String name = s.nextLine();
        System.out.print("속성 입력 : ");
        String att = s.nextLine();
        System.out.print("데미지 입력 : ");
        int dem = s.nextInt();
        //nextInt 다음 입력안될때
        s.nextLine();
        System.out.print("효과 입력 : ");
        String hyo = s.nextLine();
        //아이템 클레스에 입력 받은 내용들을 담아 보자
        Item item = new Item();
        item.setName(name);
        item.setAtt(att);
        item.setDem(dem);
        item.setHyo(hyo);

        DBClass dc = new DBClass();
        dc.insertItem(item);
        dc.selectItem();
    }
}