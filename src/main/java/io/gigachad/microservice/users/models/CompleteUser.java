package io.gigachad.microservice.users.models;

public class CompleteUser {
    private MSUser user;
    private String panNumber;

    public CompleteUser() {

    }
    public CompleteUser(MSUser user, String panNumber) {
        this.user = user;
        this.panNumber = panNumber;
    }

    public MSUser getUser() {
        return user;
    }

    public void setUser(MSUser user) {
        this.user = user;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    @Override
    public String toString() {
        return "CompleteUser{" +
                "user=" + user +
                ", panNumber='" + panNumber + '\'' +
                '}';
    }
}
