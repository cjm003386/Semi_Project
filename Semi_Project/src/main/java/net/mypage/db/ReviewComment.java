package net.mypage.db;

public class ReviewComment {
	private int num;
	private String id;
	private String content;
	private String reg_date;
	private int comment_review_num;
	private int comment_re_ref;
	private int comment_re_lev;
	private int comment_re_seq;
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public int getComment_review_num() {
		return comment_review_num;
	}
	public void setComment_review_num(int comment_review_num) {
		this.comment_review_num = comment_review_num;
	}
	public int getComment_re_ref() {
		return comment_re_ref;
	}
	public void setComment_re_ref(int comment_re_ref) {
		this.comment_re_ref = comment_re_ref;
	}
	public int getComment_re_lev() {
		return comment_re_lev;
	}
	public void setComment_re_lev(int comment_re_lev) {
		this.comment_re_lev = comment_re_lev;
	}
	public int getComment_re_seq() {
		return comment_re_seq;
	}
	public void setComment_re_seq(int comment_re_seq) {
		this.comment_re_seq = comment_re_seq;
	}
}
