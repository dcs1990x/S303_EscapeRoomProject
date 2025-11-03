package dtomodel;

import model.Clue;
import model.Difficulty;
import model.Item;
import model.Theme;

import java.util.List;

public record RoomDTO(String name, Difficulty difficulty, Theme theme, List<Long> itemsId, List<Long> cluesId) {}
