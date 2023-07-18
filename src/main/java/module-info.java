module se.backede.budget {
    
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    
    requires org.slf4j;
    
    requires se.backede.generics.persistence;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;

    opens se.backede.budget to javafx.fxml;
    opens se.backede.budget.login to javafx.fxml;
    
    opens se.backede.budget.user to org.hibernate.orm.core;
          
    exports se.backede.budget;
    exports se.backede.budget.user;
    
}
