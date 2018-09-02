package example.fileProcessors;

import example.model.UserDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

public class SoftwareXFileProcessor {

    private static final String femalePrefixes[] = {"madame", "mademoiselle", "veuve", "mme", "mlle"};
    private static final String malePrefixes[] = {"monsieur", "veuf", "m",};

    private final String DATE_FORMAT = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);

    private Pattern genderPattern = Pattern.compile("Civilité (.*)");
    private Pattern lastWordPattern = Pattern.compile(".*\\b (.+)");
    private Pattern zipCodePattern = Pattern.compile(".*\\b (([0-9]{2}))");

    private String dateFormat = "dd/MM/yyyy";

    public boolean isValid(String text) {
        return false;
    }

    public UserDto getUserInfo(String text) throws DataFormatException {
        Scanner scanner = new Scanner(text);

        String gender = null;
        String fname = null;
        String lname = null;
        String email = null;
        String country = null;
        int dept = -1;
        String birth = null; //YYYY-MM-DD
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Civilité") && fname == null) {
                Matcher matcher = genderPattern.matcher(line);
                if (matcher.find()) {
                    String genderPrefix = matcher.group(1).trim();
                    gender = getGender(genderPrefix);
                }
            } else if (line.toLowerCase().startsWith("prénom") && email == null) {
                Matcher matcher = lastWordPattern.matcher(line);
                if (matcher.find()) {
                    fname = matcher.group(1).trim();
                }
            } else if (line.startsWith("Nom") && email == null) {
                Matcher matcher = lastWordPattern.matcher(line);
                if (matcher.find()) {
                    lname = matcher.group(1).trim();
                }
            } else if (line.startsWith("E-mail") && email == null) {
                Matcher matcher = lastWordPattern.matcher(line);
                if (matcher.find()) {
                    email = matcher.group(1).trim();
                }
            } else if (line.startsWith("Né(e) le") && birth == null) {
                Matcher matcher = lastWordPattern.matcher(line);
                if (matcher.find()) {
                    birth = getFormattedDate(dateFormat, matcher.group(1).trim());
                }
            } else if (line.startsWith("Code postal") && dept == -1) {
                Matcher matcher = zipCodePattern.matcher(line);
                if (matcher.find()) {
                    dept = Integer.parseInt(matcher.group(1));
                }
            }
        }
        scanner.close();
        if (lname == null || fname == null || lname.equals("") || fname.equals("")) {
            throw new DataFormatException();
        }
        return new UserDto(gender, fname, lname, email, "", dept, birth, "test");
    }

    protected String getGender(String prefix) {
        for (String pref : femalePrefixes) {
            if (prefix.trim().replace(".", "").trim().equalsIgnoreCase(pref)){
                return "F";
            }
        }
        for (String pref : malePrefixes) {
            if (prefix.trim().replace(".", "").trim().equalsIgnoreCase(pref)){
                return "M";
            }
        }
        return null;
    }

    protected String getFormattedDate(String sourceFormat, String dateStr){
        SimpleDateFormat wrongFormatDt = new SimpleDateFormat(sourceFormat);
        try {
            Date date = wrongFormatDt.parse(dateStr);
            return dateFormatter.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateStr;
    }

}
