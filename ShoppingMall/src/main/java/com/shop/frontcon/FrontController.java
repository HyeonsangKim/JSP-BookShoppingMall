package com.shop.frontcon;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.admin.cart.CartAdd;
import com.shop.admin.cart.CartDelete;
import com.shop.admin.cart.CartListLogic;
import com.shop.admin.cart.CartModify;
import com.shop.admin.cart.OrderLogic;
import com.shop.admin.cart.OrderPage;
import com.shop.admin.category.CategoryDeleteLogic;
import com.shop.admin.category.CategoryInputLogic;
import com.shop.admin.category.CategoryListLogic;
import com.shop.admin.product.ProductDeleteLogic;
import com.shop.admin.product.ProductListLogic;
import com.shop.admin.product.ProductWriteLogic;
import com.shop.board.BoardDeleteLogic;
import com.shop.board.BoardLogic;
import com.shop.board.BoardReadLogic;
import com.shop.board.BoardUpdateLogic;
import com.shop.board.BoardWriteLogic;
import com.shop.board.comment.CommentDelete;
import com.shop.board.comment.CommentModify;
import com.shop.board.comment.CommentWrite;
import com.shop.board.comment.ReComment;
import com.shop.board.comment.ReCommentWrite;
import com.shop.command.Command;
import com.shop.member.join.JoinCheckId;
import com.shop.member.join.JoinLogic;
import com.shop.member.join.SignOut;
import com.shop.member.login.Login;
import com.shop.member.login.Logout;
import com.shop.mypage.AdminPage;
import com.shop.mypage.MemberDelete;
import com.shop.mypage.ModifyLogic;
import com.shop.mypage.MyPage;

