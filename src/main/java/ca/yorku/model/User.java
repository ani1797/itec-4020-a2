package ca.yorku.model;

public class User {

    private String username;
    private String password;
    private Name name;

    public User() {
        this.name = null;
        this.password = null;
        this.username = null;
    }

    public User(String firstName, String lastName, String username, String password) {
        this.name = new Name(firstName, lastName);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public static class Name {
        private String first;
        private String last;

        public Name() {
            this.first = null;
            this.last = null;
        }

        public Name(String first, String last) {
            this.first = first;
            this.last = last;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }
    }

}
