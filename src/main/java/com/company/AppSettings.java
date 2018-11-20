package com.company;

public class AppSettings {
    public static int WindowWidth = 800;
    public static int WindowHeight = 600;
    public static int ImgScale = 32;
    public static int MaxRabbits = 32;
    public static int Padding = ImgScale;

    public static int getWidthPadding()
    {
        return WindowWidth - Padding;
    }

    public static int getHeightPadding()
    {
        return WindowHeight - Padding;
    }
}
