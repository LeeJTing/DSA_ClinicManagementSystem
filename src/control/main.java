/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import boundary.menu;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Lwin
 */
public class main {

    public static void main(String[] args) {
        final Master MASTER = new Master();
        MASTER.initializer();
        
        menu.mainMenu();
    }
}
