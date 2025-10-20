package model;

public interface Notifier {

    void registerPlayer(Player player);
    void removePlayer(Player player);
    void notifySubscribers();
}