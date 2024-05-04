package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = createMember();

        Book book = createBook("시골 JPA", 10000, 10);

        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order getOrder = orderRepository.findone(orderId);

        assertEquals("상품 주문 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, getOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량이다.", 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어들어야 한다.", 8, book.getStockQuantity());
    }

    @Test()
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 11;

        //when
        Assertions.assertThrows(NotEnoughStockException.class,
                () ->orderService.order(member.getId(), item.getId(), orderCount));
        //then
     }


     @Test
     public void 주문취소() throws Exception {
         //given
         Member member = createMember();
         Book item = createBook("시골 JPA", 10000, 10);

         int orderCount = 2;

         Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

         //when
        orderService.cancelOrder(orderId);

         //then
         Order getOrder = orderRepository.findone(orderId);

         assertEquals("주문 취소 시 상태는 CANCEL 이다.", OrderStatus.CANCEL, getOrder.getStatus());
         assertEquals("주문이 취소된 상품은 그만큼 재고가 증가해야 한다.", 10, item.getStockQuantity());
     }
     
    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강서구", "123-123"));
        em.persist(member);
        return member;
    }

}