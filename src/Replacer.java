import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Replacer
{
    private static String _encoding;
    private static String allText="";
    private static int num;

    public static void main(String[] args)
    {

        File testFile = new File("doc.txt");
        testFile.getAbsolutePath();
        _encoding = "windows-1251";

        GetContents(testFile);

            System.out.print(allText+"\r\n");
        System.out.println("--------------------------------------------");

        Replace();

            System.out.print(allText+"\r\n");

        File newFile = new File("doc2.txt");
        newFile.getAbsolutePath();
        MakeReplacedFile(newFile);
    }


    static public void GetContents(File file)	{
        try	    {
            if (file == null)	    	{
                throw new IllegalArgumentException("File should not be null.");
            }
            if (!file.exists())	    	{
                throw new FileNotFoundException();
            }
            if (!file.canRead())	    	{
                throw new IllegalArgumentException("File cannot be written: " + file);
            }
            if (!file.isFile())	    	{
                throw new IllegalArgumentException("Should not be a directory: " + file);
            }

            FileInputStream fis = new FileInputStream(file);
            InputStreamReader in = new InputStreamReader(fis, _encoding);
            BufferedReader input =  new BufferedReader(in);

            try	   	{
                String line = null;
                while ((line = input.readLine()) != null)		{
                    allText=allText+" "+ line;
                }
            }
            finally    	{
                input.close();
            }
        }
        catch (FileNotFoundException ex)	    {
            System.out.println("File does not exist: " + file);
        }
        catch(IllegalArgumentException ex)	    {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)	    {
            ex.printStackTrace();
        }

    }

    static void Replace() {
        //Pattern p = Pattern.compile("[\\sодин|\\sдв.|\\sтри|\\sчетыр.|\\sпят.|\\sшест.|\\sсем.|\\sвосем.|\\sдевят.]");
       // Matcher m = p.matcher(allText);
        //m.find();
        allText=allText.replaceAll("\\sодин", " 1");
        allText=allText.replaceAll("\\sдв.", " 2");
        allText=allText.replaceAll("\\sтри", " 3");
        allText=allText.replaceAll("\\s[Ч|ч]етыр.", " 4");
        allText=allText.replaceAll("\\sпят.", " 5");
        allText=allText.replaceAll("\\sшест.", " 6");
        allText=allText.replaceAll("\\sсем.", " 7");
        allText=allText.replaceAll("\\sвосем.", " 8");
        allText=allText.replaceAll("\\sдевят.", " 9");

        Pattern p = Pattern.compile("\\dнадцат..");
        Matcher m = p.matcher(allText);

        while (m.find()){
            System.out.println(m.group().substring(0,1));
              //  m.
            }

    }

    static public void MakeReplacedFile(File file)	{
        try	    {

            if (!file.exists())    	{
                file.createNewFile();
            }

            if (!file.canRead())   	{
                throw new IllegalArgumentException("File cannot be written: " + file);
            }

            if (!file.isFile())   	{
                throw new IllegalArgumentException("Should not be a directory: " + file);
            }
            file.delete();
            file.createNewFile();
            FileOutputStream fis = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(fis, _encoding);
            BufferedWriter output =  new BufferedWriter(out);
            try   	{
                    output.append(allText);
                }

            finally    	{
                output.close();
            }
        }
        catch (FileNotFoundException ex)    {
            System.out.println("File does not exist: " + file);
        }
        catch(IllegalArgumentException ex)    {
            System.out.println(ex.getMessage());
        }
        catch (Exception ex)    {
            ex.printStackTrace();
        }
    }
}
