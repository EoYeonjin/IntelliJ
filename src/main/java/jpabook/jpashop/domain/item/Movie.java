package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {
    private String director;
    private String actor;

    public Movie() {
    }

    public String getDirector() {
        return this.director;
    }

    public String getActor() {
        return this.actor;
    }

    public void setDirector(final String director) {
        this.director = director;
    }

    public void setActor(final String actor) {
        this.actor = actor;
    }
}
