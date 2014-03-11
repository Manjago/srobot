package srobot;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;

public class IsRunning {
    public static void main(String[] args) {
        HWND hwnd = User32.INSTANCE.FindWindow
                (null, "Сапер");
        if (hwnd == null) {
            System.out.println("Sapper is not running");
        }
        else{
            User32.INSTANCE.ShowWindow(hwnd, 9);
            User32.INSTANCE.SetForegroundWindow(hwnd);
            WinDef.RECT rect = new WinDef.RECT();
            User32.INSTANCE.GetWindowRect(hwnd, rect);
            System.out.println(rect);

        }
    }
}
