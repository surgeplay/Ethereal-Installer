package com.gameminers.ethereal.installer;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import com.gameminers.ethereal.lib.Resources;

public class ImageURL {
    public static String create(BufferedImage image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        try {
            ImageIO.write(image, "PNG", stream);
            stream.close();
            return "data:image/png;base64,"+DatatypeConverter.printBase64Binary(stream.toByteArray());
        } catch (IOException ex) {
            return "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAABmJLR0QA/wD/AP+gvaeTAAAACXBIWXMAAAs"+
                   "TAAALEwEAmpwYAAAAB3RJTUUH1QIKEjQLqADJWQAAAlhJREFUOMt9k9FLU2EYxn/nbGvbwW0HOtLCxWJOGAihCF6IRLuovNEKkd30F3jr"+
                   "MPwf7HpSN0ESrhvrMgqFKAUNhKiIOXfjkR3b1mLipnLO93VxTF2NPnjgex++93kf3o9HocNZB0PAqANJAdhQFPDhDlT/fqtcLDbAkDDvg"+
                   "0woHg8E4nEkcGhZ/CgUjhqQF5C910GITUh9gt29oSFpLy9LaZptcFZWZGlsTL6G3ZeQanOwAYYKW73j4zE9mwW/H2y7fYLXC7ZNbXGR1Y"+
                   "UF8xgGH0JVBZAwfzWRiOmTkxAKwcAA1OtQKrmo111O07g8MsKN4eGYgHkAdR0MH2SujI5CuQzJpOsgnYbDQxfptMslk8hCgeuGQRAyT8F"+
                   "QPsL9br9/uS+Tca1GozA3B7oOjYbLhcNIy+JoYgJ7cxOAz8AXeKA6kAwGAud219ZgehpqNQiHz5qb6fRZM0AX4EDS6wCy1YKdnfOFeTwg"+
                   "BP/7c+kKoDpQbJ6cIPf3Xeg6LC1BdzfSspCWhRKNoq2ucimVwgf4gIYrUOQNGO+hJbxeKSIRKctlKaWU0rKk7O93YVkuVy5LEYlI4fXKZ"+
                   "9B6DIZ6F6q/IG9qGkowCLkcVCowNeXuoVZz75UK5HIowSDfNI0K5GegqgC8AkNR1a2biURM1zTQNGg22xdwylWaTV6USuaJEIOzfwQA8p"+
                   "BSPZ63g319sd5YrFPG+GqavNveNo8d5/Yj+P5PmJ6D4cB8VyiUudbTE4iEwwjgZ6NBcW/vqHJwkJeQnb0QJqXTpCegC7hln8bZOY3zTIc"+
                   "U/gbWdhZxsMASegAAAABJRU5ErkJggg==";
        }
    }
    
    public static BufferedImage decode(String dataURL) {
        if (dataURL.startsWith("data:image/png;base64,")) {
            String b64data = dataURL.substring(22);
            byte[] decoded = DatatypeConverter.parseBase64Binary(b64data);
            ByteArrayInputStream stream = new ByteArrayInputStream(decoded);
            try {
                return ImageIO.read(stream);
            } catch (Exception ex) {}
        }
        
        return Resources.getPNGAsset("iface/error-32");
    }
}
