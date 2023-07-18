/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package se.backede.budget.generics;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    TableView<T> tableView;

    public TableViewWrapper(TableView<T> tableView, JfxDao<T> dao) {
        this.entityClass = dao.getEntityClass();
        this.dao = dao;
        this.tableView = tableView;
        initializeTable();
    }

    private void initializeTable() {

        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(ShowFieldInTableView.class)) {

                try {
                    Class<?> fieldClazz = Class.forName(field.getGenericType().getTypeName());

                    if (fieldClazz.equals(String.class)) {
                        tableView.getColumns().add(createStringColumn(field));
                    }

                } catch (ClassNotFoundException ex) {
                    log.error("Error when extracting fildclass for Entity of class {}", entityClass.getName(), ex);
                }

            }
        }

        dao.getAll().ifPresent(data -> {
            ObservableList<T> tableData = FXCollections.observableArrayList(data);
            tableView.setItems(tableData);
        });

    }

    private TableColumn<T, String> createStringColumn(Field field) {
        TableColumn<T, String> newColumn = new TableColumn<>(StringUtils.capitalize(field.getName()));
        newColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        newColumn.setCellValueFactory(new PropertyValueFactory<>(field.getName()));

        newColumn.setOnEditCommit(new EventHandler<CellEditEvent<T, String>>() {
            @Override
            public void handle(CellEditEvent<T, String> t) {

                T entity = (T) t.getTableView().getItems().get(t.getTablePosition().getRow());

                try {
                    t.getTableColumn().getText();
                    String capitalize = StringUtils.capitalize(t.getTableColumn().getText());
                    String methodCall = "set".concat(capitalize);
                    Method declaredMethod = entity.getClass().getDeclaredMethod(methodCall, String.class);

                    declaredMethod.invoke(entity, t.getNewValue());

                    dao.updateEntity(entity);

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    log.error("Error when initializing TableView Error {}", ex);
                }
            }
        });
        return newColumn;
    }

    public void initializeDeleteListener() {

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

    public void initializeUpdateOnEnterListener() {
        tableView.setEditable(true);

        tableView.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent keyEvent) -> {

            switch (keyEvent.getCode()) {
                case ENTER:
                    T selectedItem = tableView.getSelectionModel().getSelectedItem();

                    if (dao.getInstance(entityClass).updateEntity(selectedItem).isPresent()) {
                    }

                    break;
                default:
            }

        });
    }

}
