package jpabook.model.entity.item;

import jpabook.model.entity.BaseEntity;
import jpabook.model.entity.ItemCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


    @OneToMany(mappedBy = "item")
    private final List<ItemCategory> itemCategories = new ArrayList<ItemCategory>();

    public void addItemCategories(ItemCategory itemCategory){
        itemCategories.add(itemCategory);
        itemCategory.setItem(this);
    }

    public List<ItemCategory> getItemCategories() {
        return itemCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}