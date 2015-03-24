/*
 *  Ethereal Installer
 *  Copyright (C) 2015 Aesen Vismea and Falkreon
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.gameminers.system_specific;

import com.gameminers.ethereal.lib.OperatingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessList {
    public static boolean isRunning(String processName) {
        switch(OperatingSystem.getCurrentOS()) {
            case WINDOWS:
                return isRunningWindows(processName);
            default: return false;
        }
    }
    
    
    private static boolean isRunningWindows(String processName) {
        boolean result = false;
        try {
            Process p = new ProcessBuilder().command("tasklist.exe","/FO", "list", "/FI", "\"WINDOWTITLE eq "+processName+"*\"").start();
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = null;
            while( (s=in.readLine())!=null ) {
                if (s.startsWith("Image Name:")) {
                    result = true;
                }
            }
        } catch (IOException e) {
            return false;
        }
        return result;
    }
}
