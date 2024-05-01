package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(
            name = "member_id"
    )
    private long id;
    private String name;
    @Embedded
    private Address address;
    @OneToMany(
            mappedBy = "member"
    )
    private List<Order> orders = new ArrayList();

    public Member() {
    }

    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public void setOrders(final List<Order> orders) {
        this.orders = orders;
    }
}
