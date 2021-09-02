package dev.kirilllapushinskiy.utils;

import dev.kirilllapushinskiy.communication.*;
import dev.kirilllapushinskiy.core.AppServer;



public class Searcher {

    public static Integer searchByID(Session session) {
        session.upState();
        if (AppServer.humanBeings.size() == 0) {
            session.setMessage(new FinishMessage("В коллекции нет объектов! Поиск по идентификатору невозможен!"));
            return null;
        }
        int id;
        while (true) {
            try {
                session.setMessage(new PromptMessage("Удаление элемента коллекции. \nВведите идентификатор:"));
                String text = session.getResponse();
                id = Integer.parseInt(text);
                if (id < 0) {
                    throw new IllegalArgumentException("Идентификатор не может быть отрицательным!");
                }
                Integer finalId = id;
                if (AppServer.humanBeings.stream().noneMatch((humanBeing -> humanBeing.getId().equals(finalId)))) {
                    session.setMessage(new FinishMessage("Такого идентификатора не существует!"));
                    continue;
                }

                break;
            } catch (NumberFormatException exception) {
                session.setMessage(new ErrorMessage("Некорректный идентификатор!"));
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }

        return id;
    }
}
