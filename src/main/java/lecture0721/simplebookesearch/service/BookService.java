package lecture0721.simplebookesearch.service;

import lecture0721.simplebookesearch.dao.BookDAO;
import lecture0721.simplebookesearch.vo.BookVo;

import java.util.ArrayList;

public class BookService { //Service 역할 조회,삭제 -> 하나의 트랙잭션

    private BookDAO dao;
    public BookService() {
        this.dao = new BookDAO();
    }

    public ArrayList<BookVo> bookSearchByKeyword(String keyword){
        // 키워드를 이용해서 책을 찾는다.
        // for, if 로직처리가 일반적으로 나오는데
        // DataBase 처리를 해야함
        ArrayList<BookVo> list = dao.select(keyword);
        return list;

    }
    public String bookDeleteByISBN(String bisbn){
        // 책 고유번호를 받아서 해당 책을 삭제하는 로직
        return dao.delete(bisbn);
    }
}
