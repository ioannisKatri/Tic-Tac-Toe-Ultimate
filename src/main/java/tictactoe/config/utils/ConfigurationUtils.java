package tictactoe.config.utils;

import static tictactoe.config.utils.CommonValues.FILE_KEY_VALUE_SEPARATOR;

public class ConfigurationUtils {

    public static String extractValue(String str, String expects) {
        if (str == null) {
            throw new IllegalArgumentException(expects + " is missing please check documentation");
        }

        String[] arr = str.split(FILE_KEY_VALUE_SEPARATOR);
        if (!arr[0].equals(expects)) {
            throw new IllegalArgumentException(expects + " is missing or in incorrect order please check documentation");
        }
        return arr[1].trim();
    }

    public static String extractValue(String str) {
        String[] arr = str.split(FILE_KEY_VALUE_SEPARATOR);
        return arr[1].trim();
    }

}
