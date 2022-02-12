package com.cookwarestore.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import com.cookwarestore.model.Cookware;
import com.cookwarestore.model.Order;
import com.cookwarestore.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DB {
    private List<Cookware> cookwares = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public DB() {
        this.cookwares.add(
                new Cookware(1,
                        "Lodge Chef Collection Seasoned Cast Iron Skillet",
                        "USA",
                        29.95,
                        "370-537831-651-3",
                        15));

        this.cookwares.add(new Cookware(2,
                "Cuisinart Custom-Clad 5-Ply Stainless-Steel Saucepan",
                "China",
                39.95,
                "780-555392-641-4",
                10));

        this.cookwares.add(new Cookware(3,
                "Lodge Chef Collection Seasoned Cast Iron Double Handled Square Griddle, 11",
                "USA",
                37.95,
                "370-231889-573-2",
                10));

        this.cookwares.add(new Cookware(4,
                "Mauviel Copper Sugar Pot",
                "France",
                97.90,
                "240-250534-521-7",
                5));

        this.users.add(new User(1, "Maksym", "Velykozhon", "admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User(2, "Jan", "Wisniewski", "user", DigestUtils.md5Hex("user")));
    }

    public List<Cookware> getCookwares() {
        return cookwares;
    }

    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<Cookware> getCookwareById(int cookwareId) {
        for(Cookware cookware : this.cookwares) {
            if(cookware.getId() == cookwareId) {
                return Optional.of(cookware);
            }
        }

        return Optional.empty();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getId() == userId) {
                result.add(order);
            }
        }

        return result;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}