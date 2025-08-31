module TVApp {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;
    requires spring.data.jpa;
    requires spring.beans;
    exports org.curs.AppServer;
    exports org.curs.AppClient;
    opens org.curs.AppServer to spring.core, spring.beans, spring.context;
    opens org.curs.AppClient to spring.core, spring.beans, spring.context, javafx.base, javafx.graphics;
}