@WebServlet("*.do")
public class FrontController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		actionDo(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		actionDo(request,response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("actionDo");

		request.setCharacterEncoding("utf-8");
		Command command = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String cmd = uri.substring(conPath.length());
		
		if(cmd.equals("/index.do")) {
			viewPage="index.jsp";
		}
		// 회원가입
		else if(cmd.equals("/join/joinView.do")) {
			viewPage="joinView.jsp";
		}else if(cmd.equals("/join/JoinLogic.do")) {
			command = new JoinLogic();
			command.execute(request, response);
			viewPage = "JoinResult.do";
		}else if(cmd.equals("/join/JoinResult.do")) {
			viewPage = "joinResultView.jsp";
		}else if(cmd.equals("/join/JoinCheckId.do")) {
			command = new JoinCheckId();
			command.execute(request, response);
			viewPage="joinCheckIdView.jsp";
		}
		// 로그인.로그아웃
		else if(cmd.equals("/login/LoginPage.do")) {
			viewPage="loginView.jsp";
		}
		else if(cmd.equals("/login/Login.do")) {
			command=new Login();
			command.execute(request, response);
			viewPage = "LoginResult.do";
		}else if(cmd.equals("/login/LoginResult.do")) {
			viewPage = "loginResultView.jsp";
		}else if(cmd.equals("/login/LogoutLogic.do")) {
			command = new Logout();
			command.execute(request, response);
			viewPage= "LogoutResult.do";
		}else if(cmd.equals("/login/LogoutResult.do")) {
			viewPage="logoutResultView.jsp";
		}
		//마이페이지
		else if(cmd.equals("/mypage/mypageView.do")) {
			viewPage="mypage.jsp";
		}else if(cmd.equals("/mypage/MyPageLogic.do")) {
			command=new MyPage();
			command.execute(request, response);
			viewPage="myPageResult.jsp";
		}
		//회원 정보수정, 탈퇴
		else if(cmd.equals("/mypage/ModifyLogic.do")) {
			command = new ModifyLogic();
			command.execute(request,response);
			viewPage = "ModifyResult.do";
		}else if(cmd.equals("/mypage/ModifyResult.do")) {
			viewPage="modifyResult.jsp";
		}
		//관리자 페이지
		else if(cmd.equals("/mypage/AdminPage.do")) {
			command = new AdminPage();
			command.execute(request, response);
			viewPage="memberControl.do";
		}else if(cmd.equals("/mypage/memberControl.do")) {
			viewPage="memberControl.jsp";
		}else if(cmd.equals("/mypage/MemberDelete.do")) {
			command = new MemberDelete();
			command.execute(request, response);
			viewPage= "AdminPage.do";
		}
		//회원 탈퇴
		else if(cmd.equals("/signout/SingOutPage.do")) {
			viewPage="signoutView.jsp";
		}
		else if(cmd.equals("/signout/SignOut.do")) {
			command = new SignOut();
			command.execute(request, response);
			viewPage="signOutResult.do";
		}else if(cmd.equals("/signout/signOutResult.do")) {
			viewPage="signoutResultView.jsp";
		}
		//게시판 목록
		else if(cmd.equals("/board/BoardList.do")) {
			command = new BoardLogic();
			command.execute(request, response);
			viewPage="BoardListView.do";
		}else if(cmd.equals("/board/BoardListView.do")) {
			viewPage="boardListView.jsp";
		}
		//글 쓰기
		else if(cmd.equals("/board/BoardWriteView.do")) {
			viewPage="boardWriteView.jsp";
		}else if(cmd.equals("/board/BoardWrite.do")) {
			command = new BoardWriteLogic();
			command.execute(request, response);
			viewPage="BoardResult.do";
		}
		//글 보기
		else if(cmd.equals("/board/BoardRead.do")) {
			command = new BoardReadLogic();
			command.execute(request,response);
			viewPage="BoardReadResult.do";
		}else if(cmd.equals("/board/BoardReadResult.do")) {
			viewPage="boardReadView.jsp";
		}
		//글 수정
		else if(cmd.equals("/board/boardUpdateView.do")) {
			command = new BoardReadLogic();
			command.execute(request, response);
			viewPage="boardUpdateView.jsp";
		}
		else if(cmd.equals("/board/BoardUpdate.do")) {
			command = new BoardUpdateLogic();
			command.execute(request, response);
			viewPage="BoardResult.do";
		}
		else if(cmd.equals("/board/BoardResult.do")) {
			viewPage="boardResultView.jsp";
		}
		//글 삭제
		else if(cmd.equals("/board/BoardDelete.do")) {
			command = new BoardDeleteLogic();
			command.execute(request, response);
			viewPage = "BoardResult.do";
		}
		//댓글
		else if(cmd.equals("/board/CommentWrite.do")) {
			command = new CommentWrite();
			command.execute(request, response);
			viewPage="CommentResult.do";
		}else if(cmd.equals("/board/CommentResult.do")) {
			viewPage="commentResultView.jsp";
		}
		//대댓글
		else if(cmd.equals("/board/ReComment.do")) {
			command = new ReComment();
			command.execute(request, response);
			viewPage = "ReCommentWrite.do";
		}else if(cmd.equals("/board/ReCommentWrite.do")) {
			command = new ReCommentWrite();
			command.execute(request, response);
			viewPage="commentResultView.jsp";
		}
		//댓글 삭제
		else if(cmd.equals("/board/CommentDelete.do")) {
			command = new CommentDelete();
			command.execute(request, response);
			viewPage = "CommentResult.do";
		}
		//댓글 수정
		else if(cmd.equals("/board/CommentModify.do")) {
			command = new CommentModify();
			command.execute(request, response);
			viewPage="commentResultView.jsp";
		}
		
		//카테고리 관련
		else if(cmd.equals("/admin/CategoryList.do")) {
			command = new CategoryListLogic();
			command.execute(request, response);
			viewPage="categoryList.jsp";
		}
		else if(cmd.equals("/admin/CategoryInput.do")) {
			viewPage ="categoryInput.jsp";
		}
		else if(cmd.equals("/admin/CategoryInputLogic.do")) {
			command = new CategoryInputLogic();
			command.execute(request, response);
			viewPage="CategoryResult.do";
		}
		else if(cmd.equals("/admin/CategoryResult.do")) {
			viewPage ="categoryResult.jsp";
		}
		else if(cmd.equals("/admin/CategoryDelete.do")) {
			command = new CategoryDeleteLogic();
			command.execute(request, response);
			viewPage="CategoryResult.do";
		}
		
		//상품 관련
		else if(cmd.equals("/product/ProductList.do")) {
			command = new ProductListLogic();
			command.execute(request, response);
			viewPage="ProductListResult.do";
		}else if(cmd.equals("/product/ProductListResult.do")) {
			viewPage = "productList.jsp";
		}else if(cmd.equals("/product/ProductWriteView.do")) {
			viewPage = "productWriteView.jsp";
		}else if(cmd.equals("/product/ProductWrite.do")) {
			command= new ProductWriteLogic();
			command.execute(request, response);
			viewPage = "productResult.do";
		}else if(cmd.equals("/product/productResult.do")) {
			viewPage= "productResultView.jsp";
		}else if(cmd.equals("/product/ProductDelete.do")) {
			command = new ProductDeleteLogic();
			command.execute(request, response);
			viewPage = "productResult.do";
		}
		
		//장바구니
		else if(cmd.equals("/product/CartAdd.do")) {
			command = new CartAdd();
			command.execute(request, response);
			viewPage = "ProductList.do";
		}else if(cmd.equals("/product/cart/CartModify.do")){
			command = new CartModify();
			command.execute(request, response);
			viewPage="CartList.do";
		}
		else if(cmd.equals("/product/cart/CartList.do")) {
			command = new CartListLogic();
			command.execute(request, response);
			viewPage = "cartList.jsp";
		}
		
		else if(cmd.equals("/product/cart/CartDelete.do")) {
			command = new CartDelete();
			command.execute(request, response);
			viewPage = "CartList.do";
		}else if(cmd.equals("/product/cart/Order.do")) {
			command = new OrderPage();
			command.execute(request, response);
			viewPage="order.jsp";
		}
		else if(cmd.equals("/product/cart/OrderLogic.do")) {
			command= new OrderLogic();
			command.execute(request, response);
			viewPage="orderLogic.jsp";
		}
		
		RequestDispatcher dispat = request.getRequestDispatcher(viewPage);
		dispat.forward(request, response);
	}

}
