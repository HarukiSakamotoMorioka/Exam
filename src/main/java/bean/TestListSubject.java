package bean;

public class TestListSubject {

    private String subjectCd;   // 科目コード
    private String subjectName; // 科目名

    private String studentNo;   // 学生番号
    private int no;             // 回数
    private int point;          // 点数
    private String classNum;    // クラス

    // ★★★ 追加項目（科目別一覧に必要） ★★★
    private int entYear;        // 入学年度
    private String studentName; // 氏名
    private Integer point1;     // 1回
    private Integer point2;     // 2回

    public TestListSubject() {}

    public String getSubjectCd() {
        return subjectCd;
    }

    public void setSubjectCd(String subjectCd) {
        this.subjectCd = subjectCd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    // ★★★ 追加した getter/setter ★★★

    public int getEntYear() {
        return entYear;
    }

    public void setEntYear(int entYear) {
        this.entYear = entYear;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getPoint1() {
        return point1;
    }

    public void setPoint1(Integer point1) {
        this.point1 = point1;
    }

    public Integer getPoint2() {
        return point2;
    }

    public void setPoint2(Integer point2) {
        this.point2 = point2;
    }
}
