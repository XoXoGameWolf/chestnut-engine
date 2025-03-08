package org.wolf.chestnut.util;

import org.wolf.chestnut.vector.IVec2;
import org.wolf.chestnut.graphics.Texture;
import org.wolf.chestnut.comp.Renderer;

import org.lwjgl.system.MemoryUtil;
import de.matthiasmann.twl.utils.PNGDecoder;

import java.lang.StringBuilder;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Exception;
import java.nio.ByteBuffer;
import java.io.IOException;

public class IO {
    public static String read(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            InputStream stream = IO.class.getClassLoader().getResourceAsStream(path);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

            String line = reader.readLine();

            while(line != null) {
                builder.append(line);
                line = reader.readLine();
                if(line != null) builder.append("\n");
            }
            
        } catch(Exception e) {
            Logger.log("File \"" + path + "\" could not be read!");
            System.exit(-1);
        }

        return builder.toString();
    }

    public static Texture readImage(Renderer renderer, String path) {
        try {
            PNGDecoder decoder = new PNGDecoder(IO.class.getClassLoader().getResourceAsStream(path));
            
            ByteBuffer buf = MemoryUtil.memAlloc(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buf, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buf.flip();

            Texture texture = new Texture(renderer, buf, new IVec2(decoder.getWidth(), decoder.getHeight()));

            /*for(int i = 0; i < 4 * decoder.getWidth() * decoder.getHeight(); i++) {
                if(buf.get(i) != 0) {
                    System.out.println(buf.get(i));
                }
            }

            System.exit(0);*/

            return texture;

        } catch(IOException e) {
            Logger.log("Image \"" + path + "\" could not be read!");
            System.exit(-1);
        }

        return null;
    }
}