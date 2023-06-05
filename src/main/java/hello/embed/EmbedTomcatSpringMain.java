package hello.embed;

import hello.servlet.HelloServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbedTomcatSpringMain {
    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatServletMain.main");

        Tomcat tomcat= new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // 스프링 컨테이너 생성
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloServlet.class);


        // 디스패처 서블릿 생성
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        //디스패처서블릿 등록
        Context context = tomcat.addContext("","/");
        tomcat.addServlet("","dispatcher",dispatcher);
        context.addServletMappingDecoded("/","dispatcher");

        tomcat.start();

    }

}
