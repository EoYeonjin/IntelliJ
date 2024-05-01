package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
    private String etc;

    public Album() {
    }

    public String getArtist() {
        return this.artist;
    }

    public String getEtc() {
        return this.etc;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }

    public void setEtc(final String etc) {
        this.etc = etc;
    }
}
