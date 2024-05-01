package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    @Column(
            name = "delivery_id"
    )
    private Long id;
    @OneToOne(
            mappedBy = "delivery",
            fetch = FetchType.LAZY
    )
    private Order order;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery() {
    }

    public Long getId() {
        return this.id;
    }

    public Order getOrder() {
        return this.order;
    }

    public Address getAddress() {
        return this.address;
    }

    public DeliveryStatus getStatus() {
        return this.status;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public void setStatus(final DeliveryStatus status) {
        this.status = status;
    }
}
