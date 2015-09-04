package util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class filterling implements Filter{
	protected String encoding = null;
	protected FilterConfig config = null;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
		this.config = config;
		this.encoding = config.getInitParameter("encoding");
	}
	
	@Override
	public void destroy() {
		
		this.encoding = null;
		this.config = null;
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		if(req.getCharacterEncoding() == null){
			if(encoding != null){
				req.setCharacterEncoding(encoding);
				res.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(req, res);
	}
	
	public FilterConfig getFilterConfig(){
		return config;
	}
	
	public void setFilterConfig(FilterConfig cfg){
		config = cfg;
	}
}
