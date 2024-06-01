package net.communityboard.db;

public class NoticeBean {
	private int 	notice_num;
	private String	notice_id;
	private String  notice_title;
	private String  notice_content;
	private String	notice_file;
	private int  	notice_re_ref;
	private int 	notice_re_lev;
	private int		notice_re_seq;
	private String	notice_date;
	private int		notice_readcount;
	
	
	public int getNotice_re_ref() {
		return notice_re_ref;
	}
	public void setNotice_re_ref(int notice_re_ref) {
		this.notice_re_ref = notice_re_ref;
	}
	public int getNotice_re_lev() {
		return notice_re_lev;
	}
	public void setNotice_re_lev(int notice_re_lev) {
		this.notice_re_lev = notice_re_lev;
	}
	public int getNotice_re_seq() {
		return notice_re_seq;
	}
	public void setNotice_re_seq(int notice_re_seq) {
		this.notice_re_seq = notice_re_seq;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(String notice_file) {
		this.notice_file = notice_file;
	}
	public String getNotice_date() {
		return notice_date;
	}
	public void setNotice_date(String notice_date) {
		this.notice_date = notice_date.substring(0,10);
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	

}
