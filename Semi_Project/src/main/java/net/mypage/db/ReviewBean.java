package net.mypage.db;

public class ReviewBean {
	private int review_num;
	private String review_name;
	private String review_pass;
	private String review_subject;
	private String review_content;
	private String review_file;
	private int review_re_ref;//답변 글 작성시 참조 글 번호
	private int review_re_lev;//답변 글 깊이
	private int review_re_seq;//답변 글의 순서
	private int review_readcount;//글의 조회수
	private String review_date;
	private String product_name;
	private int cnt;
	
	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public ReviewBean() {
		
	}
	
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public String getReview_name() {
		return review_name;
	}
	public void setReview_name(String review_name) {
		this.review_name = review_name;
	}
	public String getReview_pass() {
		return review_pass;
	}
	public void setReview_pass(String review_pass) {
		this.review_pass = review_pass;
	}
	public String getReview_subject() {
		return review_subject;
	}
	public void setReview_subject(String review_subject) {
		this.review_subject = review_subject;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_file() {
		return review_file;
	}
	public void setReview_file(String review_file) {
		this.review_file = review_file;
	}
	public int getReview_re_ref() {
		return review_re_ref;
	}
	public void setReview_re_ref(int review_re_ref) {
		this.review_re_ref = review_re_ref;
	}
	public int getReview_re_lev() {
		return review_re_lev;
	}
	public void setReview_re_lev(int review_re_lev) {
		this.review_re_lev = review_re_lev;
	}
	public int getReview_re_seq() {
		return review_re_seq;
	}
	public void setReview_re_seq(int review_re_seq) {
		this.review_re_seq = review_re_seq;
	}
	public int getReview_readcount() {
		return review_readcount;
	}
	public void setReview_readcount(int review_readcount) {
		this.review_readcount = review_readcount;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date.substring(0,10);;
	}
}
