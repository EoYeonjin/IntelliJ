package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.item.Item;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(
            name = "order_item_id"
    )
    private Long id;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "item_id"
    )
    private Item item;
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "order_id"
    )
    private Order order;
    private int orderPrice;
    private int count;

    public OrderItem() {
    }

    public Long getId() {
        return this.id;
    }

    public Item getItem() {
        return this.item;
    }

    public Order getOrder() {
        return this.order;
    }

    public int getOrderPrice() {
        return this.orderPrice;
    }

    public int getCount() {
        return this.count;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public void setOrderPrice(final int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setCount(final int count) {
        this.count = count;
    }
}
