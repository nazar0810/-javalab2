import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;
import static org.junit.Assert.assertEquals;
public class testParser {
    @Rule
    public TemporaryFolder tempFold = new TemporaryFolder();


    @Test
    public void testParseFileLen() throws IOException {
        File in = tempFold.newFile("file.csv");
        File out = tempFold.newFile("out.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(in.getAbsoluteFile()));
        writer.write("abc,\"abc,abc\"abc,abc");
        writer.close();
        Parser instance = new Parser(in.getAbsolutePath(),out.getAbsolutePath());
        instance.parseFile(',',"len");

        BufferedReader reader = new BufferedReader(new FileReader(out.getAbsoluteFile()));
        assertEquals("3+10+3+", reader.readLine());
        reader.close();
    }
    @Test
    public void testParseFileParse() throws IOException {
        File in = tempFold.newFile("file.csv");
        File out = tempFold.newFile("out.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(in.getAbsoluteFile()));
        writer.write("abc,\"abc,abc\"abc,abc");
        writer.newLine();
        writer.write("abc,/*abc*/abc,abc");
        writer.close();
        Parser instance = new Parser(in.getAbsolutePath(),out.getAbsolutePath());
        instance.parseFile(',',"parse");

        BufferedReader reader = new BufferedReader(new FileReader(out.getAbsoluteFile()));
        assertEquals("abc+abc,abcabc+abc+", reader.readLine());
        assertEquals("abc+abc+abc+", reader.readLine());
        reader.close();
    }


}
