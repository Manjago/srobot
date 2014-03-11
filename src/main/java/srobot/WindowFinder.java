package srobot;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;

public final class WindowFinder {
    private WindowFinder() {
    }

    public static SimpleRectangle activateAndFind(String windowName){
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow
                (null, windowName);
        if (hwnd == null) {
            return null;
        }
        else{
            User32.INSTANCE.ShowWindow(hwnd, 9);
            User32.INSTANCE.SetForegroundWindow(hwnd);
            WinDef.RECT rect = new WinDef.RECT();
            User32.INSTANCE.GetWindowRect(hwnd, rect);
            return new SimpleRectangle(new SimplePoint(rect.left, rect.top), rect.right - rect.left, rect.bottom - rect.top);
        }

    }
}
