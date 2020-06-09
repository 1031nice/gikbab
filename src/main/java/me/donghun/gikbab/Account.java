package me.donghun.gikbab;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class Account {

    // 한 사람마다 '취향'을 나타내는 객체를 가지고 있게 하는건 어떨까

   @Id @GeneratedValue
    private Long id;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
