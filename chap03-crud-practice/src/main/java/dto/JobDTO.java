package dto;

public class JobDTO {
    private String jobCode;
    private String jobName;

    public JobDTO() {
    }

    public JobDTO(String jobCode, String jobName) {
        this.jobCode = jobCode;
        this.jobName = jobName;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public String toString() {
        return "jobDTO{" +
                "jobCode='" + jobCode + '\'' +
                ", jobName='" + jobName + '\'' +
                '}';
    }
}
