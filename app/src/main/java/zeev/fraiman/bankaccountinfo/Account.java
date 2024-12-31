package zeev.fraiman.bankaccountinfo;

public class Account {
    private String accNum;
    private String accSum;
    private String accTime;
    private String accDate;
    private String accType;

    public Account(String accNum, String accSum, String accTime, String accDate, String accType) {
        this.accNum = accNum;
        this.accSum = accSum;
        this.accTime = accTime;
        this.accDate = accDate;
        this.accType = accType;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public String getAccSum() {
        return accSum;
    }

    public void setAccSum(String accSum) {
        this.accSum = accSum;
    }

    public String getAccTime() {
        return accTime;
    }

    public void setAccTime(String accTime) {
        this.accTime = accTime;
    }

    public String getAccDate() {
        return accDate;
    }

    public void setAccDate(String accDate) {
        this.accDate = accDate;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNum='" + accNum + '\'' +
                ", accSum='" + accSum + '\'' +
                ", accTime='" + accTime + '\'' +
                ", accDate='" + accDate + '\'' +
                ", accType='" + accType + '\'' +
                '}';
    }
}
