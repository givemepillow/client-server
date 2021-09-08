package dev.kirilllapushinskiy.utils;

import dev.kirilllapushinskiy.communication.*;
import dev.kirilllapushinskiy.core.AppServer;

public class Searcher {

    public static Integer searchByID(Session session) {
        if (AppServer.humanBeings.size() == 0) {
            session.setMessage(new FinishMessage("В коллекции нет объектов! Поиск по идентификатору невозможен!"));
            return null;
        }
        int id;
        try {
            String text = session.getArgs().get(0);
            id = Integer.parseInt(text);

            if (id < 0) {
                throw new IllegalArgumentException("Идентификатор не может быть отрицательным!");
            }
            Integer finalId = id;
            if (AppServer.humanBeings.stream().noneMatch((humanBeing -> humanBeing.getId().equals(finalId)))) {
                session.setMessage(new FinishMessage("Такого идентификатора не существует!"));
                return null;
            }

        } catch (NumberFormatException exception) {
            session.setMessage(new FinishMessage("Некорректный идентификатор!"));
            return null;
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
        return id;
    }
}
