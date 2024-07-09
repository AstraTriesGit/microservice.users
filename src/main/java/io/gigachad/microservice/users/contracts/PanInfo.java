package io.gigachad.microservice.users.contracts;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Repository;

/**
 * Payload! <br>
 * Objects of this class contain the userId and the corresponding PAN number.
 * Such info can only be requested by microservice.users.
 */
@Repository
@Entity
public class PanInfo {
    @Id
    int userId;
    String panNumber;

    public PanInfo() {

    }

    public PanInfo(int userId, String panNumber) {
        super();
        this.userId = userId;
        this.panNumber = panNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Override
    public String toString() {
        return "PanInfo{" +
                "userId=" + userId +
                ", panNumber='" + panNumber + '\'' +
                '}';
    }
}

