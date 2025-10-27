package model;

public interface IRoomBuilder {

    void setTheme(Theme theme);
    void addDecorations(Decoration decoration);
    void addClues(Clue hint);
    Room createRoom();
}