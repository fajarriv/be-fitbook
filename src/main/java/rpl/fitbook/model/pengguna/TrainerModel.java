package rpl.fitbook.model.pengguna;

import jakarta.persistence.*;

@Entity
public class TrainerModel extends PenggunaModel {
    private String bio;
    private Float rating;
    private Float totalRating = 0.0f;
    private Integer reviewCount = 0;

    // Constructors, getters, and setters

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Float getTotalRating() {
        return totalRating;
    }

    public void setTotalRating(Float totalRating) {
        this.totalRating = totalRating;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    // Additional logic for adding ratings
    public void addRating(Float newRating) {
        this.totalRating += newRating;
        this.reviewCount++;
        this.rating = this.totalRating / this.reviewCount;
    }
}
