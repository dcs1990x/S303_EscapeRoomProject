package model;

public interface NotifiedPlayer {

    void readNotification(Notification notification);
    void subscribe(SubscribersManager subscribersManager);
    void unsubscribe(SubscribersManager subscribersManager);
}