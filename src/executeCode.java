

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Servlet implementation class executeCode
 */
@WebServlet("/executeCode")
public class executeCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public executeCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String codeToExecute = request.getParameter("codeToExecute");
		if(codeToExecute!=""){
			
		
		System.out.println("codeToExecute:"+codeToExecute);
		ConsoleOutputCapturer COC=new ConsoleOutputCapturer();
		COC.start();
		
		doGet(request, response);
		//request.setAttribute("codeToExecute",codeToExecute);
		request.getSession().setAttribute("codeToExecute",codeToExecute);
		request.getSession().setAttribute("colorResult","#000000");
		//logic
		String source = codeToExecute;
		String codeResult="";
		
		File root = new File("S:/Development/workspace/OnlineCompilerWeb/src/"); 
		File sourceFile = new File(root, "/Test.java");
		sourceFile.getParentFile().mkdirs();
		try {
			//System.out.println("sourceFile.getPath():"+sourceFile.getPath());
			Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
			// Compile source file.
			//System.out.println("sourceFile.getPath():"+sourceFile.getPath());
			//System.out.println(System.getProperty("java.home"));
			System.setProperty("java.home", "C:\\Program Files\\Java\\jdk1.8.0_92");
			//System.out.println(System.getProperty("java.home"));
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			System.setProperty("java.home", "C:\\Program Files\\Java\\jre1.8.0_92");
			//System.out.println(System.getProperty("java.home"));
			compiler.run(null, null, null, sourceFile.getPath());

			URLClassLoader classLoader;
			try {

				classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
				Class<?> cls;
				try {
					
					cls = Class.forName("Test", true, classLoader);

					Object instance;
					try {
						instance = cls.newInstance();
						if(source.contains("main(")){
						Method meth = cls.getMethod("main", String[].class);
					    String[] params = null; // init params accordingly
					    try {
							meth.invoke(null, (Object) params);
						} catch (IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}}
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} // Should print "world".
 catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 // Should print "hello".
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//end logic
		String str=COC.stop();
		if(str.contains("error")){
			String str1[]=str.split("error");
			request.setAttribute("codeResult","error"+str1[1]+"error");
			request.getSession().setAttribute("codeResult","error"+str1[1]+"error");
			request.getSession().setAttribute("colorResult","#FF0000");
		}else{
			request.setAttribute("codeResult",str);
			request.getSession().setAttribute("codeResult",str);
		}
		File sourceFile1 = new File(root, "/Test.class");
		sourceFile.delete();
		sourceFile1.delete();
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp"); 
	    //dispatcher.forward(request,response);
	    response.sendRedirect("Home.jsp");  
		
	}else{
		//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Home.jsp"); 
	    //dispatcher.forward(request,response);
	    response.sendRedirect("Home.jsp");  
	}
	}
}
