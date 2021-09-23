// view

package a11se2020ws.presentation;

import java.util.ArrayList;

import a11se2020ws.controller.Controller;
import a11se2020ws.model.Book;
import a11se2020ws.model.BookManager;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Acts as Graphical User Interface which displays all made changes to an
 * external list of custom objects
 **/
public class MainFX extends Application implements Observer {

    ListView<Book> listView;
    ObservableList<Book> myList;

    BookManager bm = new BookManager();
    
    BorderPane root = new BorderPane();

    @Override
    public void start(final Stage stage) throws Exception {
        Controller controller = new Controller(bm);
        controller.getModel().setObserver(this);

        myList = FXCollections.observableArrayList();
        listView = new ListView<>(myList);

        /**
         * add a new book to the list, cancels insert if a book with same isbn exists
         * already in list
         **/
        Button cmdAdd = new Button("Add");
        cmdAdd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("Enter Book Info");
                dialog.setTitle("Book Entry");

                TextField title = new TextField();
                title.setPromptText("Title");

                TextField author = new TextField();
                author.setPromptText("Author");

                TextField year = new TextField();
                year.setPromptText("Year");

                TextField isbn = new TextField();
                isbn.setPromptText("ISBN");

                GridPane grid = new GridPane();
                grid.add(title, 1, 1);
                grid.add(author, 1, 2);
                grid.add(year, 1, 3);
                grid.add(isbn, 1, 4);
                dialog.getDialogPane().setContent(grid);

                // handles cancel
                if (dialog.showAndWait().orElse(null) == null) {
                    return;
                }

                String t, a, i;
                int y = 0;
                t = title.getText();
                a = author.getText();
                i = isbn.getText();
                
                // checks for valid inputs
                if (t.equals("") || a.equals("") || i.equals("")) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Add-Error:");
                    alert.setContentText("All fields must be filled out!");
                    alert.showAndWait();
                    return;
                }

                // checks for valid inputs
                try {
                    y = Integer.parseInt(year.getText());
                } catch (NumberFormatException nfe) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Add-Error:");
                    alert.setContentText("Invalid year info");
                    alert.showAndWait();
                    return;
                }

                // check for duplicate isbn
                if (!controller.getModel().addBook(t, a, y, i)) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Add-Error:");
                    alert.setContentText("This book already exists!");
                    alert.showAndWait();
                }
            }
        });

        /**
         * edit all attributes of selected book, prevents change to already existant
         * isbn
         **/
        Button cmdEdit = new Button("Edit");
        cmdEdit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setHeaderText("Change Book Attributes");
                dialog.setTitle("Edit Book");

                // create dummy book of selected one to edit, closes edit if no book is selected
                if (MainFX.this.listView.getSelectionModel().getSelectedItem() == null) {
                    return;
                }
                Book temp = MainFX.this.listView.getSelectionModel().getSelectedItem();
                String prevISBN = temp.getISBN();

                // Pre-fill TextFields with known info about book
                TextField title = new TextField(temp.getTitle());
                TextField author = new TextField(temp.getAuthor());
                TextField year = new TextField(String.valueOf(temp.getYear()));
                TextField isbn = new TextField(temp.getISBN());

                GridPane grid = new GridPane();
                grid.add(title, 1, 1);
                grid.add(author, 1, 2);
                grid.add(year, 1, 3);
                grid.add(isbn, 1, 4);
                dialog.getDialogPane().setContent(grid);

                // handles cancel
                if (dialog.showAndWait().orElse(null) == null)  return;

                // if isbn doesnt change, avoid cancellation
                if (prevISBN.equals(isbn.getText())) {

                    // if new set isbn exists alread in list, abort changes
                } else if (controller.getModel().contains(isbn.getText())) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText("Edit-Error:");
                    alert.setContentText("Book with same ISBN already exists!");
                    alert.showAndWait();
                    return;
                }

                // finalize new attributes
                temp.setTitle(title.getText());
                temp.setAuthor(author.getText());
                temp.setYear(Integer.parseInt(year.getText()));
                temp.setISBN(isbn.getText());

                update(controller.getModel().getBookList());
            }
        });

        /** Deletes selected item from list **/
        Button cmdRemove = new Button("Remove");
        cmdRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                final int index = MainFX.this.listView.getSelectionModel().getSelectedIndex();

                if (index != -1){
                    controller.getModel().getBookList().remove(index);
                    update(controller.getModel().getBookList());
                }
            }

        });

        ToolBar toolBar = new ToolBar(cmdAdd, cmdEdit, cmdRemove);
        root.setTop(toolBar);
        root.setCenter(listView);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void update(ArrayList<Book> books) {
        myList = FXCollections.observableArrayList(books);
        listView = new ListView<>();
        listView.setItems(myList);
        root.setCenter(listView);
    }
}
