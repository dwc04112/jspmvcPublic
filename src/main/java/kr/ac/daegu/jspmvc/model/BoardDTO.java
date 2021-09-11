package kr.ac.daegu.jspmvc.model;

import java.sql.Time;
import java.util.Date;

// db에서 Board테이블의 컬럼과 row를 정의.
// DataTransferObject : db에서 가져오는 테이블 row 데이터의 묶음.
// ValueObject        : db에서 가져오는 테이블 row 데이터의 묶음. setter가 없다.
public class BoardDTO {
    private int id;                      // 글 id(글번호) 1
    private String author;               // 작성자 이름 2
    private String subject;              // 글 제목 3
    private String content;              // 글 컨텐츠 4
    private Date writeDate;              // 작성 날짜 5
    private Time writeTime;              // 작성 시간 6
    private int readCount;               // 조회수 7
    private int commentCount;            // 댓글 갯수 8
    private String password;             // 수정 삭제를 위한 패스워드 9
    
    private int replyRootId;                   // 원래 글 번호 (답글일 경우)
    private int depth;                   // (답글일경우) 제목의 'RE: ' 갯수
    private int orderNum;                // (답글일경우) 글 목록보기 순서

    public int getReplyRootId() {
        return replyRootId;
    }

    public void setReplyRootId(int replyRootId) {
        this.replyRootId = replyRootId;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public Time getWriteTime() {
        return writeTime;
    }

    public void setWriteTime(Time writeTime) {
        this.writeTime = writeTime;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", writeDate=" + writeDate +
                ", writeTime=" + writeTime +
                ", readCount=" + readCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
