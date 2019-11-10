package Connect;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import javax.servlet.http.HttpServlet;



public class FreemarkerConfigurator {

    public static FreemarkerConfigurator instance;

    public FreemarkerConfigurator(HttpServlet servlet) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setServletContextForTemplateLoading(servlet.getServletContext(), "WEB-INF/pages");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        servlet.getServletContext().setAttribute("cfg", cfg);
    }

    public static synchronized void getInstance(HttpServlet servlet) {
        if (instance == null) {
            instance = new FreemarkerConfigurator(servlet);
        }
    }

}
