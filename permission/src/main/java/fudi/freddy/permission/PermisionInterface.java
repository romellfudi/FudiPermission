package fudi.freddy.permission;

import java.util.ArrayList;

/**
 *
 * @author Romell Domínguez
 * @version 1.0.a 16/07/2018
 * @since 1.0
 */

public interface PermisionInterface {
    void onRefuse(ArrayList<String> RefusePermissions);
    void onFinally();
}
