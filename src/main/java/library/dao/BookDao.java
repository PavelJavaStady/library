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

    public void save(Book book) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Book (`name`, `author`, `dateOfPresent`) VALUES (?,?,?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getDateOfPresent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Book updateBook) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Book SET name=?, author=?, dateOfPresent=?  WHERE id=?");

            preparedStatement.setString(1, updateBook.getName());
            preparedStatement.setString(2, updateBook.getAuthor());
            preparedStatement.setInt(3, updateBook.getDateOfPresent());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteId(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Book WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void assign(int id,Person selectedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Book SET personId=? WHERE id=?");

            preparedStatement.setInt(1, selectedPerson.getId());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
