package boardgame.util;

import javafx.scene.image.Image;

import java.lang.System.Logger;
import java.util.HashMap;
import java.util.Map;

public class EnumImageStorage<T extends Enum<?>> implements ImageStorage<T> {

    private static final Logger logger = System.getLogger(EnumImageStorage.class.getName());

    private final Map<T, Image> map = new HashMap<>();

    public EnumImageStorage(Class<T> enumClass) {
        var path = enumClass.getPackage().getName().replace(".", "/");
        for (var constant : enumClass.getEnumConstants()) {
            var url = String.format("%s/%s.png", path, constant.name().toLowerCase());
            try {
                map.put(constant, new Image(url));
                logger.log(Logger.Level.INFO, "Loaded image from {0}", url);
            } catch (Exception e) {
                // Failed to load image
                logger.log(Logger.Level.WARNING, "Failed to load image from {0}", url);
            }
        }
    }

    @Override
    public Image get(T constant) {
        return map.get(constant);
    }

}
