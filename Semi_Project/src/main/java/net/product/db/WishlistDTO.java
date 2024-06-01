package net.product.db;

public class WishlistDTO {
	public int wishlist_code;
    public String id;      
    public int product_code;
    public String opt_color;
	public String opt_size;
	public String product_name;
	public int product_price;
	public String product_image;

	
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	
    public int getwishlist_code() {
		return wishlist_code;
	}
	public void setwishlist_code(int cart_code) {
		this.wishlist_code = cart_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getProduct_code() {
		return product_code;
	}
	public void setProduct_code(int product_code) {
		this.product_code = product_code;
	}
	public String getOpt_color() {
		return opt_color;
	}
	public void setOpt_color(String opt_color) {
		this.opt_color = opt_color;
	}
	public String getOpt_size() {
		return opt_size;
	}
	public void setOpt_size(String opt_size) {
		this.opt_size = opt_size;
	}
}
