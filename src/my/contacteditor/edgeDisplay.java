/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.contacteditor;

import java.awt.Point;

/**
 *
 * @author Ochaun Marshall
 */
class edgeDisplay {
    Point lhs; // leftmost point in display
    Point rhs; // rightmost point in display
    
    edgeDisplay(int x_1, int y_1, int x_2, int y_2){
       lhs = new Point(x_1,y_1);
       rhs = new Point(x_2,y_2);
    }
    edgeDisplay(Point one, Point two){
        lhs = new Point(one);
        rhs = new Point(two);
    }

}
