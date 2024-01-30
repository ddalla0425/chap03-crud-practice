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
/*-----수정-------------------------------------------*/
public class Application4 {
    public static void main(String[] args) {
        // 첫번째! Connection 객체 생성!
        Connection con = getConnection();
        PreparedStatement pstmt = null; // try 안에 들어가기 전에 변수로 안하면 오류 뜬다!?
        int result = 0;


        Properties prop = new Properties();
        Scanner sc = new Scanner(System.in);

        System.out.println("변경하려는 직급 코드를 입력해주세요 : ");
        String jobCode = sc.nextLine();
        System.out.println("변경하려는 직급명을 입력해주세요 : ");
        String jobName = sc.nextLine();

        JobDTO changeJob = new JobDTO();
        changeJob.setJobCode(jobCode);
        changeJob.setJobName(jobName);


        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/job-query.xml"));
            String query = prop.getProperty("updateJob");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, changeJob.getJobName());
            pstmt.setString(2, changeJob.getJobCode());

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
            System.out.println("직업 변경 완료!");
        } else{
            System.out.println("직업 변경 실패!ㅠㅠ");
        }
    }
}
