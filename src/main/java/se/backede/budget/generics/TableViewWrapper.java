/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.generics;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;
import se.backede.generics.persistence.entity.GenericEntity;

/**
 *
 * @author joaki
 * @param <T>
 */
@Slf4j
public class TableViewWrapper<T extends GenericEntity> {

    private final Class<T> entityClass;
    private final JfxDao<T> dao;

    public TableViewWrapper(JfxDao<T> dao) {
        this.entityClass = dao.getEntityClass();
        this.dao = dao;
    }

    public void initializeTable(Set<T> initData, TableView<T> tableView) {

        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ShowFieldInTableView.class)) {

                try {
                    Class<?> fieldClazz = Class.forName(field.getGenericType().getTypeName());

                    if (fieldClazz.equals(String.class)) {
                        TableColumn<T, String> newColumn = new TableColumn<>(field.getName());
                        newColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));
                        tableView.getColumns().add(newColumn);
                    }

                } catch (ClassNotFoundException ex) {
                    log.error("Error when extracting fildclass for Entity of class {}", entityClass.getName(), ex);
                }

            }
        }

        ObservableList<T> tableData = FXCollections.observableArrayList(initData);
        tableView.setItems(tableData);

    }

    public void initializeDeleteListener(TableView<T> tableView) {
        tableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {

            switch (keyEvent.getCode()) {
                case DELETE:
                    T selectedItem = tableView.getSelectionModel().getSelectedItem();

                    if (dao.getInstance(entityClass).deleteEntity(selectedItem).isPresent()) {
                        tableView.getItems().remove(selectedItem);
                    }

                    break;
                default:
            }

        });
    }

}
