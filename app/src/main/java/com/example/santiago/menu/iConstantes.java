package com.example.santiago.menu;

/**
 * Created by Franco on 13/12/2016.
 */

public interface iConstantes {

    char START_CMD_CHAR = '*';
    char DIV_CMD_CHAR = '|';
    char END_CMD_CHAR = '#';

    int CMD_DIGITALWRITE = 10;
    int CMD_ANALOGWRITE = 11;
    int CMD_TEXT = 12;
    int CMD_READ_DIGITAL = 13;
    int CMD_MOVE_SERVO = 14;

    int PIN_CONTRAST = 2;
    int PIN_SERVO = 9;
    int PIN_LED1 = 46;
    int PIN_LED2 = 47;
    int PIN_LED3 = 48;
    int PIN_LED4 = 49;
    int PIN_SWITCH1 = 30;
    int PIN_SWITCH2 = 31;
    int PIN_SWITCH3 = 32;
    int PIN_SWITCH4 = 33;

    int  REQUEST_ENABLE_BT = 1;
    int  REQUEST_CONECTION_BT = 2;
}
