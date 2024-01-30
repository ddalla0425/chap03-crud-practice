import dto.JobDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;
/*-----조회 1행-------------------------------------------*/
public class Application1 {
    public static void main(String[] args) {
        // 첫번째! Connection 객체 생성!
        Connection con = getConnection();
        PreparedStatement pstmt = null; // try 안에 들어가기 전에 변수로 안하면 오류 뜬다!?
        ResultSet rset = null;
        JobDTO selectJob = null;
        List<JobDTO> jobList = null;

        Properties prop = new Properties();

        Scanner sc = new Scanner(System.in);

        System.out.println("조회하려는 직급코드를 입력하세요 : ");
        String jobCode = sc.nextLine();


        try {
            prop.loadFromXML(new FileInputStream("src/main/java/mapper/job-query.xml"));
            String query = prop.getProperty("selectJob1");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, jobCode);

            rset = pstmt.executeQuery();

            jobList = new ArrayList<>();

            if(rset.next()) {
                selectJob = new JobDTO();

                selectJob.setJobCode(rset.getString("job_code"));
                selectJob.setJobName(rset.getString("job_name"));

                jobList.add(selectJob);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }
        for(JobDTO jobDTO : jobList) {
            System.out.println(jobDTO);
        }
    }
}
