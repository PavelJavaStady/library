package library.dao;

import library.model.Book;
import library.model.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookDao {
    private static final String URL = "jdbc:mysql://localhost:3306/project_1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pasha1989";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> index() {
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Book";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setDateOfPresent(resultSet.getInt("dateOfPresent"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public Book show(int id) {
        Book book = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Book WHERE id=?");
            preparedStatement.setInt(1, id);
            book = new Book();
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            book.setAuthor(resultSet.getString("author"));
            book.setDateOfPresent(resultSet.getInt("dateOfPresent"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
