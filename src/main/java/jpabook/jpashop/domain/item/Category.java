package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.mapping.FetchProfile;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<Category>();

    //== 연관관계 메소드 ==//
    public void addChildCategory(Category child){
        this.child.add(child);
        child.setParent(this);
    }
}
