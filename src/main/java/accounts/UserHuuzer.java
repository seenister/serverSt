package accounts;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity                                                 //Почему импорт hibernate библиотеки зачеркивает эту аннотацию?
@Table(name = "users")
public class UserHuuzer {                               //хз нужен ли так Serilzable для Hibernate

    @Id
    @Column
    private String login;

    @Column
    private String pass;

    public UserHuuzer() {
    }

    public UserHuuzer(String login, String pass) {
        this.login = login;
        this.pass = pass;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;

    }


    public String getLogin() {
        return login;
    }

    public String getPass() {
        return pass;
    }


}


