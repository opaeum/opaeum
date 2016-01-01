package org.opaeum.runtime.event;

import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.opaeum.runtime.environment.Environment;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;

public class Emailler{
	private static Configuration cfg;
	private Collection<AttachmentHolder> attachments;
	private INotificationHandler handler;
	private Object target;
	public Emailler(Object target,INotificationHandler nh){
		if(cfg==null){
			cfg = new Configuration();
			// Specify the data source where the template files come from.
			// Here I set a file directory for it:
			cfg.setClassForTemplateLoading(target.getClass(), "/");
			// Specify how templates will see the data-model. This is an
			// advanced
			// topic...
			// but just use this:
			cfg.setObjectWrapper(new DefaultObjectWrapper());

		}
		this.handler = nh;
		this.target = target;
	}
	public boolean sendMail(){
		try{
			String templateLocation = handler.getClass().getName().replaceAll("Handler", "").replaceAll("\\.", "/");
			Template temp = null;
			try{
				temp = cfg.getTemplate(templateLocation + ".ftl");
			}catch(Exception e){
				try{
					temp = cfg.getTemplate(templateLocation + "Default.ftl");
				}catch(Exception e2){
					temp=new Template("dummy", new CharArrayReader("A Notification was send without a body".toCharArray()), cfg);
				}
			}
			CharArrayWriter out = new CharArrayWriter();
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel inetStatic = (TemplateHashModel) staticModels.get("java.net.InetAddress");
			Map<String,Object> emailMap = new HashMap<String,Object>();
			emailMap.put("InetAddress", inetStatic);
			temp.process(emailMap, out);
			out.flush();
			HtmlEmail htmlEmail = new HtmlEmail();
			htmlEmail.setHostName("smtp.gmail.com");
			htmlEmail.setTLS(true);
			htmlEmail.setAuthentication("test@gmail.com", "password");
			htmlEmail.setSmtpPort(587);
			if(handler.getFrom() != null && handler.getFrom().getPreferredEMailAddress() != null
					&& handler.getFrom().getPreferredEMailAddress().getEmailAddress() != null){
				htmlEmail.setFrom(handler.getFrom().getPreferredEMailAddress().getEmailAddress());
			}
			if(handler.getTo() != null){
				for(INotificationReceiver nr:handler.getTo()){
					addTo(htmlEmail, nr);
				}
			}
			if(target instanceof INotificationReceiver){
				addTo(htmlEmail, (INotificationReceiver) target);
			}else if(target instanceof Collection){
				Collection<?> c = (Collection<?>) target;
				for(Object object:c){
					if(object instanceof INotificationReceiver){
						addTo(htmlEmail, (INotificationReceiver) object);
					}
				}
			}
			if(handler.getCc() != null){
				for(INotificationReceiver nr:handler.getCc()){
					if(nr.getPreferredEMailAddress() != null && nr.getPreferredEMailAddress().getEmailAddress() != null){
						htmlEmail.addCc(nr.getPreferredEMailAddress().getEmailAddress());
					}
				}
			}
			if(handler.getBcc() != null){
				for(INotificationReceiver nr:handler.getBcc()){
					if(nr.getPreferredEMailAddress() != null && nr.getPreferredEMailAddress().getEmailAddress() != null){
						htmlEmail.addBcc(nr.getPreferredEMailAddress().getEmailAddress());
					}
				}
			}
			htmlEmail.setSubject("TODO");// TODO
			htmlEmail.setHtmlMsg(out.toString());
			if(this.attachments != null){
				for(AttachmentHolder ds:this.attachments){
					htmlEmail.attach(ds.getDataSource(), ds.getName(), ds.getName());
				}
			}
			htmlEmail.send();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	protected void addTo(HtmlEmail htmlEmail,INotificationReceiver nr) throws EmailException{
		if(nr.getPreferredEMailAddress() != null && nr.getPreferredEMailAddress().getEmailAddress() != null){
			htmlEmail.addTo(nr.getPreferredEMailAddress().getEmailAddress());
		}
	}
}
