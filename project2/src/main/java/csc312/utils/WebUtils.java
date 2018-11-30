package main.java.csc312.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils
{
    public static int getIntParamChecked(HttpServletRequest req, String param)
    {
        try
        {
            return Integer.parseInt(req.getParameter(param));
        }
        catch (NumberFormatException ignored)
        {
            return -1;
        }
    }
}
