

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(asyncSupported = true, urlPatterns = { "/AjaxServlet" })
public class AjaxServlet extends HttpServlet {
	
  private static final long serialVersionUID = 1L;
       
  private static WordList words = new WordList();

    public AjaxServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  String param = request.getParameter("q");
		
		if (param == null || param.length() < 1){
		  response.getWriter().append("start typing for hints...").close();
		  return ;
		}
		
		TreeMap<String,Integer> hints = words.getWordsWithPrefix(param);	
		response.getWriter().append(writeReturnList(hints)).flush();
		return;
	}

	private String writeReturnList(Map<String,Integer> hints) {
    StringBuffer buffer = new StringBuffer();
    buffer.append("<p><ul>");
    
    for( String s : hints.keySet()){
      buffer.append("<li>").append(s).append("</li>");
    }
    buffer.append("</ul></p>");
    return buffer.toString();
	}


  /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
