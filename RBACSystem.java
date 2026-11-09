/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbacsystem;

/**
 *
 * @author Pc World
 */
import java.util.*;

public class RBACSystem {


    static Map<String, Set<String>> rolePermissions = new HashMap<>();
    static Map<String, String> userRoles = new HashMap<>();

  
    public static void setupRoles() {

        rolePermissions.put("Admin",
            new HashSet<>(Arrays.asList(
                "Create", "View", "Update", "Delete"
            ))
        );

        rolePermissions.put("Manager",
            new HashSet<>(Arrays.asList(
                "View", "Update"
            ))
        );

        rolePermissions.put("Customer",
            new HashSet<>(Arrays.asList(
                "View"
            ))
        );
    }

    public static void assignRole(String user, String role) {

        if (rolePermissions.containsKey(role)) {
            userRoles.put(user, role);
            System.out.println(user + " assigned to " + role);
        } else {
            System.out.println("Invalid role!");
        }
    }

    public static boolean hasPermission(
        String user, String permission) {

        String role = userRoles.get(user);

        if (role == null) {
            return false;
        }

        return rolePermissions.get(role).contains(permission);
    }

    public static void accessAccount(
        String user, String permission) {

        if (hasPermission(user, permission)) {
            System.out.println(user + " can " + permission
                + " Account.");
        } else {
            System.out.println(user + " cannot " + permission
                + " Account. Access Denied!");
        }
    }

    public static void main(String[] args) {

        setupRoles();

        assignRole("Ali", "Admin");
        assignRole("Ahmad", "Manager");
        assignRole("Sara", "Customer");

        System.out.println("\n--- Account Access Tests ---");

        accessAccount("Ali", "Delete");
        accessAccount("Ahmad", "Update");
        accessAccount("Sara", "View");
        accessAccount("Sara", "Delete");
    }
}