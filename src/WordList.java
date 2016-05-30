import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

public class WordList {

  private static TreeMap<String, Integer> words;

  public WordList( InputStream wordlist) {
    readInList(wordlist);
  }

  private static void readInList( InputStream inStream) {
    try {
      words = new TreeMap<>();
      BufferedReader reader = new BufferedReader(new InputStreamReader(inStream ,
          StandardCharsets.ISO_8859_1));
      String word;

      while ((word = reader.readLine()) != null) {
        String[] wordSplit = word.split(" ");
        words.put(wordSplit[0].trim(), 100);
      }
    } catch (Exception e) {
      throw new RuntimeException("Cannot read in list!", e);
    }
  }


  public TreeMap<String, Integer> getWords() {
    return words;
  }

  public TreeMap<String, Integer> getWordsWithPrefix(String prefix) {
    Iterator<Entry<String, Integer>> iter = words.tailMap(prefix, true)
        .entrySet().iterator();

    if (iter == null)
      return new TreeMap<String, Integer>();

    TreeMap<String, Integer> resultMap = new TreeMap<>();
    Entry<String, Integer> e;

    while (iter.hasNext()) {
      e = (Entry<String, Integer>) iter.next();

      if (e.getKey().startsWith(prefix)) {
        resultMap.put(e.getKey(), e.getValue());
      } else {
        break;
      }
    }
    return resultMap;
  }

}
