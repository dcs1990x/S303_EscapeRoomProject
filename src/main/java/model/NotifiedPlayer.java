package model;

public interface NotifiedPlayer {

    void readNotification(Notification notification);
    void subscribe();
    void unsubscribe();
}