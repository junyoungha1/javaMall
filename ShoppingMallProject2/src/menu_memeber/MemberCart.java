package menu_memeber;

import java.util.ArrayList;

import _mall.MenuCommand;
import cart.Cart;
import cart.CartDAO;
import controller.MallController;
import util.Util;

public class MemberCart implements MenuCommand {
	private MallController mallCont;
	private CartDAO cdao;

	@Override
	public void init() {
		mallCont = MallController.getInstance();
		cdao = CartDAO.getInstance();
	}

	@Override
	public boolean update() {
		System.out.println("=====[ 장바구니 ]=====");
		ArrayList<Cart> myCartList = cdao.getMyCartList(mallCont.getLoginId());
		cdao.printMyCartList(myCartList);
		System.out.println("[1] 결제하기 [2] 상품취소 [3] 전체상품취소 [0] 뒤로가기");
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if (sel == 0) {
			mallCont.setNext("MemberMain");
		}
		if (sel == 1) {
			payItem();
		} else if (sel == 2) {
			cancelItem();
		} else if (sel == 3) {
			cdao.clearMyCart(mallCont.getLoginId());
			System.out.println("장바구니의 모든 상품이 삭제되었습니다.");
		}
		return false;
	}

	void payItem() {
		ArrayList<Cart> myCartList = cdao.getMyCartList(mallCont.getLoginId());
		if (myCartList.size() == 0) {
			System.err.println("장바구니에 상품이 없습니다");
			return;
		}
		int sel = Util.getValue("결제 상품 선택", 0, myCartList.size()) - 1;
		int price = Util.getValue("금액 입력", 100, 999999);
		if (price < myCartList.get(sel).getPrice()) {
			System.err.println("금액이 부족합니다");
		} else {
			int cartIdx = cdao.getItemIdx(myCartList.get(sel).getItemName(), cdao.getCartList());
			cdao.deleteCart(cartIdx);
			System.out.println("결제가 완료되었습니다");
			System.out.println("=====[영수증]=====");
			System.out.println("결제 상품 : " + myCartList.get(cartIdx).getItemName());
			System.out.println("상품 금액 : " + myCartList.get(cartIdx).getPrice());
			System.out.println("지불 금액 : " + price);
			System.out.println("잔여 금액 : " + (price - myCartList.get(cartIdx).getPrice()));
		}
	}

	void cancelItem() {
		ArrayList<Cart> myCartList = cdao.getMyCartList(mallCont.getLoginId());
		int sel = Util.getValue("취소할 상품 선택", 0, myCartList.size());
		int cartIdx = cdao.getItemIdx(myCartList.get(--sel).getItemName(), cdao.getCartList());
		cdao.deleteCart(cartIdx);
		System.out.println(myCartList.get(cartIdx).getItemName() + "이 삭제되었습니다");
	}

}
