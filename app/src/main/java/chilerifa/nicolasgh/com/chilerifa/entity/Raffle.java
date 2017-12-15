package chilerifa.nicolasgh.com.chilerifa.entity;

import java.io.Serializable;

/**
 * Created by Hamzo on 15-12-2017.
 */

public class Raffle implements Serializable {
    private Integer id;
    private String name;
    private String Description;
    private Integer cost;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}
