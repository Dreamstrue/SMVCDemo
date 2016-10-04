package com.mvc.test.controller;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.Instance;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.accept.ServletPathExtensionContentNegotiationStrategy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.support.ServletConfigPropertySource;
import org.springframework.web.servlet.HttpServletBean;

@Controller
public class GoController implements EnvironmentAware{
	private static final Log logger = LogFactory.getLog(GoController.class);
	private Environment environment = null; 
	public static final Set<String> se = new HashSet<String>();
    //处理HEAD类型的”/”请求
    @RequestMapping(value={"/"},method= {RequestMethod.HEAD})
    public String head() {
        return "go.jsp";
    }
    //处理GET类型的"/index"和”/”请求
    @RequestMapping(value={"/index1","/"},method= {RequestMethod.GET})
    public String index(Model model) throws Exception {
        logger.info("======processed by index=======");
        //返回msg参数
        model.addAttribute("msg", "Go Go Go!");
        if(this.environment instanceof ConfigurableEnvironment){
        	ConfigurableEnvironment ce =(ConfigurableEnvironment)this.environment;
        	MutablePropertySources mps = ce.getPropertySources();
        	ServletConfigPropertySource  ps = (ServletConfigPropertySource)mps.get("servletConfigInitParams");
        	System.out.println();
        }
        return "go.jsp";
    }
	@Override
	public void setEnvironment(Environment arg0) {
		this.environment = arg0;
	}
	public static void  addSe(String dd){
		se.add(dd);
	}
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			GoController.addSe(""+i);
		}
		int c = se.size();
		System.out.println(c);
		
		
//		HttpServletBean
		
//		FrameworkServlet
	}
}
