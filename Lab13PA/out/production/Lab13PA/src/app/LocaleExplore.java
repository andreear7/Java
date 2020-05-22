package app;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormatSymbols;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//am facut if urile :))

public class LocaleExplore {
    private final static String baseName = "res.Messages";
    private static Locale locale;
    private static ResourceBundle resourceBundle;
    Properties commands = new Properties();

    public void run() throws IOException {
        setLocale("en-US");
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("Commands.properties");
        commands.load(inputStream);
        Scanner scanner = new Scanner(System.in);
        String set = commands.getProperty("set-locale.command");
        String info = commands.getProperty("info-locale.command");
        String display = commands.getProperty("display-locales.command");
        System.out.println("Type "+info+" or "+ display + " or "+set);
        while (true) {
            System.out.print(message("prompt"));

            String command = scanner.nextLine();
            if (command.equals("exit")) break;
            String[] params = command.trim().split("\\s+");
            try {
                if (params[0].equals(display)) {
                    Class clazz = Class.forName(commands.getProperty("display-locales.impl"));
                    clazz.getConstructor().newInstance();
                }
                if (params[0].equals(set)) {
                    Class clazz = Class.forName(commands.getProperty("set-locale.impl"));
                    clazz.getConstructor(String.class).newInstance(params[1]);
                }
                if (params[0].equals(info)) {
                    Class clazz = Class.forName(commands.getProperty("info-locale.impl"));
                    clazz.getConstructor().newInstance();
                }
                if (!params[0].equals(info)&&!params[0].equals(set)&&!params[0].equals(display)) {
                    System.out.println(message("invalid"));
                }

//                switch (params[0]) {
//                    case "locales": {
//                        Class clazz = Class.forName(commands.getProperty("display-locales.impl"));
//                        clazz.getConstructor().newInstance();
//                        break;
//                    }
////                    displayLocales(); break;
//                    case "set": {
//                        Class clazz = Class.forName(commands.getProperty("set-locale.impl"));
//                        clazz.getConstructor(String.class).newInstance(params[1]);
//                        break;
//                    }
////                        setLocale(params[1]);
////                        break;
//                    case "info": {
//                        Class clazz = Class.forName(commands.getProperty("info-locale.impl"));
//                        clazz.getConstructor().newInstance();
//                        break;
//                    }
////                        info();
////                        break;
//                    default:
//                        System.out.println(message("invalid"));
//
//
//                }
            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static String message(String key, String... arguments) {
        String pattern = resourceBundle.getString(key);
        String message = new MessageFormat(pattern).format(arguments);
        return message;
    }

    public static void setLocale(String language) {
        locale = Locale.forLanguageTag(language);
        resourceBundle = ResourceBundle.getBundle(baseName, locale);
        message("locale.set", language);
    }

    public static void displayLocales() {
        message("locales");
        Locale[] available =
                locale.getAvailableLocales();
        for (Locale localee : available) {
            System.out.println(localee.getDisplayCountry() + " " + localee.getDisplayLanguage(localee));
        }
    }

    public static void info() {
        message("info");
        System.out.println(message("info", locale.getDisplayName()));
        System.out.println("Country: " + locale.getCountry());
        System.out.println("Language: " + locale.getDisplayLanguage());
        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency.getCurrencyCode());
        DateFormatSymbols dateFormatSymbols = DateFormatSymbols.getInstance(locale);
        System.out.println("Week days:");
        for (String weekDay : dateFormatSymbols.getWeekdays()) {
            System.out.print(weekDay + " ");
        }
        System.out.println();
        System.out.println("Months:");
        for (String month : dateFormatSymbols.getMonths()) {
            System.out.print(month + " ");
        }
        System.out.println();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", locale);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Today: " + dateTimeFormatter.format(now));
    }

    public static void main(String args[]) {
        try {
            new LocaleExplore().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
