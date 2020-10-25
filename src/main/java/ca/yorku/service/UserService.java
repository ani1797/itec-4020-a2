package ca.yorku.service;

import ca.yorku.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static java.util.Collections.emptyList;

public class UserService {

    private static final ObjectMapper mapper = new ObjectMapper();

    public Collection<User> addUser(User user) {
        Collection<User> users = getUsers();
        users.add(user);
        System.out.printf("Written user data: %s%n", this.write(users));
        return users;
    }

    public User getUser(String username) {
        return getUsers().stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username)).findFirst().get();
    }

    public boolean hasUser(String username) {
        return getUsers().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username));
    }

    public boolean isAuthenticated(String username, String password) {
        return getUsers().stream()
                .anyMatch(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password));
    }

    public Collection<User> getUsers() {
        try (InputStream stream = UserService.class.getClassLoader().getResourceAsStream("users.json")) {
            return mapper.readValue(stream, mapper.getTypeFactory().constructCollectionType(Collection.class, User.class));
        } catch (IOException ex) {
            System.out.println("There was an issue reading the data file and was unable to retrieve user list.");
            return emptyList();
        }
    }

    private boolean write(Collection<User> users) {
        final String filePath = UserService.class.getClassLoader().getResource("users.json").getFile();
        try (FileOutputStream out = new FileOutputStream(filePath)) {
            out.write(mapper.writeValueAsBytes(users));
            out.flush();
            return true;
        } catch (IOException ex) {
            System.out.println("There was an issue writing the data file");
            return false;
        }
    }

}
