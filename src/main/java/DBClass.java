import java.sql.*;

public class DBClass {
    //디비 연결메소드
    public Connection dbConn() {
        final String driver = "org.mariadb.jdbc.Driver";
        final String DB_IP = "localhost";
        final String DB_PORT = "3306";
        final String DB_NAME = "mydb";
        final String DB_USER = "root";
        final String DB_PASS = "1234";
        final String DB_URL =
                "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

        Connection conn = null;


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            if (conn != null) {
                System.out.println("DB 접속 성공");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로드 실패");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
        return conn;
    }

    //생성자
    //변수
    //메소드
    //데이터 넣기 메소드
    public void insertItem(String name, String att, int dem, String hyo) {
        //쿼리문 준비
        String sql = "INSERT INTO `item` (`name`, `att`, `dem`, `hyo`) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = null;
        Connection conn = dbConn();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, att);
            pstmt.setInt(3, dem);
            pstmt.setString(4, hyo);

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("아이템 데이터 삽입 성공!");

            }

        } catch (Exception e) {
            System.out.println("아이템 데이터 삽입 실패!");
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();


            }
        }


    }

    // 데이터 검색 메소드
    public void selectItem() {
        Connection conn = dbConn();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select * from item";

            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String att = rs.getString("att");
                int dem = rs.getInt("dem");
                String hyo = rs.getString("hyo");
                System.out.printf("이름 : %s, 속성 : %s, 데미지 : %d, 효과 : %s \n", name, att, dem, hyo);
            }

        } catch (SQLException e) {
            System.out.println("error: " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }

                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
