import dto.JobDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

/*-----삭제-------------------------------------------*/
public class Application5 {
    public static void main(String[] args) {
        // 첫번째! Connection 객체 생성!
        Connection con = getConnection();
        PreparedStatement pstmt = null; // try 안에 들어가기 전에 변수로 안하면 오류 뜬다!?
        int result = 0;

        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);

        System.out.println("삭제하려는 직급 코드를 입력해주세요 : ");
        String jobCode = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/job-query.xml"));
            String query = prop.getProperty("deleteJob");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, jobCode);

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        if(result > 0) {
            System.out.println("직업 삭제 완료!");
        } else{
            System.out.println("직업 삭제 실패!ㅠㅠ");
        }
    }
}
