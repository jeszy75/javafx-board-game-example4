package boardgame.util;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class EnumImageStorage<T extends Enum> implements ImageStorage<T> {

    private Map<T, Image> map = new HashMap<>();

    public EnumImageStorage(Class<T> enumClass) {
        var path = enumClass.getPackage().getName().replace(".", "/");
        for (var constant : enumClass.getEnumConstants()) {
            var url = String.format("%s/%s.png", path, constant.name().toLowerCase());
            try {
                map.put(constant, new Image(url));
            } catch (Exception e) {
                // Failed to load image
            }
        }
    }

    @Override
    public Image get(T constant) {
        return map.get(constant);
    }

}
