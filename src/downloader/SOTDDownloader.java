package downloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SOTDDownloader implements Downloader {

    @Override
    public void downloadAndWriteToDir(String path, int numberOfFiles) throws IOException {

        SimpleDateFormat monthFormatter = new SimpleDateFormat("MMM", Locale.US);
        Calendar calendar = Calendar.getInstance();

        for(int i=0; i < numberOfFiles; i++, calendar.add(Calendar.MONTH, -1)) {

            String month = monthFormatter.format(calendar.getTime());

            String content = download(
                monthFormatter.format(calendar.getTime()),
                Integer.toString(calendar.get(Calendar.YEAR))
            );


            String file = path
                + File.separator
                + monthFormatter.format(calendar.getTime())
                + calendar.get(Calendar.YEAR)
                + ".txt";

            writeToFile(file, content);
        }
    }

    private void writeToFile(String file, String content) throws IOException {

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.print(content);
        writer.close();
    }

    private String download(String month, String year) throws IOException {

        URL url = null;
        HttpURLConnection connection;

        try{
            url = new URL(
            "http://www.sudokuoftheday.co.uk/cgi-bin/sudoku.cgi?ACTION=archive2&MONTH="
                    + month
                    + "&YEAR="
                    + year
                    + "&USER="
            );
        } catch(MalformedURLException e) {
            System.out.println(e);
        }

        connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        InputStream response = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(response));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line);
            content.append('\r');
        }
        reader.close();

        return content.toString();
    }
}
