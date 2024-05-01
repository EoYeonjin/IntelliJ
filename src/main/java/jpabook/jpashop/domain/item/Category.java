package jpabook.jpashop.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue
    @Column(
            name = "category_id"
    )
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "category_item",
            joinColumns = {@JoinColumn(
                    name = "category_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "item_id"
            )}
    )
    private List<Item> items = new ArrayList();
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "parent_id"
    )
    private Category parent;
    @OneToMany(
            mappedBy = "parent"
    )
    private List<Category> child = new ArrayList();

    public Category() {
    }

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public List<Item> getItems() {
        return this.items;
    }

    public Category getParent() {
        return this.parent;
    }

    public List<Category> getChild() {
        return this.child;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public void setParent(final Category parent) {
        this.parent = parent;
    }

    public void setChild(final List<Category> child) {
        this.child = child;
    }
}
