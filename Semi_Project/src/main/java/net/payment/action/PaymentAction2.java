package net.payment.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.payment.db.Order_Info;
import net.payment.db.Order_Item;

public class PaymentAction2 implements Action {

		@Override
		public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			HttpSession session = request.getSession();
				String id = (String)session.getAttribute("id");
				if(id !=null ) {
					ActionForward forward = new ActionForward();
					Order_Info info = new Order_Info();
					OrderinfoDAO infodao = new OrderinfoDAO();
					info.setAddress1(request.getParameter("address"));
					info.setAddress2(request.getParameter("address_detail"));
					info.setDelivery_message(request.getParameter("deliveryMsg"));
					info.setId(id);
					info.setOrder_cost(Integer.parseInt(request.getParameter("totalprice")));
					String payment = request.getParameter("choose");
					
					info.setPayment_option(payment);
					if(payment=="무통장 입금") {
						info.setDepositor(request.getParameter("depositor"));
					}
					info.setPost(request.getParameter("post"));
					info.setReceiver_name(request.getParameter("name"));
					
					String phone = request.getParameter("hp1");
					phone += request.getParameter("hp2");
					phone += request.getParameter("hp3");
					System.out.println(phone);
					info.setReceiver_phone(phone);
					
					
					Order_Item item = new Order_Item();
					System.out.println(request.getParameter("count"));
					item.setProduct_count(Integer.parseInt(request.getParameter("count")));
					item.setProduct_code(Integer.parseInt(request.getParameter("product_code")));
					item.setProduct_price(Integer.parseInt(request.getParameter("totalproductprice")));
					item.setProduct_image(request.getParameter("product_image"));
					item.setProduct_name(request.getParameter("product_name"));
					item.setProduct_size(request.getParameter("size"));
					item.setProduct_color(request.getParameter("color"));
					
					int result = infodao.infoinsert(info,item) ;
					System.out.println("orderinfo 결과는 "+result);

					if(result==0) {
						System.out.println("주문처리 실패");
						forward.setPath("error/error.jsp");
						request.setAttribute("message", "주문 실패입니다.");
						forward.setRedirect(false);
						return forward;
					}
					
					forward.setRedirect(false);
					request.setAttribute("order_info", info);
					request.setAttribute("order_item", item);
					forward.setPath("payment/order_complete2.jsp");//주문처리 보기 페이지로 이동 경로 설정
					return forward;
	
				}else {
				// 로그인을 안 했으면
				String message = "결제를 하기 위해서는 먼저 로그인을 하세요!!";
				 
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('" + message + "');");
				out.println("location.href='history.back()';");
				out.println("</script>");
				out.close();
				return null;
				     
				
			}
	
		}

	}