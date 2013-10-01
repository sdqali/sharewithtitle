package in.sdqali.sharewithtitle;

import org.apache.commons.lang3.StringEscapeUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TitleParser {
    public static String retrieveTitle(String html) {
        Pattern p = Pattern.compile("<head>.*?<title>(.*?)</title>", Pattern.DOTALL);
        Matcher m = p.matcher(html);
        String title = null;
        while (m.find()) {
            title = m.group(1);
        }
        return StringEscapeUtils.unescapeHtml4(title);
    }
}