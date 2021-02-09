package telephony;

import java.awt.*;
import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder result = new StringBuilder();
        for (String url : urls) {
            if (containsDigits(url)) {
                result.append("Invalid URL!");
            } else {
                result.append("Browsing: ").append(url).append("!").append(System.lineSeparator());
            }
        }
        return result.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder result = new StringBuilder();
        for (String number : numbers) {
            if (hasOnlyDigits(number)) {
                result.append("Calling... ").append(number).append(System.lineSeparator());
            } else {
                result.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return result.toString().trim();
    }

    private boolean hasOnlyDigits(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean containsDigits(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))) {
                return true;
            }
        }
        return false;
    }
}